package io.javaclasses.fileHub.webservices.files;

import io.javaclasses.fileHub.persistent.files.File;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.FileStorage;

import java.util.List;
import java.util.Optional;

public class FileStorageBaseStub implements FileStorage {
    @Override
    public void create(File inputDataObject) {

    }

    @Override
    public void update(File inputDataObject) {

    }

    @Override
    public void delete(String dataRecordID) {

    }

    @Override
    public Optional<File> findByID(FileId dataRecordID) {
        return Optional.empty();
    }

    @Override
    public List<File> findAllFilesByFolderIdAndUserId(String folderID, String userID) {
        return null;
    }

    @Override
    public boolean isFIleNameAlreadyExist(String name) {
        return false;
    }

    @Override
    public void deleteFilesByParentFolderId(String parentFolderId) {

    }
}
