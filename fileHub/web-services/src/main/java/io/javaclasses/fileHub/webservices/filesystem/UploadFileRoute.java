package io.javaclasses.fileHub.webservices.filesystem;

import com.google.common.net.MediaType;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidValidationCommandDataException;
import io.javaclasses.fileHub.services.NotAuthorizedUserException;
import io.javaclasses.fileHub.services.files.DuplicatedFileNameException;
import io.javaclasses.fileHub.services.files.UploadFile;
import io.javaclasses.fileHub.services.files.UploadFileCommand;
import io.javaclasses.fileHub.services.files.UsersTokenNotFoundException;
import io.javaclasses.fileHub.webservices.RequestParser;
import io.javaclasses.fileHub.webservices.ResponseMessage;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.utils.IOUtils;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

import static com.google.common.base.Preconditions.checkNotNull;
import static javax.servlet.http.HttpServletResponse.*;

public class UploadFileRoute implements Route {

    private final UploadFile uploadFile;
    private final int INVALID_ENTITY_VALIDATION = 422;

    public UploadFileRoute(UploadFile uploadFile) {

        this.uploadFile = checkNotNull(uploadFile);
    }

    @Override
    public Object handle(Request request, Response response) {

        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

        RequestParser requestParser = new RequestParser(request);

        String token = requestParser.getToken();

        String folderId = requestParser.getId();

        try {

            Part file = request.raw().getPart("file");

            MediaType mimeType = MediaType.parse(file.getContentType());

            UploadFileCommand uploadFileCommand = new UploadFileCommand(new AuthToken(token),
                    file.getSubmittedFileName(), mimeType, folderId, IOUtils.toByteArray(file.getInputStream()));

            String uploadedFileId = uploadFile.handle(uploadFileCommand);

            response.status(SC_OK);

            return new ResponseMessage("File with id: " + uploadedFileId + " was successfully uploaded").serialize();

        } catch (InvalidValidationCommandDataException e) {

            response.status(INVALID_ENTITY_VALIDATION);
            return new ResponseMessage("Error: Invalid name of file.").serialize();

        } catch (UsersTokenNotFoundException | DuplicatedFileNameException e) {

            response.status(SC_BAD_REQUEST);
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
