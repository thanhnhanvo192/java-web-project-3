package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "BuildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {
    @Autowired
    private IBuildingService buildingService;

    @PostMapping("/addOrUpdate")
    public ResponseEntity<BuildingDTO> addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO) {
        //Xuống DB để xử lí thêm hoặc sửa
        BuildingDTO savedBuilding = buildingService.addOrUpdateBuilding(buildingDTO);
        return ResponseEntity.ok(savedBuilding);
    }
    @DeleteMapping("/{ids}")
    public void deleteBuilding(@PathVariable List<Long> ids) {
        buildingService.deleteBuilding(ids);
    }
    @GetMapping("/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable Long id) {
    ResponseDTO result = buildingService.listStaffs(id);
    return result;
    }
    @PostMapping("/assignment")
    public void updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO) {
        buildingService.assignmentBuilding(assignmentBuildingDTO);
    }
    @PostMapping("/search")
    public ResponseEntity<List<BuildingSearchResponse>> searchBuilding(@RequestBody BuildingSearchRequest buildingSearchRequest) {
        List<BuildingSearchResponse> result = buildingService.findBuildings(buildingSearchRequest);
        return ResponseEntity.ok(result);
    }
}
