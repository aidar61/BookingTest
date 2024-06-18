package org.example.application;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import static org.apache.http.HttpHeaders.*;
import static org.example.config.AppConfig.BASE_URL;

@Slf4j
public class BookingApplication {
    public static final String BOOKING = "booking";
    public static final Map<String, String> HEADERS_BASE_CONTROLLER = new HashMap<>() {{
        put(CONTENT_TYPE, "application/json");
        put(ACCEPT, "application/json");
        put(HOST, getDomainName(BASE_URL));
    }};

    private static String getDomainName(String url) {
        String s = url.split("//")[1];
        String spl = s.split("/")[0];
        log.info("Host is: {}", spl);
        return spl;
    }
}
