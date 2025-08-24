package com.javaweb.service;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBuildingService {
    public ResponseDTO listStaffs(Long buildingId);
    public List<BuildingSearchResponse> findBuildings(BuildingSearchRequest buildingSearchRequest, Pageable pageable);
    public BuildingDTO addOrUpdateBuilding(BuildingDTO buildingDTO);
    public BuildingDTO findBuildingById(Long buildingId);
    public void deleteBuilding(List<Long> buildingIds);
    public void assignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO);
}
