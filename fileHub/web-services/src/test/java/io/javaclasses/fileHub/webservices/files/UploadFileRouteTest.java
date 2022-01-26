package io.javaclasses.fileHub.webservices.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.services.files.UploadFile;
import io.javaclasses.fileHub.webservices.AuthenticationStorageBaseStub;
import io.javaclasses.fileHub.webservices.RequestStub;
import io.javaclasses.fileHub.webservices.ResponseStub;
import io.javaclasses.fileHub.webservices.filesystem.UploadFileRoute;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UploadFileRouteTest {

    @Test
    public void shouldCheckNullPointerSafetyOnConstructor() throws NoSuchMethodException {

        AuthenticationStorageBaseStub authenticationStorage = new AuthenticationStorageBaseStub();
        FileContentStorageBaseStub fileContentStorage = new FileContentStorageBaseStub();
        FileStorageBaseStub fileStorage = new FileStorageBaseStub();
        FolderStorageBaseStub folderStorage = new FolderStorageBaseStub();

        NullPointerTester tester = new NullPointerTester();
        tester.setDefault(UploadFile.class, new UploadFile(fileContentStorage, fileStorage, folderStorage, authenticationStorage));

        tester.testConstructor(UploadFileRoute.class.getConstructor(UploadFile.class));
    }

    @Test
    public void shouldSuccessfullyUploadFile() {

        FileStorageBaseStub fileStorage = new FileStorageBaseStub();

        FolderStorageBaseStub folderStorage = new FolderStorageBaseStub();

        FileContentStorageBaseStub contentStorage = new FileContentStorageBaseStub();

        AuthenticationStorageBaseStub authenticationStorage = new AuthenticationStorageBaseStub();

        RequestStub request = new RequestStub();

        request.setUploadedFileName("fileName");

        ResponseStub response = new ResponseStub();

        UploadFileBaseStub uploadFile = new UploadFileBaseStub(contentStorage, fileStorage, folderStorage, authenticationStorage);

        UploadFileRoute route = new UploadFileRoute(uploadFile);

        Object result = route.handle(request, response);

        assertEquals(200, response.status());

        assertEquals("{\"message\":\"File with id: fileNameid was successfully uploaded\"}", result);

    }


    @ParameterizedTest
    @ArgumentsSource(UploadFileNegativeScenariosTestData.class)
    public void shouldThrowsException(String fileName, String expectedMessage, int expectedStatusCode,
                                      UploadFile uploadFileProcess) {

        String jsonMessage = "{\"message\":\"" + expectedMessage + "\"}";

        RequestStub request = new RequestStub();

        request.setUploadedFileName(fileName);

        ResponseStub response = new ResponseStub();

        UploadFileRoute route = new UploadFileRoute(uploadFileProcess);

        Object result = route.handle(request, response);

        assertEquals(jsonMessage, result);

        assertEquals(expectedStatusCode, response.status());

    }

}
