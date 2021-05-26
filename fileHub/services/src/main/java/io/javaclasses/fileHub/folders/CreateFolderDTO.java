package io.javaclasses.fileHub.folders;

public final class CreateFolderDTO {

    private final String name;
    private final FolderID folderID;

    public CreateFolderDTO(String name, FolderID folderID) {
        this.name = name;
        this.folderID = folderID;
    }

    public String name() {
        return name;
    }

    public FolderID folderID() {
        return folderID;
    }
}
