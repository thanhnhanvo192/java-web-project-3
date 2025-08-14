package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentAreaConverter {
    public String listToString(List<RentAreaEntity> rentAreas) {
        return rentAreas.stream().map(item -> item.getValue().toString())
                .collect(Collectors.joining(","));
    }
    public List<Long> stringToList(String rentAreaStr) {
        return Arrays.stream(rentAreaStr.split(","))
                .map(String::trim)
                .map(Long::valueOf)
                .collect(Collectors.toList());
    }
    public List<RentAreaEntity> toRentAreaEntities(List<Long> rentAreaValues, BuildingEntity buildingEntity) {
        return rentAreaValues.stream()
                .map(val -> {
                    RentAreaEntity rentArea = new RentAreaEntity();
                    rentArea.setValue(val);
                    rentArea.setBuilding(buildingEntity);
                    return rentArea;
                }).collect(Collectors.toList());
    }
}
