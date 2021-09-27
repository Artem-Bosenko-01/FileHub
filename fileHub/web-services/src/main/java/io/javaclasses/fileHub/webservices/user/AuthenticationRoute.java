package io.javaclasses.fileHub.webservices.user;

import com.google.gson.JsonObject;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidValidationCommandDataException;
import io.javaclasses.fileHub.services.users.AuthenticateUser;
import io.javaclasses.fileHub.services.users.AuthenticationUserCommand;
import io.javaclasses.fileHub.services.users.DuplicatedFieldValueException;
import io.javaclasses.fileHub.services.users.UserNotFoundException;
import io.javaclasses.fileHub.webservices.InvalidParsingToJsonObject;
import io.javaclasses.fileHub.webservices.ResponseMessage;
import spark.Request;
import spark.Response;
import spark.Route;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.javaclasses.fileHub.webservices.ParserToJsonObject.parse;
import static javax.servlet.http.HttpServletResponse.*;

/**
 * Gets {@link Request request} from client side and executes authentication user in the FileHub application.
 */
public final class AuthenticationRoute implements Route {

    private final AuthenticateUser authentication;
    private final int INVALID_ENTITY_VALIDATION = 422;

    public AuthenticationRoute(AuthenticateUser process) {

        this.authentication = checkNotNull(process);
    }

    @Override
    public Object handle(Request request, Response response) {

        String body = request.body();

        try {

            JsonObject jsonObject = parse(body);

            String login = jsonObject.get("loginName").getAsString();
            String password = jsonObject.get("password").getAsString();

            AuthenticationUserCommand command = new AuthenticationUserCommand(login, password);

            AuthToken token = authentication.handle(command);

            AuthenticationSuccessfulResponse successfulResponse = new AuthenticationSuccessfulResponse(token);

            response.status(SC_OK);

            return successfulResponse.serialize();

        } catch (InvalidParsingToJsonObject invalidParsingToJsonObject) {

            response.status(SC_BAD_REQUEST);

            return new ResponseMessage(invalidParsingToJsonObject.getMessage()).serialize();

        } catch (UserNotFoundException userNotFoundException) {

            response.status(SC_NOT_FOUND);

            return new ResponseMessage(userNotFoundException.getMessage()).serialize();

        } catch (DuplicatedFieldValueException duplicatedFieldValueException) {

            response.status(SC_CONFLICT);

            return new ResponseMessage(duplicatedFieldValueException.message()).serialize();

        } catch (InvalidValidationCommandDataException invalidValidationCommandDataException) {

            response.status(INVALID_ENTITY_VALIDATION);

            return new ResponseMessage("Error: Invalid user credentials.").serialize();

        } catch (Exception exception) {

            response.status(SC_INTERNAL_SERVER_ERROR);

            return new ResponseMessage("Internal server error.").serialize();
        }
    }
}
