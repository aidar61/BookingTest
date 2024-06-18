package org.example.asserts;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;

import static org.testng.Assert.assertTrue;

@Slf4j
public class ApiAsserts {
    private Response response;

    public ApiAsserts(Response response) {
        this.response = response;
    }

    public static ApiAsserts assertThat(Response response) {
        return new ApiAsserts(response);
    }

    public ApiAsserts isCorrectResponseCode(int code) {
        if (this.response == null) assertTrue(false);
        Assertions.assertThat(this.response.getStatusCode())
                .withFailMessage(
                        "Response code is incorrect. Expected: %s , Actual: %s",
                        code,
                        this.response.getStatusCode()
                )
                .isEqualTo(code);
        log.info("Status code is correct: Actual {}, Expected {}", this.response.getStatusCode(), code);
        return this;
    }
}
