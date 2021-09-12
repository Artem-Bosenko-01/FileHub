package io.javaclasses.fileHub.webservices.filesystem;

import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.NotAuthorizedUserException;
import io.javaclasses.fileHub.services.files.DeleteFolder;
import io.javaclasses.fileHub.services.files.DeleteFolderCommand;
import io.javaclasses.fileHub.services.files.FolderNotFoundException;
import io.javaclasses.fileHub.webservices.ResponseMessage;
import io.javaclasses.fileHub.webservices.RequestParser;
import spark.Request;
import spark.Response;
import spark.Route;

import static com.google.common.base.Preconditions.checkNotNull;
import static javax.servlet.http.HttpServletResponse.*;

public class DeleteFolderRoute implements Route {

    private final DeleteFolder deleteFolder;

    public DeleteFolderRoute(DeleteFolder deleteFolder) {

        this.deleteFolder = checkNotNull(deleteFolder);
    }

    @Override
    public Object handle(Request request, Response response) {

        RequestParser parser = new RequestParser(request);

        String token = parser.getToken();

        String folderId = parser.getId();

        try {

            DeleteFolderCommand deleteFolderCommand = new DeleteFolderCommand(new AuthToken(token), folderId);

            String deletedFolderId = deleteFolder.handle(deleteFolderCommand);

            return new ResponseMessage("Folder with id: " + deletedFolderId + " was successfully deleted").serialize();

        } catch (NotAuthorizedUserException e) {

            response.status(SC_UNAUTHORIZED);
            return new ResponseMessage(e.getMessage()).serialize();

        } catch (FolderNotFoundException e) {

            response.status(SC_BAD_REQUEST);
            return new ResponseMessage(e.getMessage()).serialize();

        } catch (Exception e) {

            response.status(SC_INTERNAL_SERVER_ERROR);

            return new ResponseMessage("Internal server error.").serialize();
        }
    }
}
