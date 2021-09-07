package io.javaclasses.fileHub.webservices.filesystem;

import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.NotAuthorizedUserException;
import io.javaclasses.fileHub.services.files.*;
import io.javaclasses.fileHub.webservices.ErrorResponse;
import io.javaclasses.fileHub.webservices.GetFolderSuccessfulResponse;
import io.javaclasses.fileHub.webservices.RequestParser;
import spark.Request;
import spark.Response;
import spark.Route;

import static com.google.common.base.Preconditions.checkNotNull;
import static javax.servlet.http.HttpServletResponse.*;

public class GetFolderByIdRoute implements Route {

    private final GetFolderById getFolderById;

    public GetFolderByIdRoute(GetFolderById getFolderById) {

        this.getFolderById = checkNotNull(getFolderById);
    }

    @Override
    public Object handle(Request request, Response response) {

        RequestParser parser = new RequestParser(request);

        String token = parser.getToken();

        String folderId = parser.getId();

        GetFolderByIdQuery getFolderByIdQuery = new GetFolderByIdQuery(new AuthToken(token), folderId);

        try {

            FileSystemItemDto rootFolder = getFolderById.handle(getFolderByIdQuery);

            response.status(SC_OK);

            return new GetFolderSuccessfulResponse(rootFolder).serialize();

        } catch (FolderByIdNotFoundHandlingException | UsersTokenNotFoundException e) {

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
