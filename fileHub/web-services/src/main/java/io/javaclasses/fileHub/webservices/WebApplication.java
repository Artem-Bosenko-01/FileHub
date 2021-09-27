package io.javaclasses.fileHub.webservices;

import io.javaclasses.fileHub.webservices.filesystem.UploadFileRoute;
import io.javaclasses.fileHub.webservices.filesystem.DownloadFileRoute;
import io.javaclasses.fileHub.webservices.filesystem.DeleteFileRoute;
import io.javaclasses.fileHub.webservices.filesystem.CreateFolderRoute;
import io.javaclasses.fileHub.webservices.filesystem.GetFolderByIdRoute;
import io.javaclasses.fileHub.webservices.filesystem.GetRootFolderRoute;
import io.javaclasses.fileHub.webservices.filesystem.UpdateFileRoute;
import io.javaclasses.fileHub.webservices.filesystem.GetFolderContentRoute;
import io.javaclasses.fileHub.webservices.filesystem.DeleteFolderRoute;
import io.javaclasses.fileHub.webservices.filesystem.UpdateFolderRoute;
import io.javaclasses.fileHub.webservices.user.AuthenticationRoute;
import io.javaclasses.fileHub.webservices.user.GetUserInfoRoute;
import io.javaclasses.fileHub.webservices.user.LogOutRoute;
import io.javaclasses.fileHub.webservices.user.RegistrationRoute;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spark.Spark;

import java.nio.file.Path;
import java.nio.file.Paths;

import static spark.Spark.*;

/**
 * Configures and starts server for FileHub application. Sets main existed paths for routing at FileHub application.
 */
public final class WebApplication {

    private static final String APPLICATION_NAME = "/FileHub/server/api";
    private static final String API_VERSION_1_0 = "/1.0";

    static {

        Path pathToJsDirectory = Paths.get("").toAbsolutePath().getParent().resolve("js-app");

        staticFiles.externalLocation(pathToJsDirectory.toString());
    }

    public static void main(String[] args) {

        new WebApplication().start();
    }

    public void start() {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);

        ServiceLocator service = applicationContext.getBean("serviceLocator", ServiceLocator.class);

        initRoutesForFilesSystem(service);

        initRoutesForUserSystem(service);
    }

    public void stop() {

        Spark.stop();
    }

    private static void initRoutesForUserSystem(ServiceLocator service) {

        post(APPLICATION_NAME + API_VERSION_1_0 + "/login", new AuthenticationRoute(service.authenticateUser()));
        post(APPLICATION_NAME + API_VERSION_1_0 + "/register", new RegistrationRoute(service.registerUser()));
        get(APPLICATION_NAME + API_VERSION_1_0 + "/user", new GetUserInfoRoute(service.getUser()));
        post(APPLICATION_NAME + API_VERSION_1_0 + "/logOut", new LogOutRoute(service.logOut()));

    }

    private static void initRoutesForFilesSystem(ServiceLocator service) {

        get(APPLICATION_NAME + API_VERSION_1_0 + "/root-folder", new GetRootFolderRoute(service.getRootFolder()));
        get(APPLICATION_NAME + API_VERSION_1_0 + "/folder/:id", new GetFolderByIdRoute(service.getFolderById()));
        get(APPLICATION_NAME + API_VERSION_1_0 + "/folder/:id/content", new GetFolderContentRoute(service.getFolderContent()));
        delete(APPLICATION_NAME + API_VERSION_1_0 + "/folder/:id", new DeleteFolderRoute(service.deleteFolder()));
        delete(APPLICATION_NAME + API_VERSION_1_0 + "/file/:id", new DeleteFileRoute(service.deleteFile()));
        post(APPLICATION_NAME + API_VERSION_1_0 + "/folder/:id/folder", new CreateFolderRoute(service.createFolder()));
        put(APPLICATION_NAME + API_VERSION_1_0 + "/folder/:id", new UpdateFolderRoute(service.updateFolder()));
        put(APPLICATION_NAME + API_VERSION_1_0 + "/file/:id", new UpdateFileRoute(service.updateFile()));
        post(APPLICATION_NAME + API_VERSION_1_0 + "/folder/:id/file", new UploadFileRoute(service.uploadFile()));
        get(APPLICATION_NAME + API_VERSION_1_0 + "/file/:id", new DownloadFileRoute(service.getFileContent()));

    }
}
