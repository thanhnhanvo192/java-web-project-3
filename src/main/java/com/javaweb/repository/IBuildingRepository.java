package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.IBuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBuildingRepository extends JpaRepository<BuildingEntity, Long>, IBuildingRepositoryCustom {

    void deleteBuildingEntitiesByIdIn(List<Long> ids);
}
