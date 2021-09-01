package io.javaclasses.fileHub.webservices.authentication;

import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.users.AuthenticateUser;
import io.javaclasses.fileHub.services.users.AuthenticationUserCommand;
import io.javaclasses.fileHub.services.users.DuplicatedUserException;
import io.javaclasses.fileHub.services.users.UserNotFoundException;
import io.javaclasses.fileHub.webservices.Deserializer;
import io.javaclasses.fileHub.webservices.ErrorResponse;
import io.javaclasses.fileHub.webservices.InvalidDeserialization;
import spark.Request;
import spark.Response;
import spark.Route;

import static javax.servlet.http.HttpServletResponse.*;

/**
 * Gets request and executes authentication user in FileHub application.
 */
public class AuthenticationRoute implements Route {

    private final AuthenticateUser authentication;

    public AuthenticationRoute(AuthenticateUser process) {

        this.authentication = process;
    }

    @Override
    public Object handle(Request request, Response response) {

        String body = request.body();

        Deserializer<AuthenticationUserCommand> authenticationCommandDeserializer = new Deserializer<>();

        try {

            AuthenticationUserCommand command = authenticationCommandDeserializer.
                    deserialize(body, AuthenticationUserCommand.class);

            AuthToken token = authentication.handle(command);

            AuthenticationSuccessfulResponse successfulResponse = new AuthenticationSuccessfulResponse(token);

            response.status(SC_OK);

            return successfulResponse.serialize();

        } catch (InvalidDeserialization invalidDeserialization) {

            response.status(SC_BAD_REQUEST);

            return new ErrorResponse(invalidDeserialization.getMessage()).serialize();

        } catch (UserNotFoundException e) {

            response.status(SC_NOT_FOUND);

            return new ErrorResponse(e.getMessage()).serialize();

        } catch (DuplicatedUserException e) {

            response.status(SC_CONFLICT);

            return new ErrorResponse(e.message()).serialize();

        } catch (Exception e) {

            response.status(SC_INTERNAL_SERVER_ERROR);

            return new ErrorResponse("Internal server error.").serialize();
        }
    }
}
