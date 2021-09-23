package io.javaclasses.fileHub.persistent.files;

import io.javaclasses.fileHub.persistent.NotExistedItemException;
import io.javaclasses.fileHub.persistent.Storage;
import io.javaclasses.fileHub.persistent.users.UserId;

import java.util.List;
import java.util.Optional;

public interface FolderStorage extends Storage<FolderId, Folder> {

    List<Folder> findAllFoldersByParentFolderId(String parentId, String owner) throws NotExistedItemException;

    Optional<Folder> findRootFolderByUserId(UserId id);

    boolean isFolderNameAlreadyExist(String name, String parentFolder);

    void decreaseItemsAmount(String id) throws NotExistedItemException;

    void increaseItemsAmount(String id) throws NotExistedItemException;

    List<Folder> getNestedFolders(String parentFolderId);

}
