package io.javaclasses.fileHub.persistent.files.content;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.DataRecord;
import io.javaclasses.fileHub.persistent.files.FileId;

public final class FileContent implements DataRecord<FileId> {

    private final FileId id;
    private byte[] content;

    public FileContent(String id) {
        this.id = new FileId(Preconditions.checkNotNull(id));
    }

    @Override
    public FileId id() {
        return id;
    }

    public byte[] content() {
        return content.clone();
    }

    public void setContent(byte[] content) {
        this.content = content.clone();
    }
}
