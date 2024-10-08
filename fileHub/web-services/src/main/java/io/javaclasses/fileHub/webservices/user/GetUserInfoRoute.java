package io.javaclasses.fileHub.webservices.user;

import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.NotAuthorizedUserException;
import io.javaclasses.fileHub.services.files.UsersTokenNotFoundException;
import io.javaclasses.fileHub.services.users.GetUserInfo;
import io.javaclasses.fileHub.services.users.GetUserQuery;
import io.javaclasses.fileHub.services.users.InfoAboutUserDto;
import io.javaclasses.fileHub.services.users.UserNotFoundException;
import io.javaclasses.fileHub.webservices.RequestParser;
import io.javaclasses.fileHub.webservices.ResponseMessage;
import spark.Request;
import spark.Response;
import spark.Route;

import static com.google.common.base.Preconditions.checkNotNull;
import static javax.servlet.http.HttpServletResponse.*;

/**
 * Gets {@link Request request} from client side and executes getting user data by his token.
 */
public final class GetUserInfoRoute implements Route {

    private final GetUserInfo getUserInfo;

    public GetUserInfoRoute(GetUserInfo getUserInfo) {

        this.getUserInfo = checkNotNull(getUserInfo);
    }

    @Override
    public Object handle(Request request, Response response) {

        RequestParser parser = new RequestParser(request);

        String token = parser.getToken();

        GetUserQuery getUserQuery = new GetUserQuery(new AuthToken(token));

        try {

            InfoAboutUserDto userInfo = getUserInfo.handle(getUserQuery);

            response.status(SC_OK);

            return new GetUserSuccessfulResponse(userInfo.id().toString(), userInfo.loginName()).serialize();

        } catch (NotAuthorizedUserException notAuthorizedUserException) {

            response.status(SC_UNAUTHORIZED);
            return new ResponseMessage(notAuthorizedUserException.getMessage()).serialize();

        } catch (UserNotFoundException | UsersTokenNotFoundException commandHandlingException) {

            response.status(SC_NOT_FOUND);
            return new ResponseMessage(commandHandlingException.getMessage()).serialize();

        } catch (Exception exception) {

            response.status(SC_INTERNAL_SERVER_ERROR);
            return new ResponseMessage("Internal server error.").serialize();
        }
    }
}
