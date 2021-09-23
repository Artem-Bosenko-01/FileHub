package io.javaclasses.fileHub.persistent.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.RecordId;

import java.util.Objects;

public final class UserId implements RecordId {

    private final String id;

    public UserId(String id) {
        this.id = Preconditions.checkNotNull(id);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userID = (UserId) o;
        return id.equals(userID.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String value() {
        return id;
    }
}
