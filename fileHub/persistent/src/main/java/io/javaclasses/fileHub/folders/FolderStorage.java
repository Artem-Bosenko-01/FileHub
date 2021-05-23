package io.javaclasses.fileHub.folders;

import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.Storage;
import io.javaclasses.fileHub.users.UserID;

import java.util.List;

public interface FolderStorage extends Storage<FolderID,Folder> {
    List<Folder> findAllByUserID(UserID id) throws NotExistIDException;
}
