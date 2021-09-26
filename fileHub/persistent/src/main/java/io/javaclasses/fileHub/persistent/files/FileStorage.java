package io.javaclasses.fileHub.persistent.files;

import io.javaclasses.fileHub.persistent.NotExistedItemException;
import io.javaclasses.fileHub.persistent.Storage;

import java.util.List;

/**
 * The {@link Storage storage} that contains instruments for managing {@link File files} in the FileHub application.
 */
public interface FileStorage extends Storage<FileId, File> {

    List<File> findAllFilesByFolderIdAndUserId(String folderID, String userID) throws NotExistedItemException;

    boolean isFIleNameAlreadyExist(String name);

    void deleteFilesByParentFolderId(String parentFolderId);
}
