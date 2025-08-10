package com.javaweb.repository.custom;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;

import java.util.List;

public interface IBuildingRepositoryCustom {
    List<BuildingEntity> findBuildings(BuildingSearchBuilder buildingSearchBuilder);
}
