package io.javaclasses.fileHub.persistent.files;

import io.javaclasses.fileHub.persistent.AbstractInMemoryStorage;
import io.javaclasses.fileHub.persistent.NotExistUserIdException;
import io.javaclasses.fileHub.persistent.users.UserId;

import java.util.List;
import java.util.stream.Collectors;

public class FileStorageInMemory extends AbstractInMemoryStorage<FileId, File>
        implements FileStorage {

    @Override
    public List<File> findAllFilesByFolderIDAndUserID(FolderId folderID, UserId userID) throws NotExistUserIdException {

        if (records().values().stream().noneMatch(file -> file.folder().equals(folderID))){

            throw new NotExistUserIdException("Folder with " + folderID + " not exist");

        }

        if (records().values().stream().noneMatch(file -> file.owner().equals(userID))){

            throw new NotExistUserIdException("User with " + userID + " not exist");

        }

        return records().values().stream()
                .filter(file -> file.folder().equals(folderID) && file.owner().equals(userID))
                .collect(Collectors.toList());

    }

}
