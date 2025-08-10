package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.converter.BuildingSearchResponseConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.IBuildingRepository;
import com.javaweb.repository.IRentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<BuildingSearchResponse> findBuildings(BuildingSearchRequest buildingSearchRequest) {
        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(buildingSearchRequest);
        List<BuildingEntity> buildingEntities = buildingRepository.findBuildings(buildingSearchBuilder);
        List<BuildingSearchResponse> result = new ArrayList<>();
        //Filter
        for (BuildingEntity buildingEntity : buildingEntities) {
            BuildingSearchResponse buildingSearchResponse =
                    buildingSearchResponseConverter.toBuildingSearchResponse(buildingEntity);
            result.add(buildingSearchResponse);
        }
        return result;
    }

    @Override
    public BuildingDTO addBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);

        if (buildingDTO.getTypeCode() != null) {
            buildingEntity.setType(String.join(",", buildingDTO.getTypeCode()));
        }
        String rentAreaStr = buildingDTO.getRentArea();
        String[] rentAreaArr = rentAreaStr.split(",");
        for (String item : rentAreaArr) {
            String trimedRentArea = item.trim();
            try {
                int value = Integer.parseInt(trimedRentArea);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Rent area is not a number");
            }
        }
        for (String rentArea : rentAreaArr) {
            String trimedRentArea = rentArea.trim();
            int value = Integer.parseInt(trimedRentArea);
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setValue(rentArea);
            rentAreaEntity.setBuilding(buildingEntity);
            rentAreaRepository.save(rentAreaEntity);
        }
        if (buildingDTO.getId() != null) {
            buildingRepository.findById(buildingDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Building with id: " + buildingDTO.getId()));
        }
        BuildingEntity savedEntity = buildingRepository.save(buildingEntity);
        return modelMapper.map(savedEntity, BuildingDTO.class);
    }
}
