package com.esportslan.microservices.esportslanapi.utilities;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CityCodes {
    private static final Map<String, String> CITY_CODE_MAP;

    static {
        Map<String, String> map = new HashMap<>();

        map.put("mumbai", "CTBOM");
        map.put("delhi", "CTDEL");
        map.put("bangalore", "CTBLR");
        map.put("chennai", "CTMAA");
        map.put("hyderabad", "CTHYD");
        map.put("kolkata", "CTCCU");
        map.put("pune", "CTPNQ");

        map.put("goa", "CTGOI");
        map.put("jaipur", "CTJAI");
        map.put("udaipur", "CTUDR");
        map.put("kochi", "CTCOK");
        map.put("trivandrum", "CTTRV");
        map.put("coimbatore", "CTCJB");
        map.put("madurai", "CTIXM");
        map.put("vizag", "CTVIZ");

        CITY_CODE_MAP = Collections.unmodifiableMap(map);
    }

    private CityCodes() {}

    public static String resolveCityCode(String city) {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be null or empty");
        }

        return CITY_CODE_MAP.getOrDefault(city.toLowerCase(), city);
    }
}
