package com.javaweb.repository;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAssignmentBuildingRepository extends JpaRepository<AssignmentBuildingEntity, Long> {
    void deleteAssignmentBuildingEntitiesByBuilding(BuildingEntity building);

    void deleteAssignmentBuildingEntitiesByBuildingIdIn(List<Long> buildingIds);
}
