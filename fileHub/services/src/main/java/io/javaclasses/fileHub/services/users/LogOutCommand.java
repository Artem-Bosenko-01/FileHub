package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.AuthenticatedUserCommand;

/**
 * Contains data for executes the {@link LogOut log out} process.
 */
public class LogOutCommand extends AuthenticatedUserCommand {

    public LogOutCommand(AuthToken token) {

        super(token);
    }
}
