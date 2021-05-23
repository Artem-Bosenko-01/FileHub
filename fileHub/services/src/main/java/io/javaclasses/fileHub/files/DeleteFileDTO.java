package io.javaclasses.fileHub.files;

public final class DeleteFileDTO {
    private final String DELETED_FILE_ID;

    public DeleteFileDTO(FileID id) {
        DELETED_FILE_ID = "File was deleted: " + id;
    }

    public String getDELETED_FILE_ID() {
        return DELETED_FILE_ID;
    }
}
