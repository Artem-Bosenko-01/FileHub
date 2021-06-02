package io.javaclasses.fileHub.persistent.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.DataRecord;
import io.javaclasses.fileHub.persistent.users.UserId;

import java.util.Objects;

public final class File implements DataRecord<FileId> {

    private final FileId fileID;
    private String name;
    private UserId userID;
    private Integer size;
    private MimeType mimeType;
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

    public MimeType mimeType() {
        return mimeType;
    }

    public void setMimeType(MimeType mimeType) {
        this.mimeType = Preconditions.checkNotNull(mimeType);
    }

    public FolderId folder() {
        return folder;
    }

    public void setFolder(FolderId folder) {
        this.folder = Preconditions.checkNotNull(folder);
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

        return fileID.equals(file.fileID) &&
                name.equals(file.name) &&
                Objects.equals(userID, file.userID) &&
                Objects.equals(size, file.size) &&
                mimeType == file.mimeType &&
                Objects.equals(folder, file.folder);

    }

    @Override
    public int hashCode() {
        return Objects.hash(fileID, name, userID, size, mimeType, folder);
    }
}
