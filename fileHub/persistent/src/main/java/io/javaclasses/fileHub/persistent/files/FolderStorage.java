package io.javaclasses.fileHub.persistent.files;

import io.javaclasses.fileHub.persistent.NotExistUserIdException;
import io.javaclasses.fileHub.persistent.Storage;
import io.javaclasses.fileHub.persistent.users.UserId;

import java.util.List;
import java.util.Optional;

public interface FolderStorage extends Storage<FolderId, Folder> {

    List<Folder> findAllFoldersByParentFolderId(FolderId parentId) throws NotExistUserIdException;

    Optional<FolderId> findParentFolderByChildId(FolderId childId) throws NotExistUserIdException;

    Optional<Folder> findFolderByName(String name, UserId owner);

    int getSizeRecordsList();

}
