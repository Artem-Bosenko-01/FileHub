package io.javaclasses.fileHub.webservices.authorization;

import io.javaclasses.fileHub.webservices.MockRequest;
import io.javaclasses.fileHub.webservices.TestResponse;
import io.javaclasses.fileHub.webservices.WebApplication;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class AuthenticationRouteTest {

    private static final WebApplication application = new WebApplication();

    @BeforeAll
    public static void beforeClass() {

        application.start();
    }

    @AfterAll
    public static void afterClass() {

        application.stop();
    }

    @Test
    public void shouldGetTokenAfterSuccessfullyUserAuthentication() {
        MockRequest request = new MockRequest();
        String body = "{\"loginName\": \"cas\",\"password\": \"dcsdcs\"}";
        TestResponse res = request.send("POST", "/FileHub/server/api/1.0/login", body);
        HashMap<String, String> responseBody = res.json();

        Assertions.assertEquals(200, res.status);
        Assertions.assertNotNull(responseBody.get("token"));
    }

    @Test
    public void shouldGetErrorMessageAfterAuthenticationNonRegisterUser() {
        MockRequest request = new MockRequest();
        String body = "{\"loginName\": \"artem\",\"password\": \"dcsdcs\"}";
        TestResponse res = request.send("POST", "/FileHub/server/api/1.0/login", body);
        HashMap<String, String> responseBody = res.json();

        Assertions.assertEquals(404, res.status);
        Assertions.assertNotNull(responseBody.get("message"));
    }

    @Test
    public void shouldGetErrorMessageAfterGettingEmptyRequestBody() {
        MockRequest request = new MockRequest();
        String body = "{}";
        TestResponse res = request.send("POST", "/FileHub/server/api/1.0/login", body);
        HashMap<String, String> responseBody = res.json();

        Assertions.assertEquals(400, res.status);
        Assertions.assertNotNull(responseBody.get("message"));
    }
}