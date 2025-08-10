package com.javaweb.service;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;

import java.util.List;

public interface IBuildingService {
    public ResponseDTO listStaffs(Long buildingId);
    public List<BuildingSearchResponse> findBuildings(BuildingSearchRequest buildingSearchRequest);
    public BuildingDTO addBuilding(BuildingDTO buildingDTO);
}
