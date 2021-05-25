package io.javaclasses.fileHub.files.content;

public class UpdateFileContentDTO {
    private final byte[] content;

    public UpdateFileContentDTO(byte[] content) {
        this.content = content;
    }

    public byte[] content() {
        return content;
    }
}
