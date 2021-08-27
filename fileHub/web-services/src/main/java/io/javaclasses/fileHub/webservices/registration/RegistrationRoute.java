package io.javaclasses.fileHub.webservices.registration;

import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.services.ServiceAdapter;
import io.javaclasses.fileHub.services.users.RegistrationUserCommand;
import io.javaclasses.fileHub.webservices.ErrorResponse;
import spark.Request;
import spark.Response;
import spark.Route;

public class RegistrationRoute implements Route {

    private final ServiceAdapter adapter;

    public RegistrationRoute(ServiceAdapter adapter) {

        this.adapter = adapter;
    }

    @Override
    public Object handle(Request request, Response response) {

        String body = request.body();

        UserRegistrationCredentials credentials = new UserRegistrationCredentials(body);

        RegistrationUserCommand command = credentials.deserialize();

        try {

            adapter.registerUser().handle(command);

            response.status(200);
            return true;

        } catch (InvalidHandleCommandException e) {

            response.status(422);
            return new ErrorResponse(e.getMessage()).serialize();

        } catch (Exception e) {

            response.status(500);
            return new ErrorResponse("Internal server error.").serialize();
        }
    }
}
