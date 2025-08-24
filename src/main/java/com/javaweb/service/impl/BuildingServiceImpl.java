package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.*;
import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.IAssignmentBuildingRepository;
import com.javaweb.repository.IBuildingRepository;
import com.javaweb.repository.IRentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingServiceImpl implements IBuildingService {
    @Autowired
    private IBuildingRepository buildingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;
    @Autowired
    private BuildingSearchResponseConverter buildingSearchResponseConverter;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IRentAreaRepository rentAreaRepository;
    @Autowired
    private BuildingDTOConverter buildingDTOConverter;
    @Autowired
    private BuildingEntityConverter buildingEntityConverter;
    @Autowired
    private IAssignmentBuildingRepository assignmentBuildingRepository;

    @Override
    public ResponseDTO listStaffs(Long buildingId) {
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> staffAssigment = building.getUserEntities();
        List<StaffResponseDTO> staffResponseDTOList = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        for (UserEntity staff : staffs) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(staff.getId());
            staffResponseDTO.setFullName(staff.getFullName());
            if (staffAssigment.contains(staff)) {
                staffResponseDTO.setChecked("checked");
            } else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOList.add(staffResponseDTO);
        }
        responseDTO.setData(staffResponseDTOList);
        responseDTO.setMessage("success");
        return responseDTO;
    }

    @Override
    public List<BuildingSearchResponse> findBuildings(BuildingSearchRequest buildingSearchRequest, Pageable pageable) {
        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(buildingSearchRequest);

        Page<BuildingEntity> buildingEntities = buildingRepository.findBuildings(buildingSearchBuilder, pageable);
        List<BuildingEntity> buildings = buildingEntities.getContent();

        List<BuildingSearchResponse> result = new ArrayList<>();
        //Filter
        for (BuildingEntity buildingEntity : buildings) {
            BuildingSearchResponse buildingSearchResponse =
                    buildingSearchResponseConverter.toBuildingSearchResponse(buildingEntity);
            result.add(buildingSearchResponse);
        }

        buildingSearchRequest.setTotalItems((int)buildingEntities.getTotalElements());
        return result;
    }

    @Override
    @Transactional
    public BuildingDTO addOrUpdateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity;
        if (buildingDTO.getId() != null) {
            buildingEntity = buildingRepository.findById(buildingDTO.getId()).get();
            rentAreaRepository.deleteRentAreaEntitiesByBuilding(buildingEntity);
        } else {
            buildingEntity = new BuildingEntity();
        }
        buildingEntity = buildingEntityConverter.toBuildingEntity(buildingDTO, buildingEntity);
        rentAreaRepository.saveAll(buildingEntity.getRentAreas());
        BuildingEntity savedEntity = buildingRepository.save(buildingEntity);
        return modelMapper.map(savedEntity, BuildingDTO.class);
    }

    @Override
    public BuildingDTO findBuildingById(Long buildingId) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
        BuildingDTO buildingDTO = buildingDTOConverter.toBuildingDTO(buildingEntity);
        return buildingDTO;
    }

    @Override
    @Transactional
    public void deleteBuilding(List<Long> buildingIds) {
        for (Long buildingId : buildingIds) {
            BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
            rentAreaRepository.deleteRentAreaEntitiesByBuilding(buildingEntity);
            assignmentBuildingRepository.deleteAssignmentBuildingEntitiesByBuilding(buildingEntity);
            buildingRepository.deleteById(buildingId);
        }
    }

    @Override
    @Transactional
    public void assignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        BuildingEntity buildingEntity = buildingRepository
                .findById(assignmentBuildingDTO.getBuildingId()).get();
        assignmentBuildingRepository.deleteAssignmentBuildingEntitiesByBuilding(buildingEntity);

        List<Long> staffIds = assignmentBuildingDTO.getStaffs();
        List<AssignmentBuildingEntity> assignmentBuildingEntityList = new ArrayList<>();
        for (Long staffId : staffIds) {
            UserEntity staff = userRepository.findById(staffId).get();
            AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();
            assignmentBuildingEntity.setBuilding(buildingEntity);
            assignmentBuildingEntity.setStaff(staff);
            assignmentBuildingEntityList.add(assignmentBuildingEntity);
        }
        assignmentBuildingRepository.saveAll(assignmentBuildingEntityList);
    }
}
