package io.javaclasses.fileHub.persistent.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.RecordId;
import io.javaclasses.fileHub.persistent.users.UserId;

import java.util.Objects;

public final class FolderId implements RecordId {

    private final String id;

    public FolderId(String name, UserId userID) {
        this.id = Preconditions.checkNotNull(name) + Preconditions.checkNotNull(userID);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        FolderId userID = (FolderId) o;

        return id.equals(userID.id);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
