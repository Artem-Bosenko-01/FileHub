package io.javaclasses.fileHub.folders;

import io.javaclasses.fileHub.AbstractInMemoryStorage;
import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.users.UserID;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FolderStorageInMemory extends AbstractInMemoryStorage<FolderID, Folder> implements FolderStorage{

    @Override
    public List<Folder> findAllFoldersByParentFolderId(FolderID parentId) throws NotExistIDException {
        if(records().values().stream().noneMatch(folder -> folder.id().equals(parentId)))
            throw new NotExistIDException("Parent folder doesn't exist: " + parentId);

        return records().values().stream().
                filter(folder -> folder.parentFolder().equals(Optional.of(parentId))).
                collect(Collectors.toList());
    }

    @Override
    public Optional<FolderID> findParentFolderByChildId(FolderID childId) throws NotExistIDException {

        Optional<Folder> findFolder = records().values().stream().
                filter(folder -> folder.id().equals(childId)).
                findFirst();

        if(findFolder.isPresent()){
            return findFolder.get().parentFolder();
        }else throw new NotExistIDException("Folder doesn't exist: " + childId);
    }

    @Override
    public Optional<Folder> findFolderByName(String name, UserID owner) {
        return records().values().stream().
                filter(folder -> folder.name().equals(name) && folder.owner().equals(owner)).
                findFirst();
    }

    @Override
    public int getSizeRecordsList() {
        return records().size();
    }
}
