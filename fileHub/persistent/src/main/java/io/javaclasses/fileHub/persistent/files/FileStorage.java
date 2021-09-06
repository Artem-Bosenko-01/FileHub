package io.javaclasses.fileHub.persistent.files;

import io.javaclasses.fileHub.persistent.NotExistUserIdException;
import io.javaclasses.fileHub.persistent.Storage;
import io.javaclasses.fileHub.persistent.users.UserId;

import java.util.List;

public interface FileStorage extends Storage<FileId, File> {
    List<File> findAllFilesByFolderIdAndUserId(FolderId folderID, UserId userID) throws NotExistUserIdException;
}
