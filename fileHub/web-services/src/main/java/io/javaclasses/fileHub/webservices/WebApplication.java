package io.javaclasses.fileHub.webservices;

import io.javaclasses.fileHub.services.ServiceLocator;
import io.javaclasses.fileHub.webservices.filesystem.*;
import io.javaclasses.fileHub.webservices.user.AuthenticationRoute;
import io.javaclasses.fileHub.webservices.user.GetUserInfoRoute;
import io.javaclasses.fileHub.webservices.user.RegistrationRoute;
import spark.Spark;

import static spark.Spark.*;

/**
 * Configures and starts server for FileHub application. Sets main existed paths for routing at FileHub application.
 */
public final class WebApplication {

    public static void main(String[] args) {

        new WebApplication().start();
    }

    public void start() {

        staticFiles.externalLocation("js-app");

        ServiceLocator service = new ServiceLocator();

        String APPLICATION_NAME = "/FileHub/server/api";
        String API_VERSION_1_0 = "/1.0";

        post(APPLICATION_NAME + API_VERSION_1_0 + "/login", new AuthenticationRoute(service.authenticateUser()));
        post(APPLICATION_NAME + API_VERSION_1_0 + "/register", new RegistrationRoute(service.registerUser()));
        get(APPLICATION_NAME + API_VERSION_1_0 + "/root-folder", new GetRootFolderRoute(service.getRootFolder()));
        get(APPLICATION_NAME + API_VERSION_1_0 + "/folder/:id", new GetFolderByIdRoute(service.getFolderById()));
        get(APPLICATION_NAME + API_VERSION_1_0 + "/user", new GetUserInfoRoute(service.getUser()));
        get(APPLICATION_NAME + API_VERSION_1_0 + "/folder/:id/content", new GetFolderContentRoute(service.getFolderContent()));
        delete(APPLICATION_NAME + API_VERSION_1_0 + "/folder/:id", new DeleteFolderRoute(service.deleteFolder()));
        delete(APPLICATION_NAME + API_VERSION_1_0 + "/file/:id", new DeleteFileRoute(service.deleteFile()));
        post(APPLICATION_NAME + API_VERSION_1_0 + "/folder/:id/folder", new CreateFolderRoute(service.createFolder()));
    }

    public void stop() {

        Spark.stop();
    }
}
