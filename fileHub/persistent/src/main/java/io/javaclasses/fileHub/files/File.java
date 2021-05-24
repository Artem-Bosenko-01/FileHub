package io.javaclasses.fileHub.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.DataRecord;
import io.javaclasses.fileHub.folders.FolderID;
import io.javaclasses.fileHub.users.UserID;

import java.util.Objects;

public final class File implements DataRecord<FileID> {

    private final FileID fileID;
    private String name;
    private UserID userID;
    private Integer size;
    private MimeType mimeType;
    private FolderID folder;

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

    public FolderID folder() {
        return folder;
    }

    public void setFolder(FolderID folder) {
        this.folder = Preconditions.checkNotNull(folder);
    }

    public UserID owner() {
        return userID;
    }

    public void setUserID(UserID userID) {
        this.userID = Preconditions.checkNotNull(userID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return fileID.equals(file.fileID) && name.equals(file.name) && Objects.equals(userID, file.userID) && Objects.equals(size, file.size) && mimeType == file.mimeType && Objects.equals(folder, file.folder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileID, name, userID, size, mimeType, folder);
    }
}
