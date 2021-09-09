package io.javaclasses.fileHub.webservices.filesystem;

import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.NotAuthorizedUserException;
import io.javaclasses.fileHub.services.files.UsersTokenNotFoundException;
import io.javaclasses.fileHub.services.files.content.GetFolderContent;
import io.javaclasses.fileHub.services.files.content.GetFolderContentDTO;
import io.javaclasses.fileHub.services.files.content.GetFolderContentQuery;
import io.javaclasses.fileHub.services.files.content.InvalidFolderContentGetting;
import io.javaclasses.fileHub.webservices.ResponseMessage;
import io.javaclasses.fileHub.webservices.RequestParser;
import spark.Request;
import spark.Response;
import spark.Route;

import static javax.servlet.http.HttpServletResponse.*;

public class GetFolderContentRoute implements Route {

    private final GetFolderContent getFolderContent;

    public GetFolderContentRoute(GetFolderContent getFolderContent) {

        this.getFolderContent = getFolderContent;
    }

    @Override
    public Object handle(Request request, Response response) {

        RequestParser parser = new RequestParser(request);

        String token = parser.getToken();

        String folderId = parser.getId();

        GetFolderContentQuery getFolderContentQuery = new GetFolderContentQuery(new AuthToken(token), folderId);

        try {

            GetFolderContentDTO folderContent = getFolderContent.handle(getFolderContentQuery);

            response.status(SC_OK);

            return new GetFolderContentSuccessfulResponse(folderContent.content()).serialize();

        } catch (InvalidFolderContentGetting | UsersTokenNotFoundException e) {

            response.status(SC_NOT_FOUND);
            return new ResponseMessage(e.getMessage()).serialize();

        } catch (NotAuthorizedUserException e) {

            response.status(SC_UNAUTHORIZED);
            return new ResponseMessage(e.getMessage()).serialize();

        } catch (Exception e) {

            response.status(SC_INTERNAL_SERVER_ERROR);
            return new ResponseMessage("Internal server error.").serialize();
        }
    }

}
