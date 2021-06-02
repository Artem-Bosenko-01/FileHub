package io.javaclasses.fileHub.persistent.files;

import io.javaclasses.fileHub.persistent.AbstractInMemoryStorage;
import io.javaclasses.fileHub.persistent.NotExistUserIdException;
import io.javaclasses.fileHub.persistent.users.UserId;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FolderStorageInMemory extends AbstractInMemoryStorage<FolderId, Folder> implements FolderStorage {

    @Override
    public List<Folder> findAllFoldersByParentFolderId(FolderId parentId) throws NotExistUserIdException {
        if (records().values().stream().noneMatch(folder -> folder.id().equals(parentId))) {
            throw new NotExistUserIdException("Parent folder doesn't exist: " + parentId);
        }

        return records().values().stream().
                filter(folder -> folder.parentFolder() != null && folder.parentFolder().equals(parentId)).
                collect(Collectors.toList());

    }

    @Override
    public Optional<FolderId> findParentFolderByChildId(FolderId childId) throws NotExistUserIdException {

        Optional<Folder> findFolder = records().values().stream().
                filter(folder -> folder.id().equals(childId)).
                findFirst();

        if (findFolder.isPresent()) {
            if (findFolder.get().parentFolder() != null) {
                return Optional.of(findFolder.get().parentFolder());
            }
            else return Optional.empty();
        } else throw new NotExistUserIdException("Folder id: " + childId + " doesn't exist");
    }

    @Override
    public Optional<Folder> findFolderByName(String name, UserId owner) {

        return records().values().stream().
                filter(folder -> folder.name().equals(name) && folder.owner().equals(owner)).
                findFirst();
    }

    @Override
    public int getSizeRecordsList() {
        return records().size();
    }
}
