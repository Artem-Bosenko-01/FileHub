package io.javaclasses.fileHub.webservices;

import io.javaclasses.fileHub.services.ServiceAdapter;
import io.javaclasses.fileHub.webservices.authentication.AuthenticationRoute;
import io.javaclasses.fileHub.webservices.registration.RegistrationRoute;

import static spark.Spark.post;

/**
 * Initializer that configure main existed paths af FileHub application.
 */
public class EntryPointOfRestApplication {

    public static void main(String[] args) {

        ServiceAdapter adapter = new ServiceAdapter();

        post("/login", new AuthenticationRoute(adapter));
        post("/register", new RegistrationRoute(adapter));

    }
}
