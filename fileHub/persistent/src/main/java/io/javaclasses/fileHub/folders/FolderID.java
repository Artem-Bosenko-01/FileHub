package io.javaclasses.fileHub.folders;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.RecordID;

import java.util.Objects;

public final class FolderID implements RecordID {

    private final int id;

    public FolderID(int id) {
        this.id = Preconditions.checkNotNull(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FolderID userID = (FolderID) o;
        return id == userID.id;
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
