package io.javaclasses.fileHub.webservices.filesystem;

import com.google.gson.JsonObject;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidValidationCommandDataException;
import io.javaclasses.fileHub.services.NotAuthorizedUserException;
import io.javaclasses.fileHub.services.files.*;
import io.javaclasses.fileHub.webservices.InvalidParsingToJsonObjectException;
import io.javaclasses.fileHub.webservices.RequestParser;
import io.javaclasses.fileHub.webservices.ResponseMessage;
import spark.Request;
import spark.Response;
import spark.Route;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.javaclasses.fileHub.webservices.ParserToJsonObject.parse;
import static javax.servlet.http.HttpServletResponse.*;

/**
 * Gets the {@link Request request}, parses the necessary data for the {@link UpdateFolderCommand command} and
 * executes {@link UpdateFolder updating existed folder} by folderId.
 */
public final class UpdateFolderRoute implements Route {

    private final UpdateFolder updateFolder;
    private final int INVALID_ENTITY_VALIDATION = 422;

    public UpdateFolderRoute(UpdateFolder updateFolder) {

        this.updateFolder = checkNotNull(updateFolder);
    }

    @Override
    public Object handle(Request request, Response response) {


        RequestParser parser = new RequestParser(request);

        String token = parser.getToken();

        String body = parser.body();

        try {

            JsonObject jsonObject = parse(body);

            UpdateFolderCommand updateFolderCommand = convertToCommand(token, jsonObject);

            FolderId updatedFolderId = updateFolder.handle(updateFolderCommand);

            return new ResponseMessage("Folder with id: " + updatedFolderId + " was successfully updated").serialize();

        } catch (FolderNameAlreadyUsedException | InvalidParsingToJsonObjectException invalidCommandHandlingException) {

            response.status(SC_BAD_REQUEST);
            return new ResponseMessage(invalidCommandHandlingException.getMessage()).serialize();

        } catch (UsersTokenNotFoundException | FolderNotFoundException invalidCommandHandlingException) {

            response.status(SC_NOT_FOUND);
            return new ResponseMessage(invalidCommandHandlingException.getMessage()).serialize();

        } catch (InvalidValidationCommandDataException invalidValidationCommandDataException) {

            response.status(INVALID_ENTITY_VALIDATION);
            return new ResponseMessage("Error: Invalid name of folder.").serialize();

        } catch (NotAuthorizedUserException notAuthorizedUserException) {

            response.status(SC_UNAUTHORIZED);
            return new ResponseMessage(notAuthorizedUserException.getMessage()).serialize();

        } catch (Exception exception) {

            response.status(SC_INTERNAL_SERVER_ERROR);
            return new ResponseMessage("Internal server error.").serialize();
        }
    }

    private static UpdateFolderCommand convertToCommand(String token, JsonObject jsonObject)
            throws InvalidValidationCommandDataException {

        String id = jsonObject.get("id").getAsString();
        String name = jsonObject.get("name").getAsString();
        long size = jsonObject.get("itemsAmount").getAsLong();
        String parentFolderId = jsonObject.get("parentFolderId").getAsString();

        return new UpdateFolderCommand(new AuthToken(token), id, name, size, parentFolderId);
    }
}
