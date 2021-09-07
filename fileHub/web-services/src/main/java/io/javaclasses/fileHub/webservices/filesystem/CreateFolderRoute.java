package io.javaclasses.fileHub.webservices.filesystem;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.NotAuthorizedUserException;
import io.javaclasses.fileHub.services.files.CreateFolder;
import io.javaclasses.fileHub.services.files.CreateFolderCommand;
import io.javaclasses.fileHub.webservices.ErrorResponse;
import io.javaclasses.fileHub.webservices.InvalidParsingToJsonObject;
import io.javaclasses.fileHub.webservices.RequestParser;
import spark.Request;
import spark.Response;
import spark.Route;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.javaclasses.fileHub.webservices.ParserToJsonObject.parse;
import static javax.servlet.http.HttpServletResponse.*;

public class CreateFolderRoute implements Route {

    private final CreateFolder createFolder;

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

            FolderId createdFolderId = createFolder.handle(createFolderCommand);

            return new Gson().toJson("Folder with id: " + createdFolderId.toString() + " was successfully created");

        } catch (NotAuthorizedUserException e) {

            response.status(SC_UNAUTHORIZED);
            return new ErrorResponse(e.getMessage()).serialize();

        } catch (InvalidCommandHandlingException | InvalidParsingToJsonObject e) {

            response.status(SC_BAD_REQUEST);
            return new ErrorResponse(e.getMessage()).serialize();

        } catch (Exception e) {

            response.status(SC_INTERNAL_SERVER_ERROR);
            return new ErrorResponse("Internal server error.").serialize();
        }
    }
}
