package io.javaclasses.fileHub.users;

import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.AuthenticatedUserCommand;

/**
 *
 * */
public final class ProfileUpdateManagementCommand extends AuthenticatedUserCommand {

    private final UserID id;
    private final String loginName;
    private final String firstName;
    private final String lastName;
    private final String password;

    public ProfileUpdateManagementCommand(AuthToken token, UserID id, String loginName, String firstName, String lastName, String password) {
        super(token);
        this.id = id;
        this.loginName = loginName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String loginName() {
        return loginName;
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

    public UserID id() {
        return id;
    }
}
