package io.javaclasses.fileHub.webservices.registration;

import io.javaclasses.fileHub.services.users.DuplicatedUserException;
import io.javaclasses.fileHub.services.users.RegisterUser;
import io.javaclasses.fileHub.services.users.RegistrationUserCommand;
import io.javaclasses.fileHub.webservices.Deserializer;
import io.javaclasses.fileHub.webservices.ErrorResponse;
import io.javaclasses.fileHub.webservices.InvalidDeserialization;
import spark.Request;
import spark.Response;
import spark.Route;

import static javax.servlet.http.HttpServletResponse.*;

public class RegistrationRoute implements Route {

    private final RegisterUser registration;

    public RegistrationRoute(RegisterUser process) {

        this.registration = process;
    }

    @Override
    public Object handle(Request request, Response response) {

        String body = request.body();

        Deserializer<RegistrationUserCommand> registrationCommandDeserializer = new Deserializer<>();

        try {

            RegistrationUserCommand command = registrationCommandDeserializer.
                    deserialize(body, RegistrationUserCommand.class);

            registration.handle(command);

            response.status(SC_OK);

            return "User was successfully registered";

        } catch (InvalidDeserialization invalidDeserialization) {

            response.status(SC_BAD_REQUEST);

            return new ErrorResponse(invalidDeserialization.getMessage()).serialize();

        } catch (DuplicatedUserException e) {

            response.status(422);

            ValidationErrorResponse errorResponse = new ValidationErrorResponse();

            errorResponse.addError(e.field(), e.message());

            return errorResponse.serialize();

        } catch (Exception e) {

            response.status(SC_INTERNAL_SERVER_ERROR);

            return new ErrorResponse("Internal server error.").serialize();
        }
    }
}
