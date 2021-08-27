package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.AuthenticatedUserCommand;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This is object, that contains data, that was inputted by user when he want to update
 * his profile information.
 */
public final class UpdatingProfileCommand extends AuthenticatedUserCommand {


    private final UserId userID;
    private final String newLoginName;
    private final String password;

    public UpdatingProfileCommand(AuthToken token, UserId userID, String newLoginName, String password) {

        super(checkNotNull(token));
        this.userID = checkNotNull(userID);
        this.newLoginName = checkNotNull(newLoginName);
        this.password = checkNotNull(password);

    }

    public UserId userID() {
        return userID;
    }

    public String newLoginName() {
        return newLoginName;
    }

    public String password() {
        return password;
    }

}
