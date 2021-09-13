package io.javaclasses.fileHub.webservices.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.services.files.UploadFile;
import io.javaclasses.fileHub.webservices.AuthenticationStorageBaseStub;
import io.javaclasses.fileHub.webservices.WebApplication;
import io.javaclasses.fileHub.webservices.filesystem.UploadFileRoute;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UploadFileRouteTest {

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
    public void shouldCheckNullPointerSafetyOnConstructor() throws NoSuchMethodException {

        AuthenticationStorageBaseStub authenticationStorage = new AuthenticationStorageBaseStub();
        FileContentStorageBaseStub fileContentStorage = new FileContentStorageBaseStub();
        FileStorageBaseStub fileStorage = new FileStorageBaseStub();
        FolderStorageBaseStub folderStorage = new FolderStorageBaseStub();

        NullPointerTester tester = new NullPointerTester();
        tester.setDefault(UploadFile.class, new UploadFile(fileContentStorage, fileStorage, folderStorage, authenticationStorage));

        tester.testConstructor(UploadFileRoute.class.getConstructor(UploadFile.class));
    }

}
