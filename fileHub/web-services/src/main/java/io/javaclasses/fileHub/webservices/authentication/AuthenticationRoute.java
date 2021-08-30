package io.javaclasses.fileHub.webservices.authentication;

import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.services.users.AuthenticateUser;
import io.javaclasses.fileHub.services.users.AuthenticationUserCommand;
import io.javaclasses.fileHub.webservices.Deserializer;
import io.javaclasses.fileHub.webservices.ErrorResponse;
import io.javaclasses.fileHub.webservices.InvalidInputDataForDeserializing;
import io.javaclasses.fileHub.webservices.RESPONSE_STATUS;
import spark.Request;
import spark.Response;
import spark.Route;

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

            response.status(RESPONSE_STATUS.OK.code());

            return successfulResponse.serialize();

        } catch (InvalidInputDataForDeserializing invalidInputDataForDeserializing) {

            response.status(RESPONSE_STATUS.BAD_REQUEST.code());

            invalidInputDataForDeserializing.printStackTrace();

            return new ErrorResponse(invalidInputDataForDeserializing.getMessage()).serialize();

        } catch (InvalidHandleCommandException e) {

            response.status(RESPONSE_STATUS.UNAUTHORIZED.code());

            return new ErrorResponse(e.getMessage()).serialize();

        } catch (Exception e) {

            response.status(RESPONSE_STATUS.SERVER_ERROR.code());

            return new ErrorResponse("Internal server error.").serialize();
        }
    }
}
