package io.javaclasses.fileHub.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.DataRecord;

import java.util.Objects;

public final class File implements DataRecord<FileID> {

    private final FileID fileID;
    private String name;
    private Integer size;
    private MimeType mimeType;

    public File(FileID fileID) {
        this.fileID = Preconditions.checkNotNull(fileID);
    }

    @Override
    public FileID id() {
        return fileID;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = Preconditions.checkNotNull(name);
    }

    public Integer size() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = Preconditions.checkNotNull(size);
    }

    public MimeType mimeType() {
        return mimeType;
    }

    public void setMimeType(MimeType mimeType) {
        this.mimeType = Preconditions.checkNotNull(mimeType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return fileID.equals(file.fileID) && name.equals(file.name) && Objects.equals(size, file.size) && mimeType == file.mimeType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileID, name, size, mimeType);
    }
}
