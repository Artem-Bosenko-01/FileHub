package io.javaclasses.fileHub.persistent.users.tokens;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.RecordId;

import java.util.Objects;

/**
 * Key that gives some permission for authenticated user in the FileHub application.
 */
public final class UserAuthToken implements RecordId {

    private final String value;

    public UserAuthToken(String value) {
        this.value = Preconditions.checkNotNull(value);
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuthToken that = (UserAuthToken) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
