package com.javaweb.converter;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class TypeCodeConverter {
    public String listToString(List<String> typeCodes) {
        if (typeCodes == null || typeCodes.size() == 0) {
            return "";
        }
        return String.join(",", typeCodes);
    }
    public List<String> stringToList(String typeCodeStr) {
        if (typeCodeStr == null || typeCodeStr.equals("")) {
            return null;
        }
        return Arrays.asList(typeCodeStr.split(","));
    }
}
