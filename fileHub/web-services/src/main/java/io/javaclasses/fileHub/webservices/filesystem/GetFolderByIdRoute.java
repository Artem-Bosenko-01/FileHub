package io.javaclasses.fileHub.webservices.filesystem;

import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.NotAuthorizedUserException;
import io.javaclasses.fileHub.services.files.*;
import io.javaclasses.fileHub.webservices.RequestParser;
import io.javaclasses.fileHub.webservices.ResponseMessage;
import spark.Request;
import spark.Response;
import spark.Route;

import static com.google.common.base.Preconditions.checkNotNull;
import static javax.servlet.http.HttpServletResponse.*;

/**
 * Gets the {@link Request request} and fetches {@link FileSystemItemDto folder dto} by folderId.
 */
public final class GetFolderByIdRoute implements Route {

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

        } catch (FolderByIdNotFoundHandlingException | UsersTokenNotFoundException handlingException) {

            response.status(SC_NOT_FOUND);
            return new ResponseMessage(handlingException.getMessage()).serialize();

        } catch (NotAuthorizedUserException notAuthorizedUserException) {

            response.status(SC_UNAUTHORIZED);
            return new ResponseMessage(notAuthorizedUserException.getMessage()).serialize();

        } catch (Exception exception) {

            response.status(SC_INTERNAL_SERVER_ERROR);
            return new ResponseMessage("Internal server error.").serialize();
        }
    }
}
