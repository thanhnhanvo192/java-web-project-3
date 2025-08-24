package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.IBuildingService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController(value = "BuildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {
    @Autowired
    private IBuildingService buildingService;

    @PostMapping("/addOrUpdate")
    public ResponseEntity<BuildingDTO> addOrUpdateBuilding(@Valid @RequestBody BuildingDTO buildingDTO) {
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
    public ResponseEntity<List<BuildingSearchResponse>> searchBuilding(@RequestBody BuildingSearchRequest model, HttpServletRequest request) {
        DisplayTagUtils.of(request, model);
        Pageable pageable = PageRequest.of(
                model.getPage() - 1,
                model.getMaxPageItems(),
                Sort.by("id").ascending()
        );
        List<BuildingSearchResponse> result = buildingService.findBuildings(model, pageable);
        return ResponseEntity.ok(result);
    }
}
