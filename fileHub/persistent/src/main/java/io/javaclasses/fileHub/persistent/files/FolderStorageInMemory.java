package io.javaclasses.fileHub.persistent.files;

import io.javaclasses.fileHub.persistent.AbstractInMemoryStorage;
import io.javaclasses.fileHub.persistent.NotExistedItemException;
import io.javaclasses.fileHub.persistent.users.UserId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Repository for saving and managing {@link Folder folders data} in RAM in Filehub application.
 */
@Component
public class FolderStorageInMemory extends AbstractInMemoryStorage<FolderId, Folder> implements FolderStorage {

    @Override
    public List<Folder> findAllFoldersByParentFolderId(String parentId, String owner) throws NotExistedItemException {

        if (records().values().stream().noneMatch(folder -> folder.id().value().equals(parentId))) {
            throw new NotExistedItemException(parentId);
        }

        return records().values().stream().
                filter(folder -> folder.parentFolder() != null && folder.parentFolder().equals(parentId)).
                collect(Collectors.toList());

    }


    @Override
    public Optional<Folder> findRootFolderByUserId(UserId id) {

        return records().values().stream().
                filter(folder -> folder.parentFolder() == null && folder.owner().equals(id)).
                findFirst();
    }

    @Override
    public boolean isFolderNameAlreadyExist(String name, String parentFolder) {

        return records().values().stream().anyMatch(file ->
                file.name().equals(name) &&
                        file.parentFolder() != null && file.parentFolder().equals(parentFolder));
    }

    @Override
    public void decreaseItemsAmount(String id) {

        Optional<Folder> folder = records().values().stream().
                filter(fold -> fold.id().value().equals(id)).findFirst();

        folder.ifPresent(value -> value.setItemsAmount(value.itemsAmount() - 1));
    }

    @Override
    public void increaseItemsAmount(String id) {

        Optional<Folder> folder = records().values().stream().
                filter(fold -> fold.id().value().equals(id)).findFirst();

        folder.ifPresent(value -> value.setItemsAmount(value.itemsAmount() + 1));
    }

    @Override
    public List<Folder> getNestedFolders(String parentFolderId) {

        return records().values().stream().
                filter(folder -> folder.parentFolder() != null && folder.parentFolder().equals(parentFolderId)).
                collect(Collectors.toList());
    }
}
