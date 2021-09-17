package io.javaclasses.fileHub.webservices.filesystem;

import com.google.gson.JsonObject;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.InvalidValidationCommandDataException;
import io.javaclasses.fileHub.services.NotAuthorizedUserException;
import io.javaclasses.fileHub.services.files.CreateFolder;
import io.javaclasses.fileHub.services.files.CreateFolderCommand;
import io.javaclasses.fileHub.webservices.InvalidParsingToJsonObject;
import io.javaclasses.fileHub.webservices.RequestParser;
import io.javaclasses.fileHub.webservices.ResponseMessage;
import spark.Request;
import spark.Response;
import spark.Route;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.javaclasses.fileHub.webservices.ParserToJsonObject.parse;
import static javax.servlet.http.HttpServletResponse.*;

/**
 * Gets the {@link Request request} and parses request body to {@link CreateFolderCommand command}
 * and executes {@link CreateFolder creating new folder}.
 */
public final class CreateFolderRoute implements Route {

    private final CreateFolder createFolder;
    private final int INVALID_ENTITY_VALIDATION = 422;

    public CreateFolderRoute(CreateFolder createFolder) {

        this.createFolder = checkNotNull(createFolder);
    }

    @Override
    public Object handle(Request request, Response response) {

        RequestParser parser = new RequestParser(request);

        String token = parser.getToken();

        String parentFolderId = parser.getId();

        String body = parser.body();

        try {

            JsonObject jsonObject = parse(body);

            String name = jsonObject.get("name").getAsString();
            Integer itemsAmount = jsonObject.get("itemsAmount").getAsInt();

            CreateFolderCommand createFolderCommand = new
                    CreateFolderCommand(new AuthToken(token), name, itemsAmount, parentFolderId);

            String createdFolderId = createFolder.handle(createFolderCommand).toString();

            return new ResponseMessage("Folder with id: " + createdFolderId + " was successfully created").serialize();

        } catch (NotAuthorizedUserException notAuthorizedUserException) {

            response.status(SC_UNAUTHORIZED);
            return new ResponseMessage(notAuthorizedUserException.getMessage()).serialize();

        } catch (InvalidValidationCommandDataException invalidValidationCommandDataException) {

            response.status(INVALID_ENTITY_VALIDATION);

            return new ResponseMessage("Error: Invalid name of folder.").serialize();

        } catch (InvalidCommandHandlingException | InvalidParsingToJsonObject exception) {

            response.status(SC_BAD_REQUEST);
            return new ResponseMessage(exception.getMessage()).serialize();

        } catch (Exception exception) {

            response.status(SC_INTERNAL_SERVER_ERROR);
            return new ResponseMessage("Internal server error.").serialize();
        }
    }
}
