package io.javaclasses.fileHub.persistent.files.content;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.DataRecord;
import io.javaclasses.fileHub.persistent.files.FileId;

public final class FileContent implements DataRecord<FileId> {

    private final FileId id;
    private byte[] content;

    public FileContent(FileId id) {
        this.id = Preconditions.checkNotNull(id);
    }

    @Override
    public FileId id() {
        return id;
    }

    public byte[] content() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
