package com.esportslan.microservices.esportslanapi.utilities;

import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class Utils {

    public static boolean isStringEmptyOrBlank(String value) {
        if (StringUtils.isEmpty(value) || StringUtils.isBlank(value)) {
            return true;
        }
        return false;
    }

    public static Date convertToSQLDate(String dateString) {
        LocalDate localDate = LocalDate.parse(dateString);

        return Date.valueOf(localDate);
    }

    public static boolean isFutureDate(String dateString) {
        Date dateToCheck = convertToSQLDate(dateString);
        LocalDate localDate = dateToCheck.toLocalDate();

        LocalDate today = LocalDate.now();

        return localDate.isAfter(today);
    }
}
