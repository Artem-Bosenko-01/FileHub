package io.javaclasses.fileHub.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.users.UserID;

public final class CreateFileDTO {

    private final FileID id;
    private final String name;
    private final MimeType mimeType;
    private final UserID owner;

    public CreateFileDTO(FileID id, String name, MimeType mimeType, UserID owner) {
        this.id = Preconditions.checkNotNull(id);
        this.name = Preconditions.checkNotNull(name);
        this.mimeType = Preconditions.checkNotNull(mimeType);
        this.owner = owner;
    }

    public FileID id() {
        return id;
    }

    public String name() {
        return name;
    }

    public MimeType mimeType() {
        return mimeType;
    }

    public UserID owner() {
        return owner;
    }
}
