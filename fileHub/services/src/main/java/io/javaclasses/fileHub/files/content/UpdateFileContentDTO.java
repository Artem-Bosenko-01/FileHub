package io.javaclasses.fileHub.files.content;

import com.google.common.base.Preconditions;

public final class UpdateFileContentDTO {
    private final byte[] content;

    public UpdateFileContentDTO(byte[] content) {
        this.content = Preconditions.checkNotNull(content);
    }

    public byte[] content() {
        return content;
    }
}
