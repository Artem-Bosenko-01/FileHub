package io.javaclasses.fileHub.webservices;

import io.javaclasses.fileHub.services.ServiceLocator;
import io.javaclasses.fileHub.webservices.authentication.AuthenticationRoute;
import io.javaclasses.fileHub.webservices.registration.RegistrationRoute;

import static spark.Spark.post;

/**
 * Initializer that configure main existed paths af FileHub application.
 */
public class WebApplication {

    public static void main(String[] args) {

        new WebApplication().start();
    }

    public void start() {

        ServiceLocator service = new ServiceLocator();

        String APPLICATION_NAME = "/FileHub/server/api";
        String API_VERSION_1_0 = "/1.0";

        post(APPLICATION_NAME + API_VERSION_1_0 + "/login", new AuthenticationRoute(service.authenticateUser()));
        post(APPLICATION_NAME + API_VERSION_1_0 + "/register", new RegistrationRoute(service.registerUser()));
    }
}
