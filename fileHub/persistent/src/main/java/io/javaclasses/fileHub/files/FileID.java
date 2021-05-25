package io.javaclasses.fileHub.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.RecordID;
import io.javaclasses.fileHub.folders.FolderID;
import io.javaclasses.fileHub.users.UserID;

import java.util.Objects;

/**
 *
 * */
public final class FileID implements RecordID {
    private final String id;

    public FileID(String name, UserID userID, FolderID folderID) {
        this.id = Preconditions.checkNotNull(name) + Preconditions.checkNotNull(userID) + Preconditions.checkNotNull(folderID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileID userID = (FileID) o;
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
