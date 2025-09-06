package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRentAreaRepository extends JpaRepository<RentAreaEntity, Long> {
    void deleteRentAreaEntitiesByBuilding(BuildingEntity building);

    void deleteRentAreaEntitiesByBuildingIdIn(List<Long> buildingIds);
}
