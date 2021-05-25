package io.javaclasses.fileHub.files.content;

import io.javaclasses.fileHub.files.FileID;

public class CreateFileContentDTO {

    private final FileID fileID;
    private final byte[] content;

    public CreateFileContentDTO(FileID fileID, byte[] content) {
        this.fileID = fileID;
        this.content = content;
    }
}
