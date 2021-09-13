package io.javaclasses.fileHub.webservices.files;

import io.javaclasses.fileHub.persistent.files.Folder;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.users.UserId;

import java.util.List;
import java.util.Optional;

public class FolderStorageBaseStub implements FolderStorage {
    @Override
    public void create(Folder inputDataObject) {

    }

    @Override
    public void update(Folder inputDataObject) {

    }

    @Override
    public void delete(String dataRecordID) {

    }

    @Override
    public Optional<Folder> findByID(FolderId dataRecordID) {
        return Optional.empty();
    }

    @Override
    public List<Folder> findAllFoldersByParentFolderId(String parentId, String owner) {
        return null;
    }

    @Override
    public Optional<Folder> findFolderById(String id, UserId owner) {
        return Optional.empty();
    }

    @Override
    public Optional<Folder> findRootFolderByUserId(UserId id) {
        return Optional.empty();
    }

    @Override
    public int getSizeRecordsList() {
        return 0;
    }

    @Override
    public boolean isFolderNameAlreadyExist(String name) {
        return false;
    }

    @Override
    public void decreaseItemsAmount(String id) {

    }

    @Override
    public void increaseItemsAmount(String id) {

    }

    @Override
    public List<Folder> getNestedFolders(String parentFolderId) {
        return null;
    }
}
