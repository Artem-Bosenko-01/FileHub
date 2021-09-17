package io.javaclasses.fileHub.webservices.filesystem;

import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.NotAuthorizedUserException;
import io.javaclasses.fileHub.services.files.content.GetFileContent;
import io.javaclasses.fileHub.services.files.content.GetFileContentDTO;
import io.javaclasses.fileHub.services.files.content.GetFileContentQuery;
import io.javaclasses.fileHub.webservices.RequestParser;
import io.javaclasses.fileHub.webservices.ResponseMessage;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.servlet.ServletOutputStream;

import static com.google.common.base.Preconditions.checkNotNull;
import static javax.servlet.http.HttpServletResponse.*;

/**
 * Gets the {@link Request request} and fetches {@link GetFileContentDTO fileContent} by fileId.
 * After that it writes fileContent to {@link Response response} as binary data.
 */
public final class DownloadFileRoute implements Route {

    private final GetFileContent downloadFile;

    public DownloadFileRoute(GetFileContent downloadFile) {

        this.downloadFile = checkNotNull(downloadFile);
    }

    @Override
    public Object handle(Request request, Response response) {

        RequestParser parser = new RequestParser(request);

        String token = parser.getToken();

        String fileId = parser.getId();

        GetFileContentQuery getFileContentQuery = new GetFileContentQuery(new AuthToken(token), fileId);

        try {

            GetFileContentDTO fileContent = downloadFile.handle(getFileContentQuery);

            try (ServletOutputStream outputStream = response.raw().getOutputStream();) {
                outputStream.write(fileContent.content());
            }

            response.status(SC_OK);
            return response;

        } catch (NotAuthorizedUserException notAuthorizedUserException) {

            response.status(SC_UNAUTHORIZED);
            return new ResponseMessage(notAuthorizedUserException.getMessage()).serialize();

        } catch (InvalidCommandHandlingException invalidCommandHandlingException) {

            response.status(SC_BAD_REQUEST);
            return new ResponseMessage(invalidCommandHandlingException.getMessage()).serialize();

        } catch (Exception exception) {

            response.status(SC_INTERNAL_SERVER_ERROR);
            return new ResponseMessage("Internal server error.").serialize();
        }


    }
}
