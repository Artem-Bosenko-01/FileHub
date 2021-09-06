package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.services.AnonymousUserCommand;
import io.javaclasses.fileHub.services.ValidationCommandDataException;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.javaclasses.fileHub.services.users.ValidationRules.validateUsersCredentials;

/**
 * Data that needed to register user in the FileHub application.
 */
public final class RegistrationUserCommand extends AnonymousUserCommand {

    private final String loginName;

    private final String password;

    public RegistrationUserCommand(String loginName, String password) throws ValidationCommandDataException {

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

        checkNotNull(o);
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;
        RegistrationUserCommand that = (RegistrationUserCommand) o;
        return loginName.equals(that.loginName) &&
                password.equals(that.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(loginName, password);
    }
}
