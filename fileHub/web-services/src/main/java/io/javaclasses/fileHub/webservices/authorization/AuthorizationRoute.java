package io.javaclasses.fileHub.webservices.authorization;

import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.services.ServiceAdapter;
import io.javaclasses.fileHub.services.users.AuthenticationUserCommand;
import io.javaclasses.fileHub.webservices.ResponseError;
import spark.Request;
import spark.Response;
import spark.Route;

public class AuthorizationRoute implements Route {

    private final ServiceAdapter adapter;

    public AuthorizationRoute(ServiceAdapter adapter) {

        this.adapter = adapter;
    }

    @Override
    public Object handle(Request request, Response response) {

        String body = request.body();

        UserCredentials credentials = new UserCredentials(body);

        AuthenticationUserCommand command = credentials.deserialize();

        try {

            AuthToken token = adapter.authenticateUser().handle(command);

            AuthenticationSuccessfulResponse successfulResponse = new AuthenticationSuccessfulResponse(token);

            response.status(200);
            return successfulResponse.serialize();

        } catch (InvalidHandleCommandException e) {

            response.status(403);

            return new ResponseError(e.getMessage()).serialize();

        } catch (Exception e) {

            response.status(500);

            return new ResponseError(e.getMessage()).serialize();
        }
    }
}
