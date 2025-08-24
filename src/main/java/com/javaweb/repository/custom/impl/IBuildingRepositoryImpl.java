package com.javaweb.repository.custom.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.IBuildingRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class IBuildingRepositoryImpl implements IBuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
//     JOIN TABLE
    public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
        //join table district
        Long staffId = buildingSearchBuilder.getStaffId();
        if (staffId != null) {
            sql.append(" INNER JOIN assignmentbuilding ON assignmentbuilding.buildingid = b.id ");
        }
        //join table rentarea
        Long areaFrom = buildingSearchBuilder.getAreaFrom();
        Long areaTo = buildingSearchBuilder.getAreaTo();
        if (areaFrom != null || areaTo != null) {
            sql.append(" INNER JOIN rentarea ON rentarea.buildingid = b.id ");
        }
    }
    //NORMAL QUERY
    public static void normalQuery(BuildingSearchBuilder buildingSearchBuilder, StringBuilder whereSql) {
        try {
            Field[] fields = buildingSearchBuilder.getClass().getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if (fieldName.equals("staffId") || fieldName.equals("typeCode")
                || fieldName.startsWith("area") || fieldName.startsWith("rentPrice"))
                    continue;
                Object value = item.get(buildingSearchBuilder);
                if (value != null && !"".equals(value)) {
                    if (item.getType() == Long.class || item.getType() == Integer.class) {
                        whereSql.append(" AND b." + fieldName + " = " + value + " ");
                    }
                    else if (item.getType() == String.class) {
                        whereSql.append(" AND b." + fieldName + " LIKE '%" + value + "%' ");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //SPECIAL QUERY
    public static void specialQuery(BuildingSearchBuilder buildingSearchBuilder, StringBuilder whereSql) {
        // staffId
        Long staffId = buildingSearchBuilder.getStaffId();
        if (staffId != null) {
            whereSql.append(" AND assignmentbuilding.staffid = " + staffId + " ");
        }
        // rentArea
        Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();
        Long rentAreaTo = buildingSearchBuilder.getAreaTo();
        if (rentAreaFrom != null || rentAreaTo != null) {
            whereSql.append(" AND EXISTS (SELECT 1 FROM rentarea WHERE rentarea.buildingid = b.id ");
            if (rentAreaFrom != null) {
                whereSql.append(" AND rentarea.value >= " + rentAreaFrom + " ");
            }
            if (rentAreaTo != null) {
                whereSql.append(" AND rentarea.value <= " + rentAreaTo + " ");
            }
            whereSql.append(" ) ");
        }
        //rentPrice
        Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
        Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
        if (rentPriceFrom != null) {
            whereSql.append(" AND b.rentprice >= " + rentPriceFrom + " " );
        }
        if (rentPriceTo != null) {
            whereSql.append(" AND b.rentprice <= " + rentPriceTo + " ");
        }
        List<String> typeCode = buildingSearchBuilder.getTypeCode();
        if (typeCode != null && !typeCode.isEmpty()) {
            whereSql.append(" AND(");
            String listTypeCode = typeCode.stream().map(item -> "b.type LIKE '%" + item + "%' ").collect(Collectors.joining(" OR "));
            whereSql.append(listTypeCode);
            whereSql.append(" ) ");
        }
    }

    @Override
    public Page<BuildingEntity> findBuildings(BuildingSearchBuilder buildingSearchBuilder, Pageable pageable) {
        StringBuilder fromSql = new StringBuilder(" FROM building b ");
        StringBuilder whereSql = new StringBuilder(" WHERE 1=1 ");

        joinTable(buildingSearchBuilder, fromSql);
        normalQuery(buildingSearchBuilder, whereSql);
        specialQuery(buildingSearchBuilder, whereSql);

        StringBuilder sql =
                new StringBuilder(" SELECT b.* " + fromSql + whereSql + " GROUP BY b.id ORDER BY b.id ASC ");
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<BuildingEntity> result = query.getResultList();
        String splCount = " SELECT COUNT(DISTINCT b.id) " + fromSql + whereSql;
        Query countQuery = entityManager.createNativeQuery(splCount);
        Number total = (Number) countQuery.getSingleResult();

        return new PageImpl<>(result, pageable, total.longValue());
    }
}
