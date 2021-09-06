package io.javaclasses.fileHub.webservices.user;

import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.NotAuthorizedUserException;
import io.javaclasses.fileHub.services.files.UsersTokenNotFoundException;
import io.javaclasses.fileHub.services.users.GetUserInfo;
import io.javaclasses.fileHub.services.users.GetUserQuery;
import io.javaclasses.fileHub.services.users.InfoAboutUserDto;
import io.javaclasses.fileHub.services.users.UserNotFoundException;
import io.javaclasses.fileHub.webservices.ErrorResponse;
import spark.Request;
import spark.Response;
import spark.Route;

import static com.google.common.base.Preconditions.checkNotNull;
import static javax.servlet.http.HttpServletResponse.*;

public class GetUserInfoRoute implements Route {

    private final GetUserInfo getUserInfo;

    public GetUserInfoRoute(GetUserInfo getUserInfo) {

        this.getUserInfo = checkNotNull(getUserInfo);
    }

    @Override
    public Object handle(Request request, Response response) {

        String token = request.headers("Authorization").split(" ")[1];

        GetUserQuery getUserQuery = new GetUserQuery(new AuthToken(token));

        try {

            InfoAboutUserDto userInfo = getUserInfo.handle(getUserQuery);

            response.status(SC_OK);

            return new GetUserSuccessfulResponse(userInfo.id().toString(), userInfo.loginName()).serialize();

        } catch (NotAuthorizedUserException e) {

            response.status(SC_UNAUTHORIZED);
            return new ErrorResponse(e.getMessage()).serialize();

        } catch (UserNotFoundException | UsersTokenNotFoundException e) {

            response.status(SC_NOT_FOUND);
            return new ErrorResponse(e.getMessage()).serialize();

        } catch (Exception e) {

            response.status(SC_INTERNAL_SERVER_ERROR);
            return new ErrorResponse("Internal server error.").serialize();
        }
    }
}
