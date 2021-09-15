package io.javaclasses.fileHub.webservices.user;

import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.NotAuthorizedUserException;
import io.javaclasses.fileHub.services.files.UsersTokenNotFoundException;
import io.javaclasses.fileHub.services.users.LogOut;
import io.javaclasses.fileHub.services.users.LogOutCommand;
import io.javaclasses.fileHub.webservices.RequestParser;
import io.javaclasses.fileHub.webservices.ResponseMessage;
import spark.Request;
import spark.Response;
import spark.Route;

import static com.google.common.base.Preconditions.checkNotNull;
import static javax.servlet.http.HttpServletResponse.*;

/**
 * Gets {@link Request request} from client side and executes completing user active session by deleting token from storage.
 */
public class LogOutRoute implements Route {

    private final LogOut logOut;

    public LogOutRoute(LogOut logOut) {

        this.logOut = checkNotNull(logOut);
    }

    @Override
    public Object handle(Request request, Response response) {

        RequestParser parser = new RequestParser(request);

        String token = parser.getToken();

        LogOutCommand logOutCommand = new LogOutCommand(new AuthToken(token));

        try {

            String deletedToken = logOut.handle(logOutCommand);

            return "User with token: " + deletedToken + " was logged out from FileHub application.";

        } catch (NotAuthorizedUserException notAuthorizedUserException) {

            response.status(SC_UNAUTHORIZED);
            return new ResponseMessage(notAuthorizedUserException.getMessage()).serialize();

        } catch (UsersTokenNotFoundException usersTokenNotFoundException) {

            response.status(SC_BAD_REQUEST);
            return new ResponseMessage(usersTokenNotFoundException.getMessage()).serialize();

        } catch (Exception exception) {

            response.status(SC_INTERNAL_SERVER_ERROR);
            return new ResponseMessage("Internal server error.").serialize();
        }

    }
}
