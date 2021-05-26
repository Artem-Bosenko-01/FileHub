package io.javaclasses.fileHub.folders;

public final class GetFolderByNameDTO {

    public GetFolderByNameDTO(FolderID folderID) {
        this.folderID = folderID;
    }

    private final FolderID folderID;

    public FolderID folderID() {
        return folderID;
    }
}
