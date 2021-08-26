package io.javaclasses.fileHub.webservices;

import io.javaclasses.fileHub.services.ServiceAdapter;
import io.javaclasses.fileHub.webservices.authorization.AuthorizationRoute;

import static spark.Spark.post;

public class EntryPointOfRestApplication {

    public static void main(String[] args) {

        ServiceAdapter adapter = new ServiceAdapter();

        post("/login", new AuthorizationRoute(adapter));

    }
}
