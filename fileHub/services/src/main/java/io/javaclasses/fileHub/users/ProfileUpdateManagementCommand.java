package io.javaclasses.fileHub.users;

import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.AuthenticatedUserCommand;

/**
 *
 * */
public final class ProfileUpdateManagementCommand extends AuthenticatedUserCommand {


    private final UserID userID;
    private final String newLoginName;
    private final String firstName;
    private final String lastName;
    private final String password;

    public ProfileUpdateManagementCommand(AuthToken token, UserID userID, String newLoginName, String firstName, String lastName, String password) {
        super(token);
        this.userID = userID;
        this.newLoginName = newLoginName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public UserID userID() {
        return userID;
    }

    public String newLoginName() {
        return newLoginName;
    }

    public String password() {
        return password;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

}
