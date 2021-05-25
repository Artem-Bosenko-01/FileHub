package io.javaclasses.fileHub.files.content;

import com.google.common.base.Preconditions;

public final class GetFileContentDTO {
    private final byte[] content;

    public GetFileContentDTO(byte[] content) {
        this.content = Preconditions.checkNotNull(content);
    }

    public byte[] content() {
        return content;
    }
}
