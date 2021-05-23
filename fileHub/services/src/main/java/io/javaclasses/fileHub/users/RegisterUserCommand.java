package io.javaclasses.fileHub.users;

import io.javaclasses.fileHub.AnonymousUserCommand;

/**
 *
 * */
public class RegisterUserCommand extends AnonymousUserCommand {

    private final UserID id;
    private final String loginName;
    private final String firstName;
    private final String lastName;
    private final String password;

    public RegisterUserCommand(UserID id, String loginName, String firstName, String lastName, String password) {
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
