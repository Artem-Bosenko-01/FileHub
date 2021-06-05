package io.javaclasses.fileHub.persistent.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.RecordId;
import io.javaclasses.fileHub.persistent.users.UserId;

import javax.annotation.Nullable;
import java.util.Objects;

public final class FileId implements RecordId {
    private final String id;

    public FileId(String name, UserId userID, @Nullable FolderId folderID) {
        this.id = Preconditions.checkNotNull(name) + Preconditions.checkNotNull(userID) + folderID;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        FileId fileID = (FileId) o;

        return id.equals(fileID.id);
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
