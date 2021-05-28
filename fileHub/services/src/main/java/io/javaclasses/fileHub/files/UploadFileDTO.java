package io.javaclasses.fileHub.files;

import com.google.common.base.Preconditions;

public class UploadFileDTO {
    private final FileID fileID;
    private final byte[] content;

    public UploadFileDTO(FileID fileID, byte[] content) {
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
