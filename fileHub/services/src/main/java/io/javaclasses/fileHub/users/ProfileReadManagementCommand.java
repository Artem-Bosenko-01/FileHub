package io.javaclasses.fileHub.users;

import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.AuthenticatedUserCommand;

import java.util.Objects;

public final class ProfileReadManagementCommand extends AuthenticatedUserCommand {
    public ProfileReadManagementCommand(AuthToken token, UserID id) {
        super(token);
        this.id = id;
    }

    private final UserID id;

    public UserID id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileReadManagementCommand that = (ProfileReadManagementCommand) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
