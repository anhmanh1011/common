package com.yody.common.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class MapperUtils {

    private static ObjectMapper objectMapper = null;

    private MapperUtils() {
    }

    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper().configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
        return objectMapper;
    }

    public static <T> T jsonToObject(String source, Class<T> target) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        try {
            return getObjectMapper().readValue(source, target);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String objectToJson(Object o) {
        try {
            return getObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BigDecimal stringToDecimal(String s) {
        if (StringUtils.isBlank(s)) {
            return null;
        }
        if (NumberUtils.isCreatable(s)) {
            return new BigDecimal(s).setScale(2, RoundingMode.HALF_UP);
        }
        try {
            return new BigDecimal(new BigInteger(Base64.getDecoder().decode(s)), 2);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }
}
