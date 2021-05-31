package io.javaclasses.fileHub.files;

import io.javaclasses.fileHub.AbstractInMemoryStorage;
import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.folders.FolderID;
import io.javaclasses.fileHub.users.UserID;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileStorageInMemory extends AbstractInMemoryStorage<FileID,File>
        implements FileStorage {
    @Override
    public Optional<File> findByName(String name) {
        return records().values().
                stream().
                filter(file -> file.name().equals(name)).
                findFirst();
    }

    @Override
    public List<File> findAllFilesByFolderIDAndUserID(FolderID folderID, UserID userID) throws NotExistIDException {
        if(records().values().stream().noneMatch(file -> file.folder().equals(folderID))) throw new NotExistIDException("Folder with " + folderID + " not exist");
        if(records().values().stream().noneMatch(file -> file.owner().equals(userID))) throw new NotExistIDException("User with " + userID + " not exist");
        return records().values().stream()
                .filter(file -> file.folder().equals(folderID) && file.owner().equals(userID))
                .collect(Collectors.toList());
    }

}
