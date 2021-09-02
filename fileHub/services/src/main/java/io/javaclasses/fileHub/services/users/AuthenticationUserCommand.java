package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.services.AnonymousUserCommand;
import io.javaclasses.fileHub.services.ValidationCommandDataException;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.javaclasses.fileHub.services.users.ValidationRules.validateUsersCredentials;

/**
 * Data that is needed to authenticate the user in the Filehub application.
 */
public final class AuthenticationUserCommand extends AnonymousUserCommand {

    private final String loginName;
    private final String password;

    public AuthenticationUserCommand(String loginName, String password) throws ValidationCommandDataException {

        validateUsersCredentials(loginName, password);

        this.loginName = checkNotNull(loginName);
        this.password = checkNotNull(password);

    }

    public String loginName() {
        return loginName;
    }

    public String password() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationUserCommand that = (AuthenticationUserCommand) o;
        return loginName.equals(that.loginName) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginName, password);
    }
}
