package io.javaclasses.fileHub.webservices.registration;

import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.services.users.RegistrationUser;
import io.javaclasses.fileHub.services.users.RegistrationUserCommand;
import io.javaclasses.fileHub.webservices.Deserializer;
import io.javaclasses.fileHub.webservices.ErrorResponse;
import io.javaclasses.fileHub.webservices.InvalidInputDataForDeserializing;
import io.javaclasses.fileHub.webservices.RESPONSE_STATUS;
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

        Deserializer<RegistrationUserCommand> registrationCommandDeserializer = new Deserializer<>();

        try {

            RegistrationUserCommand command = registrationCommandDeserializer.
                    deserialize(body, RegistrationUserCommand.class);

            registration.handle(command);

            response.status(RESPONSE_STATUS.OK.code());

            return true;

        } catch (InvalidInputDataForDeserializing invalidInputDataForDeserializing) {

            response.status(RESPONSE_STATUS.BAD_REQUEST.code());

            invalidInputDataForDeserializing.printStackTrace();

            return new ErrorResponse(invalidInputDataForDeserializing.getMessage()).serialize();

        } catch (InvalidHandleCommandException e) {

            response.status(RESPONSE_STATUS.VALIDATION_ERROR.code());

            ValidationErrorResponse errorResponse = new ValidationErrorResponse();

            errorResponse.addError("email", e.getMessage());

            return errorResponse.serialize();

        } catch (Exception e) {

            response.status(RESPONSE_STATUS.SERVER_ERROR.code());

            return new ErrorResponse("Internal server error.").serialize();
        }
    }
}
