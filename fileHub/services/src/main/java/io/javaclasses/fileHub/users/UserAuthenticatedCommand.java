package io.javaclasses.fileHub.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.AnonymousUserCommand;

import java.util.Objects;

/**
 * This is object, that contains data, that was inputted by user in the process of authentication in Filehub application.
 */
public final class UserAuthenticatedCommand extends AnonymousUserCommand {

    private final String loginName;
    private final String password;

    public UserAuthenticatedCommand(String loginName, String password) {
        this.loginName = Preconditions.checkNotNull(loginName);
        this.password = Preconditions.checkNotNull(password);
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
        UserAuthenticatedCommand that = (UserAuthenticatedCommand) o;
        return loginName.equals(that.loginName) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginName, password);
    }
}
