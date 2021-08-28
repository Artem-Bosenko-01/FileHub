package io.javaclasses.fileHub.webservices.registration;

import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.services.users.RegistrationUser;
import io.javaclasses.fileHub.services.users.RegistrationUserCommand;
import io.javaclasses.fileHub.webservices.ErrorResponse;
import spark.Request;
import spark.Response;
import spark.Route;

public class RegistrationRoute implements Route {

    private final RegistrationUser registration;

    public RegistrationRoute(RegistrationUser process) {

        this.registration = process;
    }

    @Override
    public Object handle(Request request, Response response) {

        String body = request.body();

        UserRegistrationCredentials credentials = new UserRegistrationCredentials(body);

        RegistrationUserCommand command = credentials.deserialize();

        try {

            registration.handle(command);

            response.status(200);
            return true;

        } catch (InvalidHandleCommandException e) {

            response.status(422);
            ValidationErrorResponse errorResponse = new ValidationErrorResponse();
            errorResponse.addError("email", e.getMessage());
            return errorResponse.serialize();

        } catch (Exception e) {

            response.status(500);
            return new ErrorResponse("Internal server error.").serialize();
        }
    }
}
