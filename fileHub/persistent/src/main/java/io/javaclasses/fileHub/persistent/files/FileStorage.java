package io.javaclasses.fileHub.persistent.files;

import io.javaclasses.fileHub.persistent.NotExistedItemException;
import io.javaclasses.fileHub.persistent.Storage;

import java.util.List;

public interface FileStorage extends Storage<FileId, File> {
    List<File> findAllFilesByFolderIdAndUserId(String folderID, String userID) throws NotExistedItemException;

    boolean isFIleNameAlreadyExist(String name);

    void deleteFilesByParentFolderId(String parentFolderId);
}
