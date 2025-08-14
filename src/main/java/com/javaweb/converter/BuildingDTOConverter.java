package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuildingDTOConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TypeCodeConverter typeCodeConverter;
    @Autowired
    private RentAreaConverter rentAreaConverter;

    public BuildingDTO toBuildingDTO(BuildingEntity buildingEntity) {
        BuildingDTO buildingDTO = modelMapper.map(buildingEntity, BuildingDTO.class);
        buildingDTO.setTypeCode(typeCodeConverter.stringToList(buildingEntity.getType()));
        buildingDTO.setRentArea(rentAreaConverter.listToString(buildingEntity.getRentAreas()));
        return buildingDTO;
    }
}
