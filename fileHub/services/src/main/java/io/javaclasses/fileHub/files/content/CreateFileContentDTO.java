package io.javaclasses.fileHub.files.content;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.files.FileID;

public final class CreateFileContentDTO {

    private final FileID fileID;
    private final byte[] content;

    public CreateFileContentDTO(FileID fileID, byte[] content) {
        this.fileID = Preconditions.checkNotNull(fileID);
        this.content = Preconditions.checkNotNull(content);
    }

    public FileID fileID() {
        return fileID;
    }

    public byte[] content() {
        return content;
    }
}
