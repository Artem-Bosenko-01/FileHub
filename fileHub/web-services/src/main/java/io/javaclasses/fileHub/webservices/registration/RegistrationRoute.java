package io.javaclasses.fileHub.webservices.registration;

import com.google.gson.JsonObject;
import io.javaclasses.fileHub.services.ValidationCommandDataException;
import io.javaclasses.fileHub.services.users.DuplicatedUserException;
import io.javaclasses.fileHub.services.users.RegisterUser;
import io.javaclasses.fileHub.services.users.RegistrationUserCommand;
import io.javaclasses.fileHub.webservices.ErrorResponse;
import io.javaclasses.fileHub.webservices.InvalidParsingToJsonObject;
import io.javaclasses.fileHub.webservices.Parser;
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

        try {

            JsonObject jsonObject = Parser.parse(body);

            String login = jsonObject.get("loginName").getAsString();
            String password = jsonObject.get("password").getAsString();


            RegistrationUserCommand command = new RegistrationUserCommand(login, password);

            registration.handle(command);

            response.status(SC_OK);

            return "User was successfully registered";

        } catch (InvalidParsingToJsonObject invalidParsingToJsonObject) {

            response.status(SC_BAD_REQUEST);

            return new ErrorResponse(invalidParsingToJsonObject.getMessage()).serialize();

        } catch (DuplicatedUserException e) {

            response.status(422);

            ValidationErrorResponse errorResponse = new ValidationErrorResponse();

            errorResponse.addError(e.field(), e.message());

            return errorResponse.serialize();

        } catch (ValidationCommandDataException e) {

            ValidationErrorResponse errorResponse = new ValidationErrorResponse();

            errorResponse.addErrors(e.errors());

            return errorResponse.serialize();

        } catch (Exception e) {

            response.status(SC_INTERNAL_SERVER_ERROR);

            return new ErrorResponse("Internal server error.").serialize();
        }
    }
}
