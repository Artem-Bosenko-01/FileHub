package io.javaclasses.fileHub.persistent.files;

import com.google.common.base.Preconditions;
import com.google.common.net.MediaType;
import io.javaclasses.fileHub.persistent.DataRecord;
import io.javaclasses.fileHub.persistent.users.UserId;

import javax.annotation.Nullable;
import java.util.Objects;

public final class File implements DataRecord<FileId> {

    private final FileId fileID;
    private String name;
    private UserId userID;
    private Integer size;
    private MediaType mimeType;
    @Nullable
    private FolderId folder;

    public File(FileId fileID) {
        this.fileID = Preconditions.checkNotNull(fileID);
    }

    @Override
    public FileId id() {
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

    public MediaType mimeType() {
        return mimeType;
    }

    public void setMimeType(MediaType mimeType) {
        this.mimeType = Preconditions.checkNotNull(mimeType);
    }

    public FolderId folder() {
        return folder;
    }

    public void setFolder(@Nullable FolderId folder) {
        this.folder = folder;
    }

    public UserId owner() {
        return userID;
    }

    public void setUserID(UserId userID) {
        this.userID = Preconditions.checkNotNull(userID);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        File file = (File) o;

        return fileID.equals(file.fileID);

    }

    @Override
    public int hashCode() {
        return Objects.hash(fileID);
    }
}
