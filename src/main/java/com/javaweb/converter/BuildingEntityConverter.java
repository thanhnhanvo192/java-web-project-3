package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuildingEntityConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TypeCodeConverter typeCodeConverter;
    @Autowired
    private RentAreaConverter rentAreaConverter;

    public BuildingEntity toBuildingEntity(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        modelMapper.map(buildingDTO, buildingEntity);
        setTypeCode(buildingDTO, buildingEntity);
        setRentAreas(buildingDTO, buildingEntity);
        return buildingEntity;
    }

    private void setTypeCode(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        if (buildingDTO.getTypeCode() != null) {
            buildingEntity.setType(typeCodeConverter.listToString(buildingDTO.getTypeCode()));
        }
    }
    private void setRentAreas(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        List<RentAreaEntity> current = buildingEntity.getRentAreas();
        current.clear();
        if (buildingDTO.getRentArea() != null) {
            List<Long> rentAreaValues = rentAreaConverter.stringToList(buildingDTO.getRentArea());
            List<RentAreaEntity> rentAreas = rentAreaConverter.toRentAreaEntities(rentAreaValues, buildingEntity);
            current.addAll(rentAreas);
        }
    }
}
