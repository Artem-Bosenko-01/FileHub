package io.javaclasses.fileHub.persistent.files;

import io.javaclasses.fileHub.persistent.NotExistedItem;
import io.javaclasses.fileHub.persistent.Storage;
import io.javaclasses.fileHub.persistent.users.UserId;

import java.util.List;
import java.util.Optional;

public interface FolderStorage extends Storage<FolderId, Folder> {

    List<Folder> findAllFoldersByParentFolderId(String parentId, String owner) throws NotExistedItem;

    Optional<FolderId> findParentFolderByChildId(FolderId childId) throws NotExistedItem;

    Optional<Folder> findFolderById(String id, UserId owner);

    Optional<Folder> findRootFolderByUserId(UserId id);

    int getSizeRecordsList();

}
