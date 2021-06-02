package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.MimeType;
import io.javaclasses.fileHub.persistent.users.UserId;

import static com.google.common.base.Preconditions.*;

public final class FileInformation {

    private final FileId fileID;
    private final String name;
    private final UserId userID;
    private final MimeType mimeType;
    private final FolderId folder;

    public FileInformation(FileId fileID, String name, UserId userID, MimeType mimeType, FolderId folder) {
        this.fileID = checkNotNull(fileID);
        this.name = checkNotNull(name);
        this.userID = checkNotNull(userID);
        this.mimeType = checkNotNull(mimeType);
        this.folder = checkNotNull(folder);
    }

    public FileId fileID() {
        return fileID;
    }

    public String name() {
        return name;
    }

    public UserId userID() {
        return userID;
    }

    public MimeType mimeType() {
        return mimeType;
    }

    public FolderId folder() {
        return folder;
    }
}
