package com.javaweb.enums;

import java.util.Map;
import java.util.TreeMap;

public enum TypeCode {
    NOI_THAT("Nội Thất"),
    TANG_TRET("Tầng Trệt"),
    NGUYEN_CAN("Nguyên Căn");

    private final String name;
    TypeCode(String name) {
        this.name = name;
    }
    public static Map<String, String> getAllTypeCode() {
        Map<String, String> typeCodeMap = new TreeMap<String, String>();
        for (TypeCode item : TypeCode.values()) {
            typeCodeMap.put(item.toString(), item.name);
        }
        return typeCodeMap;
    }
}
