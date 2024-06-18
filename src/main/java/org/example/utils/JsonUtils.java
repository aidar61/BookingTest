package org.example.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String convertToJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T deserialize(Response response, Class<T> type) {
        try {
            String jsonString = response.then()
                    .extract()
                    .body()
                    .asString();
            return objectMapper.readValue(jsonString, type);
        } catch (Exception e) {
            log.error("Can not parse object", e);
            return null;
        }
    }
}
