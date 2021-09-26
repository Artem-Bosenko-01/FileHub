package io.javaclasses.fileHub.webservices.user;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.services.users.RegisterUser;
import io.javaclasses.fileHub.webservices.*;
import io.javaclasses.fileHub.webservices.files.FolderStorageBaseStub;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RegistrationRouteTest {

    @Test
    public void shouldCheckNullPointerSafetyOnConstructor() throws NoSuchMethodException {

        UserStorageBaseStub userStorageStub = new UserStorageBaseStub();

        FolderStorageBaseStub folderStorageBaseStub = new FolderStorageBaseStub();

        NullPointerTester tester = new NullPointerTester();
        tester.setDefault(RegisterUser.class, new RegisterUser(userStorageStub, folderStorageBaseStub));

        tester.testConstructor(RegistrationRoute.class.getConstructor(RegisterUser.class));
    }

    @Test
    public void shouldGetMessageAfterSuccessfullyRegistrationUser() throws IOException {

        WebApplication webApplication = new WebApplication();

        webApplication.start();

        MockRequest request = new MockRequest();

        String login = UUID.randomUUID().toString();

        String body = "{\"loginName\": \"" + login + "\",\"password\": \"sdvdds\"}";
        TestResponse res = request.send("POST", "/FileHub/server/api/1.0/register", body);
        String responseBody = res.toString();

        assertEquals(200, res.status);
        assertNotNull(responseBody);

        webApplication.stop();
    }

    @Test
    public void shouldGetErrorMessageAfterRegisterExistedUser() throws IOException {

        WebApplication webApplication = new WebApplication();

        webApplication.start();

        MockRequest request = new MockRequest();
        String body = "{\"loginName\": \"artemsdsdv\",\"password\": \"dcsdcs\"}";
        TestResponse res = request.send("POST", "/FileHub/server/api/1.0/register", body);
        HashMap<String, String> responseBody = res.json();

        assertEquals(422, res.status);
        assertNotNull(responseBody.get("errors"));

        webApplication.stop();
    }

    @Test
    public void shouldGetErrorMessageAfterGettingEmptyRequestBody() throws IOException {

        WebApplication webApplication = new WebApplication();

        webApplication.start();

        MockRequest request = new MockRequest();
        String body = "{}";
        TestResponse res = request.send("POST", "/FileHub/server/api/1.0/register", body);
        HashMap<String, String> responseBody = res.json();

        assertEquals(400, res.status);
        assertNotNull(responseBody.get("message"));

        webApplication.stop();
    }


    @Test
    public void shouldSuccessfullyRegisterUser() {

        String requestBody = "{\"loginName\":\"ascaacs\",\"password\":\"password\"}";

        UserStorageBaseStub userStorageStub = new UserStorageBaseStub();

        FolderStorageBaseStub folderStorageBaseStub = new FolderStorageBaseStub();

        RequestStub request = new RequestStub(requestBody);

        ResponseStub response = new ResponseStub();

        RegisterUserBaseStub registerUser = new RegisterUserBaseStub(userStorageStub, folderStorageBaseStub);

        RegistrationRoute route = new RegistrationRoute(registerUser);

        Object result = route.handle(request, response);

        assertEquals(200, response.status());

        assertEquals("User was successfully registered", result);
    }


    @ParameterizedTest
    @ArgumentsSource(RegisterUserNegativeScenariosTestData.class)
    public void shouldThrowsException(String requestBody, String expectedMessage, int expectedStatusCode,
                                      RegisterUser registerUser) {

        RequestStub request = new RequestStub(requestBody);

        ResponseStub response = new ResponseStub();

        RegistrationRoute route = new RegistrationRoute(registerUser);

        Object result = route.handle(request, response);

        assertEquals(expectedMessage, result);

        assertEquals(expectedStatusCode, response.status());

    }
}
