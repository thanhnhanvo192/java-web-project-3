package com.javaweb.converter;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.model.request.BuildingSearchRequest;
import org.springframework.stereotype.Component;

@Component
public class BuildingSearchBuilderConverter {
    public BuildingSearchBuilder toBuildingSearchBuilder(BuildingSearchRequest request) {
        BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
                .setName(request.getName())
                .setFloorArea(request.getFloorArea())
                .setWard(request.getWard())
                .setStreet(request.getStreet())
                .setDistrictId(request.getDistrict())
                .setNumberOfBasement(request.getNumberOfBasement())
                .setTypeCode(request.getTypeCode())
                .setManagerName(request.getManagerName())
                .setManagerPhone(request.getManagerPhone())
                .setRentPriceFrom(request.getRentPriceFrom())
                .setRentPriceTo(request.getRentPriceTo())
                .setAreaFrom(request.getAreaFrom())
                .setAreaTo(request.getAreaTo())
                .setStaffId(request.getStaffId())
                .build();
        return buildingSearchBuilder;
    }
}
