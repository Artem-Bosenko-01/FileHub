package io.javaclasses.fileHub.webservices.authorization;

import io.javaclasses.fileHub.webservices.*;
import io.javaclasses.fileHub.webservices.authentication.AuthenticationRoute;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AuthenticationRouteFunctionalTest {

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
    public void shouldCheckNullPointerSafetyOnConstructor() {

    }

    @Test
    public void shouldGetTokenAfterSuccessfullyUserAuthentication() throws IOException {
        MockRequest request = new MockRequest();
        String body = "{\"loginName\": \"cas\",\"password\": \"dcsdcs\"}";
        TestResponse res = request.send("POST", "/FileHub/server/api/1.0/login", body);
        HashMap<String, String> responseBody = res.json();

        assertEquals(200, res.status);
        assertNotNull(responseBody.get("token"));
    }

    @Test
    public void shouldGetErrorMessageAfterAuthenticationNonRegisterUser() throws IOException {
        MockRequest request = new MockRequest();
        String body = "{\"loginName\": \"artem\",\"password\": \"dcsdcs\"}";
        TestResponse res = request.send("POST", "/FileHub/server/api/1.0/login", body);
        HashMap<String, String> responseBody = res.json();

        assertEquals(404, res.status);
        assertNotNull(responseBody.get("message"));
    }

    @Test
    public void shouldGetErrorMessageAfterGettingEmptyRequestBody() throws IOException {
        MockRequest request = new MockRequest();
        String body = "{}";
        TestResponse res = request.send("POST", "/FileHub/server/api/1.0/login", body);
        HashMap<String, String> responseBody = res.json();

        assertEquals(400, res.status);
        assertNotNull(responseBody.get("message"));
    }


    @Test
    public void shouldSuccessfullyAuthenticateUser() {

        String requestBody = "{\"loginName\":\"ascaacs\",\"password\":\"password\"}";

        UserStorageBaseStub userStorageStub = new UserStorageBaseStub();

        AuthenticationStorageBaseStub authenticationStorageStub = new AuthenticationStorageBaseStub();

        RequestStub request = new RequestStub(requestBody);

        ResponseStub response = new ResponseStub();

        AuthenticateUserBaseStub authenticateUser = new AuthenticateUserBaseStub(userStorageStub, authenticationStorageStub);

        AuthenticationRoute route = new AuthenticationRoute(authenticateUser);

        Object result = route.handle(request, response);

        assertEquals(200, response.status());

        assertEquals("{\"token\":\"token\"}", result);
    }


    @ParameterizedTest
    @ArgumentsSource(AuthenticateUserTestData.class)
    public void shouldThrowsException(String requestBody, String expectedMessage, int expectedStatusCode,
                                      AuthenticateUserBaseStub authenticateUserStub) {

        String jsonMessage = "{\"message\":\"" + expectedMessage + "\"}";

        RequestStub request = new RequestStub(requestBody);

        ResponseStub response = new ResponseStub();

        AuthenticationRoute route = new AuthenticationRoute(authenticateUserStub);

        Object result = route.handle(request, response);

        assertEquals(jsonMessage, result);

        assertEquals(expectedStatusCode, response.status());

    }

}