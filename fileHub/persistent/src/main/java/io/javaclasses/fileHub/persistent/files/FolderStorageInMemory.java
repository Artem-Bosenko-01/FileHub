package io.javaclasses.fileHub.persistent.files;

import io.javaclasses.fileHub.persistent.AbstractInMemoryStorage;
import io.javaclasses.fileHub.persistent.NotExistedItem;
import io.javaclasses.fileHub.persistent.users.UserId;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FolderStorageInMemory extends AbstractInMemoryStorage<FolderId, Folder> implements FolderStorage {

    @Override
    public List<Folder> findAllFoldersByParentFolderId(String parentId, String owner) throws NotExistedItem {
        if (records().values().stream().noneMatch(folder -> folder.id().toString().equals(parentId))) {
            throw new NotExistedItem("Parent folder doesn't exist: " + parentId);
        }

        return records().values().stream().
                filter(folder -> folder.parentFolder() != null && folder.parentFolder().toString().equals(parentId)).
                collect(Collectors.toList());

    }

    @Override
    public Optional<String> findParentFolderByChildId(FolderId childId) throws NotExistedItem {

        Optional<Folder> findFolder = records().values().stream().
                filter(folder -> folder.id().equals(childId)).
                findFirst();

        if (findFolder.isPresent()) {

            if (findFolder.get().parentFolder() != null) {

                return Optional.of(findFolder.get().parentFolder());

            } else {

                return Optional.empty();
            }

        } else {

            throw new NotExistedItem("Folder id: " + childId + " doesn't exist");
        }
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
}
