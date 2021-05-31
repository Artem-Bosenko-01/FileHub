package io.javaclasses.fileHub.files;

import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.Storage;
import io.javaclasses.fileHub.folders.FolderID;
import io.javaclasses.fileHub.users.UserID;

import java.util.List;
import java.util.Optional;

public interface FileStorage extends Storage<FileID,File> {
    Optional<File> findByName(String name);
    List<File> findAllFilesByFolderIDAndUserID(FolderID folderID, UserID userID) throws NotExistIDException;
}
