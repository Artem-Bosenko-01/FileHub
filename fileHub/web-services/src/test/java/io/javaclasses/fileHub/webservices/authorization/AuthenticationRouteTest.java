package io.javaclasses.fileHub.webservices.authorization;

import io.javaclasses.fileHub.webservices.EntryPointOfRestApplication;
import io.javaclasses.fileHub.webservices.MockRequest;
import io.javaclasses.fileHub.webservices.TestResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import spark.Spark;

import java.util.HashMap;

class AuthenticationRouteTest {
    @BeforeAll
    public static void beforeClass() {
        EntryPointOfRestApplication.main(null);
    }

    @AfterAll
    public static void afterClass() {
        Spark.stop();
    }

    @Test
    public void shouldGetTokenAfterSuccessfullyUserAuthentication() {
        MockRequest request = new MockRequest();
        String body = "{\"loginName\": \"cas\",\"password\": \"dcsdcs\"}";
        TestResponse res = request.send("POST", "/login", body);
        HashMap<String, String> responseBody = res.json();

        Assertions.assertEquals(200, res.status);
        Assertions.assertNotNull(responseBody.get("token"));
    }

    @Test
    public void shouldGetErrorMessageAfterAuthenticationNonRegisterUser() {
        MockRequest request = new MockRequest();
        String body = "{\"loginName\": \"artem\",\"password\": \"dcsdcs\"}";
        TestResponse res = request.send("POST", "/login", body);
        HashMap<String, String> responseBody = res.json();

        Assertions.assertEquals(403, res.status);
        Assertions.assertNotNull(responseBody.get("message"));
    }

    @Test
    public void shouldGetErrorMessageAfterGettingEmptyRequestBody() {
        MockRequest request = new MockRequest();
        String body = "{}";
        TestResponse res = request.send("POST", "/login", body);
        HashMap<String, String> responseBody = res.json();

        Assertions.assertEquals(500, res.status);
        Assertions.assertNotNull(responseBody.get("message"));
    }
}