package io.javaclasses.fileHub.webservices.authentication;

import com.google.gson.JsonObject;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.ValidationCommandDataException;
import io.javaclasses.fileHub.services.users.AuthenticateUser;
import io.javaclasses.fileHub.services.users.AuthenticationUserCommand;
import io.javaclasses.fileHub.services.users.DuplicatedUserException;
import io.javaclasses.fileHub.services.users.UserNotFoundException;
import io.javaclasses.fileHub.webservices.ErrorResponse;
import io.javaclasses.fileHub.webservices.InvalidParsingToJsonObject;
import io.javaclasses.fileHub.webservices.Parser;
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

        try {

            JsonObject jsonObject = Parser.parse(body);

            String login = jsonObject.get("loginName").getAsString();
            String password = jsonObject.get("password").getAsString();


            AuthenticationUserCommand command = new AuthenticationUserCommand(login, password);

            AuthToken token = authentication.handle(command);

            AuthenticationSuccessfulResponse successfulResponse = new AuthenticationSuccessfulResponse(token);

            response.status(SC_OK);

            return successfulResponse.serialize();

        } catch (InvalidParsingToJsonObject invalidParsingToJsonObject) {

            response.status(SC_BAD_REQUEST);

            return new ErrorResponse(invalidParsingToJsonObject.getMessage()).serialize();

        } catch (UserNotFoundException e) {

            response.status(SC_NOT_FOUND);

            return new ErrorResponse(e.getMessage()).serialize();

        } catch (DuplicatedUserException e) {

            response.status(SC_CONFLICT);

            return new ErrorResponse(e.message()).serialize();

        } catch (ValidationCommandDataException e) {

            response.status(422);

            return new ErrorResponse(e.getMessage()).serialize();

        } catch (Exception e) {

            response.status(SC_INTERNAL_SERVER_ERROR);

            return new ErrorResponse("Internal server error.").serialize();
        }
    }
}
