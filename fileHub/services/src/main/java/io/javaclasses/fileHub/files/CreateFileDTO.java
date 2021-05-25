package io.javaclasses.fileHub.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.folders.FolderID;
import io.javaclasses.fileHub.users.UserID;

public final class CreateFileDTO {

    private final FileID fileID;
    private final String name;
    private final MimeType mimeType;
    private final UserID owner;
    private final FolderID folder;

    public CreateFileDTO(FileID fileID, String name, MimeType mimeType, UserID owner, FolderID folder) {
        this.fileID = Preconditions.checkNotNull(fileID);
        this.name = Preconditions.checkNotNull(name);
        this.mimeType = Preconditions.checkNotNull(mimeType);
        this.owner = Preconditions.checkNotNull(owner);
        this.folder = Preconditions.checkNotNull(folder);
    }

    public FileID fileID(){ return fileID;}

    public String name() {
        return name;
    }

    public MimeType mimeType() {
        return mimeType;
    }

    public UserID owner() {
        return owner;
    }

    public FolderID folder() {
        return folder;
    }
}
