package io.javaclasses.fileHub.services.files.content;

import com.google.common.base.Preconditions;

/**
 * This is object, that contains data after successful
 * execution {@link GettingFileContent get file's content process}.
 */
public final class GetFileContentDTO {

    private final byte[] content;

    public GetFileContentDTO(byte[] content) {
        this.content = Preconditions.checkNotNull(content);
    }

    public byte[] content() {
        return content.clone();
    }

}
