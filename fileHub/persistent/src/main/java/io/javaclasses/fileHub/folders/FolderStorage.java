package io.javaclasses.fileHub.folders;

import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.Storage;
import io.javaclasses.fileHub.users.UserID;

import java.util.List;
import java.util.Optional;

public interface FolderStorage extends Storage<FolderID,Folder> {
    List<Folder> findAllFoldersByParentFolderId(FolderID parentId) throws NotExistIDException;
    Optional<FolderID> findParentFolderByChildId(FolderID childId)throws NotExistIDException;
    Optional<Folder> findFolderByName(String name, UserID owner);
    int getSizeRecordsList();
}
