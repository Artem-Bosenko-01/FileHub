package io.javaclasses.fileHub.webservices;

import io.javaclasses.fileHub.services.ProcessesFactory;
import io.javaclasses.fileHub.webservices.authentication.AuthenticationRoute;
import io.javaclasses.fileHub.webservices.registration.RegistrationRoute;

import static spark.Spark.post;

/**
 * Initializer that configure main existed paths af FileHub application.
 */
public class WebApplication {

    public static void main(String[] args) {

        ProcessesFactory adapter = new ProcessesFactory();

        post("/login", new AuthenticationRoute(adapter.authenticateUser()));
        post("/register", new RegistrationRoute(adapter.registerUser()));

    }
}
