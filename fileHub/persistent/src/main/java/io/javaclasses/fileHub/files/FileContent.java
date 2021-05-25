package io.javaclasses.fileHub.files;

import io.javaclasses.fileHub.DataRecord;

public class FileContent implements DataRecord<FileID> {

    private final FileID id;
    private byte[] content;

    public FileContent(FileID id) {
        this.id = id;
    }

    @Override
    public FileID id() {
        return null;
    }

    public byte[] content() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
