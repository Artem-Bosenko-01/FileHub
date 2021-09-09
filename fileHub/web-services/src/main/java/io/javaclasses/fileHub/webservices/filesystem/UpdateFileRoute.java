package io.javaclasses.fileHub.webservices.filesystem;

import com.google.gson.JsonObject;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidValidationCommandDataException;
import io.javaclasses.fileHub.services.NotAuthorizedUserException;
import io.javaclasses.fileHub.services.files.*;
import io.javaclasses.fileHub.webservices.InvalidParsingToJsonObject;
import io.javaclasses.fileHub.webservices.RequestParser;
import io.javaclasses.fileHub.webservices.ResponseMessage;
import spark.Request;
import spark.Response;
import spark.Route;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.javaclasses.fileHub.webservices.ParserToJsonObject.parse;
import static javax.servlet.http.HttpServletResponse.*;

public class UpdateFileRoute implements Route {

    private final UpdateFile updateFile;
    private final int INVALID_ENTITY_VALIDATION = 422;

    public UpdateFileRoute(UpdateFile updateFile) {

        this.updateFile = checkNotNull(updateFile);
    }

    @Override
    public Object handle(Request request, Response response) {

        RequestParser parser = new RequestParser(request);

        String token = parser.getToken();

        String body = parser.body();

        try {

            JsonObject jsonObject = parse(body);

            UpdateFileCommand updateFileCommand = convertToCommand(token, jsonObject);

            FileId updatedFileId = updateFile.handle(updateFileCommand);

            return new ResponseMessage("File with id: " + updatedFileId + " was successfully updated").serialize();

        } catch (FileNameAlreadyUsed | InvalidParsingToJsonObject exception) {

            response.status(SC_BAD_REQUEST);
            return new ResponseMessage(exception.getMessage()).serialize();

        } catch (InvalidValidationCommandDataException e) {

            response.status(INVALID_ENTITY_VALIDATION);

            return new ResponseMessage("Error: Invalid user credentials.").serialize();

        } catch (UsersTokenNotFoundException | FileNotFoundException e) {

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

    private UpdateFileCommand convertToCommand(String token, JsonObject jsonObject) throws InvalidValidationCommandDataException {

        String id = jsonObject.get("id").getAsString();
        String name = jsonObject.get("name").getAsString();
        String mimeType = jsonObject.get("mimeType").getAsString();
        long size = jsonObject.get("size").getAsLong();
        String parentFolderId = jsonObject.get("parentFolderId").getAsString();

        return new UpdateFileCommand(new AuthToken(token), id, name, mimeType, size, parentFolderId);
    }
}
