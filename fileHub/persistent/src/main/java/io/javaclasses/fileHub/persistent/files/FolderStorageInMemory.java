package io.javaclasses.fileHub.persistent.files;

import io.javaclasses.fileHub.persistent.AbstractInMemoryStorage;
import io.javaclasses.fileHub.persistent.NotExistedItemException;
import io.javaclasses.fileHub.persistent.users.UserId;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FolderStorageInMemory extends AbstractInMemoryStorage<FolderId, Folder> implements FolderStorage {

    @Override
    public List<Folder> findAllFoldersByParentFolderId(String parentId, String owner) throws NotExistedItemException {
        if (records().values().stream().noneMatch(folder -> folder.id().toString().equals(parentId))) {
            throw new NotExistedItemException("Parent folder doesn't exist: " + parentId);
        }

        return records().values().stream().
                filter(folder -> folder.parentFolder() != null && folder.parentFolder().equals(parentId)).
                collect(Collectors.toList());

    }

    @Override
    public Optional<Folder> findFolderById(String id, UserId owner) {

        return records().values().stream().
                filter(folder -> folder.id().toString().equals(id) && folder.owner().equals(owner)).
                findFirst();
    }

    @Override
    public Optional<Folder> findRootFolderByUserId(UserId id) {
        return records().values().stream().
                filter(folder -> folder.parentFolder() == null && folder.owner().equals(id)).
                findFirst();
    }

    @Override
    public int getSizeRecordsList() {
        return records().size();
    }

    @Override
    public boolean isFolderNameAlreadyExist(String name) {
        return records().values().stream().anyMatch(file -> file.name().equals(name));
    }

    @Override
    public void decreaseItemsAmount(String id) {

        Optional<Folder> folder = records().values().stream().
                filter(fold -> fold.id().toString().equals(id)).findFirst();

        folder.ifPresent(value -> value.setItemsAmount(value.itemsAmount() - 1));
    }

    @Override
    public void increaseItemsAmount(String id) {
        Optional<Folder> folder = records().values().stream().
                filter(fold -> fold.id().toString().equals(id)).findFirst();

        folder.ifPresent(value -> value.setItemsAmount(value.itemsAmount() + 1));
    }

    @Override
    public List<Folder> getNestedFolders(String parentFolderId) {

        return records().values().stream().
                filter(folder -> folder.parentFolder() != null && folder.parentFolder().equals(parentFolderId)).
                collect(Collectors.toList());
    }
}
