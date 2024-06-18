package org.example.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Auth;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.example.application.BookingApplication.HEADERS_BASE_CONTROLLER;
import static org.example.config.AppConfig.BASE_URL;
import static org.example.utils.EntityManager.generateAuth;

/**
 * @author Aidar Askeev
 */
@Getter
@Slf4j
public abstract class HttpStructure {
    public static final String SLASH = "/";
    public String token;
    protected String url;
    protected Map<String, String> headers;
    protected RequestSpecification spec;
    protected Response response;


    public HttpStructure(String url) {
        this.url = url;
        this.token = extractCode();
        log.info("Token: {}", token);
        this.headers = HEADERS_BASE_CONTROLLER;
        this.spec = new RequestSpecBuilder()
                .setBaseUri(this.url)
                .addHeaders(this.headers)
                .build();
    }

    private String extractCode() {
        Auth auth = generateAuth();
        return given()
                .baseUri(BASE_URL)
                .headers(HEADERS_BASE_CONTROLLER)
                .body(auth.toJson())
                .post("auth")
                .as(Auth.class)
                .getToken();
    }

    public void logResponse() {
        log.warn("Response is:");
        log.warn(getResponse().getBody().asString());
        log.warn(String.valueOf(getResponse().getStatusCode()));
    }

    public static String getEndpoint(String... args) {
        StringBuilder endpoint = new StringBuilder();
        for (String arg : args)
            endpoint.append(arg).append(SLASH);
        return endpoint.substring(0, endpoint.length() - 1);
    }

    public String formatParameter(Map<String, String> parameters) {
        StringBuilder query = new StringBuilder("?");
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            query.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        return query.deleteCharAt(query.length() - 1).toString();
    }

    public Response get(String endPoint) {
        log.info("performed GET {}", endPoint);
        this.response = given()
                .spec(spec)
                .get(endPoint);
        logResponse();
        return this.response;
    }

    public Response post(String endpoint, String body) {
        log.info("performed POST {}", endpoint);
        log.info("Body is {}", body);
        this.response = given()
                .spec(spec)
                .body(body)
                .post(endpoint);
        logResponse();
        return this.response;
    }

}
