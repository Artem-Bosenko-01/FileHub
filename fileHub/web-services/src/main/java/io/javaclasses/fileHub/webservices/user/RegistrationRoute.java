package io.javaclasses.fileHub.webservices.user;

import com.google.gson.JsonObject;
import io.javaclasses.fileHub.services.InvalidValidationCommandDataException;
import io.javaclasses.fileHub.services.users.DuplicatedUserException;
import io.javaclasses.fileHub.services.users.RegisterUser;
import io.javaclasses.fileHub.services.users.RegistrationUserCommand;
import io.javaclasses.fileHub.webservices.InvalidParsingToJsonObject;
import io.javaclasses.fileHub.webservices.ResponseMessage;
import spark.Request;
import spark.Response;
import spark.Route;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.javaclasses.fileHub.webservices.ParserToJsonObject.parse;
import static javax.servlet.http.HttpServletResponse.*;

/**
 * Gets {@link Request request} from client side and executes registration user by user credentials.
 * User credentials are parsed from request body.
 */
public final class RegistrationRoute implements Route {

    private final RegisterUser registration;
    private final int INVALID_ENTITY_VALIDATION = 422;

    public RegistrationRoute(RegisterUser process) {

        this.registration = checkNotNull(process);
    }

    @Override
    public Object handle(Request request, Response response) {

        String body = request.body();

        try {

            JsonObject jsonObject = parse(body);

            String login = jsonObject.get("loginName").getAsString();
            String password = jsonObject.get("password").getAsString();

            RegistrationUserCommand command = new RegistrationUserCommand(login, password);

            registration.handle(command);

            response.status(SC_OK);

            return "User was successfully registered";

        } catch (InvalidParsingToJsonObject invalidParsingToJsonObject) {

            response.status(SC_BAD_REQUEST);

            return new ResponseMessage(invalidParsingToJsonObject.getMessage()).serialize();

        } catch (DuplicatedUserException duplicatedUserException) {

            response.status(INVALID_ENTITY_VALIDATION);

            ValidationErrorResponse errorResponse = new ValidationErrorResponse();

            errorResponse.addError(duplicatedUserException.field(), duplicatedUserException.message());

            return errorResponse.serialize();

        } catch (InvalidValidationCommandDataException validationCommandDataException) {

            response.status(INVALID_ENTITY_VALIDATION);

            ValidationErrorResponse errorResponse = new ValidationErrorResponse();

            errorResponse.addErrors(validationCommandDataException.errors());

            return errorResponse.serialize();

        } catch (Exception exception) {

            response.status(SC_INTERNAL_SERVER_ERROR);

            return new ResponseMessage("Internal server error.").serialize();
        }
    }
}
