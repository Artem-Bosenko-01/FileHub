package io.javaclasses.fileHub.webservices.filesystem;

import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.NotAuthorizedUserException;
import io.javaclasses.fileHub.services.files.DeleteFile;
import io.javaclasses.fileHub.services.files.DeleteFileCommand;
import io.javaclasses.fileHub.services.files.FileNotFoundException;
import io.javaclasses.fileHub.webservices.ResponseMessage;
import io.javaclasses.fileHub.webservices.RequestParser;
import spark.Request;
import spark.Response;
import spark.Route;

import static com.google.common.base.Preconditions.checkNotNull;
import static javax.servlet.http.HttpServletResponse.*;

public class DeleteFileRoute implements Route {

    private final DeleteFile deleteFile;

    public DeleteFileRoute(DeleteFile deleteFile) {

        this.deleteFile = checkNotNull(deleteFile);
    }

    @Override
    public Object handle(Request request, Response response) {

        RequestParser parser = new RequestParser(request);

        String token = parser.getToken();

        String fileId = parser.getId();

        try {

            DeleteFileCommand deleteFileCommand = new DeleteFileCommand(new AuthToken(token), fileId);

            String deletedFileId = deleteFile.handle(deleteFileCommand);

            return "File with id: " + deletedFileId + " was successfully deleted";

        } catch (NotAuthorizedUserException e) {

            response.status(SC_UNAUTHORIZED);
            return new ResponseMessage(e.getMessage()).serialize();

        } catch (FileNotFoundException e) {

            response.status(SC_BAD_REQUEST);
            return new ResponseMessage(e.getMessage()).serialize();

        } catch (Exception e) {

            response.status(SC_INTERNAL_SERVER_ERROR);

            return new ResponseMessage("Internal server error.").serialize();
        }
    }
}
