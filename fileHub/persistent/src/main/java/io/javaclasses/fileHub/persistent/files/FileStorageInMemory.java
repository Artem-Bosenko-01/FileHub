package io.javaclasses.fileHub.persistent.files;

import io.javaclasses.fileHub.persistent.AbstractInMemoryStorage;
import io.javaclasses.fileHub.persistent.NotExistedItemException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class FileStorageInMemory extends AbstractInMemoryStorage<FileId, File>
        implements FileStorage {

    @Override
    public List<File> findAllFilesByFolderIdAndUserId(String folderID, String userID) throws NotExistedItemException {

        if (userID == null) {

            throw new NotExistedItemException(userID);

        }

        return records().values().stream()
                .filter(file -> (file.folder() != null && file.folder().equals(folderID)) && file.owner().toString().equals(userID))
                .collect(Collectors.toList());

    }

    @Override
    public boolean isFIleNameAlreadyExist(String name) {
        return records().values().stream().anyMatch(file -> file.name().equals(name));
    }

    @Override
    public void deleteFilesByParentFolderId(String parentFolderId) {

        List<File> removedFiles = records().values().stream().
                filter(file -> Objects.equals(file.folder(), parentFolderId)).collect(Collectors.toList());

        removedFiles.forEach(removedFile -> records().remove(removedFile.id()));

    }

}
