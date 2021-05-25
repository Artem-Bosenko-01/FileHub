package io.javaclasses.fileHub.folders;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.RecordID;
import io.javaclasses.fileHub.users.UserID;

import java.util.Objects;

public final class FolderID implements RecordID {

    private final String id;

    public FolderID(String name, UserID userID) {
        this.id = Preconditions.checkNotNull(name) + Preconditions.checkNotNull(userID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FolderID userID = (FolderID) o;
        return id.equals(userID.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
