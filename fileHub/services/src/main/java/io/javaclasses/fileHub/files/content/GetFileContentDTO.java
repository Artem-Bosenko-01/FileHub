package io.javaclasses.fileHub.files.content;

public class GetFileContentDTO {
    private final byte[] content;

    public GetFileContentDTO(byte[] content) {
        this.content = content;
    }

    public byte[] content() {
        return content;
    }
}
