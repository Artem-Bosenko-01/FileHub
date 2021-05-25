package io.javaclasses.fileHub.users;

import com.google.common.base.Preconditions;
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
        super(Preconditions.checkNotNull(token));
        this.userID = Preconditions.checkNotNull(userID);
        this.newLoginName = Preconditions.checkNotNull(newLoginName);
        this.firstName = Preconditions.checkNotNull(firstName);
        this.lastName = Preconditions.checkNotNull(lastName);
        this.password = Preconditions.checkNotNull(password);
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
