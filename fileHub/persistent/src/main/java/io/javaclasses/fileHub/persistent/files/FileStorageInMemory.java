package io.javaclasses.fileHub.persistent.files;

import io.javaclasses.fileHub.persistent.AbstractInMemoryStorage;
import io.javaclasses.fileHub.persistent.NotExistedItem;

import java.util.List;
import java.util.stream.Collectors;

public class FileStorageInMemory extends AbstractInMemoryStorage<FileId, File>
        implements FileStorage {

    @Override
    public List<File> findAllFilesByFolderIdAndUserId(String folderID, String userID) throws NotExistedItem {

        if (userID == null) {

            throw new NotExistedItem("User with doesn't be null ");

        }

        return records().values().stream()
                .filter(file -> file.folder().toString().equals(folderID) && file.owner().toString().equals(userID))
                .collect(Collectors.toList());

    }

}
