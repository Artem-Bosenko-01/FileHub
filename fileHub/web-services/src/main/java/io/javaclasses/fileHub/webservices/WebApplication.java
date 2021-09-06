package io.javaclasses.fileHub.webservices;

import io.javaclasses.fileHub.services.ServiceLocator;
import io.javaclasses.fileHub.webservices.authentication.AuthenticationRoute;
import io.javaclasses.fileHub.webservices.folder.GetFolderByIdRoute;
import io.javaclasses.fileHub.webservices.registration.RegistrationRoute;
import io.javaclasses.fileHub.webservices.rootfolder.GetRootFolderRoute;
import spark.Spark;

import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Configures and starts server for FileHub application. Sets main existed paths for routing at FileHub application.
 */
public final class WebApplication {

    public static void main(String[] args) {

        new WebApplication().start();
    }

    public void start() {

        ServiceLocator service = new ServiceLocator();

        String APPLICATION_NAME = "/FileHub/server/api";
        String API_VERSION_1_0 = "/1.0";

        post(APPLICATION_NAME + API_VERSION_1_0 + "/login", new AuthenticationRoute(service.authenticateUser()));
        post(APPLICATION_NAME + API_VERSION_1_0 + "/register", new RegistrationRoute(service.registerUser()));
        get(APPLICATION_NAME + API_VERSION_1_0 + "/root-folder", new GetRootFolderRoute(service.getRootFolder()));
        get(APPLICATION_NAME + API_VERSION_1_0 + "/folder/:id", new GetFolderByIdRoute(service.getFolderById()));
    }

    public void stop() {

        Spark.stop();
    }
}
