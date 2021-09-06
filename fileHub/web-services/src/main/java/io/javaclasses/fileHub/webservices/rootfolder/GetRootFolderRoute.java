package io.javaclasses.fileHub.webservices.rootfolder;

import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.NotAuthorizedUserException;
import io.javaclasses.fileHub.services.files.*;
import io.javaclasses.fileHub.webservices.ErrorResponse;
import io.javaclasses.fileHub.webservices.GetFolderSuccessfulResponse;
import spark.Request;
import spark.Response;
import spark.Route;

import static com.google.common.base.Preconditions.checkNotNull;
import static javax.servlet.http.HttpServletResponse.*;

public class GetRootFolderRoute implements Route {

    private final GetRootFolder getRootFolder;

    public GetRootFolderRoute(GetRootFolder getRootFolder) {

        this.getRootFolder = checkNotNull(getRootFolder);
    }

    @Override
    public Object handle(Request request, Response response) {

        String token = request.headers("Authorization").split(" ")[1];

        GetRootFolderQuery getRootFolderQuery = new GetRootFolderQuery(new AuthToken(token));

        try {

            GetFolderDto rootFolder = getRootFolder.handle(getRootFolderQuery);

            response.status(SC_OK);

            return new GetFolderSuccessfulResponse(rootFolder).serialize();

        } catch (RootFolderNotFoundHandlingException | UsersTokenNotFoundException e) {

            response.status(SC_NOT_FOUND);
            return new ErrorResponse(e.getMessage()).serialize();

        } catch (NotAuthorizedUserException e) {

            response.status(SC_UNAUTHORIZED);
            return new ErrorResponse(e.getMessage()).serialize();

        } catch (Exception e) {

            response.status(SC_INTERNAL_SERVER_ERROR);
            return new ErrorResponse("Internal server error.").serialize();
        }
    }
}
