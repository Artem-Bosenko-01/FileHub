package io.javaclasses.fileHub.webservices.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.services.files.content.GetFileContent;
import io.javaclasses.fileHub.webservices.AuthenticationStorageBaseStub;
import io.javaclasses.fileHub.webservices.WebApplication;
import io.javaclasses.fileHub.webservices.filesystem.DownloadFileRoute;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DownloadFileRouteTest {

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

        NullPointerTester tester = new NullPointerTester();
        tester.setDefault(GetFileContent.class, new GetFileContent(fileContentStorage, authenticationStorage));

        tester.testConstructor(DownloadFileRoute.class.getConstructor(GetFileContent.class));
    }
}
