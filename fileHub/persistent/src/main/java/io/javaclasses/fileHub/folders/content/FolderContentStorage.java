package io.javaclasses.fileHub.folders.content;

import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.Storage;
import io.javaclasses.fileHub.folders.FolderID;

public interface FolderContentStorage extends Storage<FolderID, FolderContent> {
    public FolderContent getAllFolderContentByID(FolderID id) throws NotExistIDException;
}
