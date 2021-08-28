package io.javaclasses.fileHub.webservices.authentication;

import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.services.ServiceAdapter;
import io.javaclasses.fileHub.services.users.AuthenticationUser;
import io.javaclasses.fileHub.services.users.AuthenticationUserCommand;
import io.javaclasses.fileHub.services.users.RegistrationUser;
import io.javaclasses.fileHub.webservices.ErrorResponse;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Gets request and executes authentication user in FileHub application.
 */
public class AuthenticationRoute implements Route {

    private final AuthenticationUser authentication;

    public AuthenticationRoute(AuthenticationUser process) {

        this.authentication = process;
    }

    @Override
    public Object handle(Request request, Response response) {

        String body = request.body();

        UserAuthenticationCredentials credentials = new UserAuthenticationCredentials(body);

        AuthenticationUserCommand command = credentials.deserialize();

        try {

            AuthToken token = authentication.handle(command);

            AuthenticationSuccessfulResponse successfulResponse = new AuthenticationSuccessfulResponse(token);

            response.status(200);
            return successfulResponse.serialize();

        } catch (InvalidHandleCommandException e) {

            response.status(403);

            return new ErrorResponse(e.getMessage()).serialize();

        } catch (Exception e) {

            response.status(500);

            return new ErrorResponse("Internal server error.").serialize();
        }
    }
}
