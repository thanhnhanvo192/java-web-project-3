package com.javaweb.controller.admin;

import com.javaweb.enums.District;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IBuildingService buildingService;

    @GetMapping (value = "/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute BuildingSearchRequest buildingSearchRequest) {
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("buildingSearchRequest", buildingSearchRequest);

        List<BuildingSearchResponse> buildingSearchResponseList = buildingService.findBuildings(buildingSearchRequest);
        mav.addObject("buildingSearchResponseList", buildingSearchResponseList);
        mav.addObject("staffs", userService.getStaffs());
        mav.addObject("districts", District.getAllDistricts());
        mav.addObject("typeCodes", TypeCode.getAllTypeCode());
        return mav;
    }

    @GetMapping (value = "/admin/building-edit")
    public ModelAndView buildingEdit(@ModelAttribute("buildingEdit") BuildingDTO buildingEdit) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("districts", District.getAllDistricts());
        mav.addObject("typeCodes", TypeCode.getAllTypeCode());
        return mav;
    }

    @GetMapping (value = "/admin/building-edit-{id}")
    public ModelAndView buildingEdit(@PathVariable("id") Long Id) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        BuildingDTO buildingDTO = new BuildingDTO();
        buildingDTO.setId(Id);
        buildingDTO.setName("test name");
        mav.addObject("buildingEdit", buildingDTO);
        mav.addObject("districts", District.getAllDistricts());
        mav.addObject("typeCodes", TypeCode.getAllTypeCode());
        return mav;
    }
}
