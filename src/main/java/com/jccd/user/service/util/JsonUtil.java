package com.jccd.user.service.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.text.SimpleDateFormat;


public class JsonUtil {
    private static final String DEFAULT_DATE_FORMAT = "yyyyMMdd HH:mm:ss.SSS";
    private static final ObjectMapper OBJECT_MAPPER = createObjectMapper();

    public static <T> T toObject(String json, Class<T> type) {
        if (null == json) return null;

        try {
            return OBJECT_MAPPER.readValue(json, type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 把Json字符串转换成对象
     *
     * @param <T>  the type parameter
     * @param json the json
     * @param typeReference    the typeReference
     * @return object
     */
    public static <T> T toObject(String json, TypeReference<T> typeReference) {
        if (null == json) return null;

        try {
            return OBJECT_MAPPER.readValue(json, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toJson(Object obj) {
        if (null == obj) return null;

        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .setDateFormat(new SimpleDateFormat(DEFAULT_DATE_FORMAT))
                .enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
                .enable(DeserializationFeature.USE_LONG_FOR_INTS)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    private JsonUtil() {}

}
