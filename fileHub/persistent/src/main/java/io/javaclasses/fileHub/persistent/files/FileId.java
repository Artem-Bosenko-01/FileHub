package io.javaclasses.fileHub.persistent.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.RecordId;

import java.util.Objects;

public final class FileId implements RecordId {

    private final String id;

    public FileId(String id) {
        this.id = Preconditions.checkNotNull(id);
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
    public String value() {
        return id;
    }
}
