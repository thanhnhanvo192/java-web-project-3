package com.javaweb.controller.admin;

import com.javaweb.enums.District;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IBuildingService buildingService;

    @GetMapping (value = "/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute BuildingSearchRequest model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/list");
        DisplayTagUtils.of(request, model);
        Pageable pageable = PageRequest.of(
                model.getPage() - 1,
                model.getMaxPageItems(),
                Sort.by("id").ascending()
        );
        List<BuildingSearchResponse> buildingSearchResponseList = buildingService.findBuildings(model, pageable);
        model.setListResult(buildingSearchResponseList);

        mav.addObject("model", model);
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
        BuildingDTO buildingDTO = buildingService.findBuildingById(Id);
        mav.addObject("buildingEdit", buildingDTO);
        mav.addObject("districts", District.getAllDistricts());
        mav.addObject("typeCodes", TypeCode.getAllTypeCode());
        return mav;
    }
}
