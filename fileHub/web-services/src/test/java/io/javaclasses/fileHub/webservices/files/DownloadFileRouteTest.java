package io.javaclasses.fileHub.webservices.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.services.files.content.GetFileContent;
import io.javaclasses.fileHub.webservices.AuthenticationStorageBaseStub;
import io.javaclasses.fileHub.webservices.RequestStub;
import io.javaclasses.fileHub.webservices.ResponseStub;
import io.javaclasses.fileHub.webservices.WebApplication;
import io.javaclasses.fileHub.webservices.filesystem.DownloadFileRoute;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DownloadFileRouteTest {

    @Test
    public void shouldCheckNullPointerSafetyOnConstructor() throws NoSuchMethodException {

        AuthenticationStorageBaseStub authenticationStorage = new AuthenticationStorageBaseStub();
        FileContentStorageBaseStub fileContentStorage = new FileContentStorageBaseStub();

        NullPointerTester tester = new NullPointerTester();
        tester.setDefault(GetFileContent.class, new GetFileContent(fileContentStorage, authenticationStorage));

        tester.testConstructor(DownloadFileRoute.class.getConstructor(GetFileContent.class));
    }

    @Test
    public void shouldSuccessfullyGetFileContent() {

        FileContentStorageBaseStub contentStorage = new FileContentStorageBaseStub();

        AuthenticationStorageBaseStub authenticationStorage = new AuthenticationStorageBaseStub();

        RequestStub request = new RequestStub();

        ResponseStub response = new ResponseStub();

        GetFileContentBaseStub getFileContent = new GetFileContentBaseStub(contentStorage, authenticationStorage);

        DownloadFileRoute route = new DownloadFileRoute(getFileContent);

        ResponseStub result = (ResponseStub) route.handle(request, response);

        assertEquals(200, response.status());

        assertEquals(3, result.responseBody());
    }


    @ParameterizedTest
    @ArgumentsSource(DownloadFileNegativeScenariosTestData.class)
    public void shouldThrowsException(String expectedMessage, int expectedStatusCode,
                                      GetFileContent getFileContent) {

        String jsonMessage = "{\"message\":\"" + expectedMessage + "\"}";

        RequestStub request = new RequestStub();

        ResponseStub response = new ResponseStub();

        DownloadFileRoute route = new DownloadFileRoute(getFileContent);

        Object result = route.handle(request, response);

        assertEquals(jsonMessage, result);

        assertEquals(expectedStatusCode, response.status());

    }
}
