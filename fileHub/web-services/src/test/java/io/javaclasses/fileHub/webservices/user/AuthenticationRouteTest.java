package io.javaclasses.fileHub.webservices.user;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.services.users.AuthenticateUser;
import io.javaclasses.fileHub.webservices.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AuthenticationRouteTest {

    @Test
    public void shouldCheckNullPointerSafetyOnConstructor() throws NoSuchMethodException {

        UserStorageBaseStub userStorageStub = new UserStorageBaseStub();

        AuthenticationStorageBaseStub authenticationStorageStub = new AuthenticationStorageBaseStub();

        NullPointerTester tester = new NullPointerTester();
        tester.setDefault(AuthenticateUser.class, new AuthenticateUser(userStorageStub, authenticationStorageStub));

        tester.testConstructor(AuthenticationRoute.class.getConstructor(AuthenticateUser.class));
    }

    @Test
    public void shouldGetTokenAfterSuccessfullyUserAuthentication() throws IOException {

        WebApplication webApplication = new WebApplication();

        webApplication.start();

        MockRequest request = new MockRequest();
        String body = "{\"loginName\": \"artrms@kasc.com\",\"password\": \"123456\"}";
        TestResponse res = request.send("POST", "/FileHub/server/api/1.0/login", body);
        HashMap<String, String> responseBody = res.json();

        assertEquals(200, res.status);
        assertNotNull(responseBody.get("token"));

        webApplication.stop();
    }

    @Test
    public void shouldGetErrorMessageAfterAuthenticationNonRegisterUser() throws IOException {

        WebApplication webApplication = new WebApplication();

        webApplication.start();

        MockRequest request = new MockRequest();

        String login = UUID.randomUUID().toString();

        String body = "{\"loginName\": \"" + login + "\",\"password\": \"sdvdds\"}";
        TestResponse res = request.send("POST", "/FileHub/server/api/1.0/login", body);
        HashMap<String, String> responseBody = res.json();

        assertEquals(404, res.status);
        assertNotNull(responseBody.get("message"));

        webApplication.stop();
    }

    @Test
    public void shouldGetErrorMessageAfterGettingEmptyRequestBody() throws IOException {

        WebApplication webApplication = new WebApplication();

        webApplication.start();

        MockRequest request = new MockRequest();
        String body = "{}";
        TestResponse res = request.send("POST", "/FileHub/server/api/1.0/login", body);
        HashMap<String, String> responseBody = res.json();

        assertEquals(400, res.status);
        assertNotNull(responseBody.get("message"));

        webApplication.stop();
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
    @ArgumentsSource(AuthenticateUserNegativeScenariosTestData.class)
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