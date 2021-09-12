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

public class DownloadFileRoute implements Route {

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

        } catch (NotAuthorizedUserException e) {

            response.status(SC_UNAUTHORIZED);
            return new ResponseMessage(e.getMessage()).serialize();

        } catch (InvalidCommandHandlingException e) {

            response.status(SC_BAD_REQUEST);
            return new ResponseMessage(e.getMessage()).serialize();

        } catch (Exception e) {

            response.status(SC_INTERNAL_SERVER_ERROR);
            return new ResponseMessage("Internal server error.").serialize();
        }


    }
}
