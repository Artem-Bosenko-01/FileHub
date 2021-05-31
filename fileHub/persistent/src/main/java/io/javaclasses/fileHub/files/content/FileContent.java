package io.javaclasses.fileHub.files.content;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.DataRecord;
import io.javaclasses.fileHub.files.FileID;

public final class FileContent implements DataRecord<FileID> {

    private final FileID id;
    private byte[] content;

    public FileContent(FileID id) {
        this.id = Preconditions.checkNotNull(id);
    }

    @Override
    public FileID id() {
        return id;
    }

    public byte[] content() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
