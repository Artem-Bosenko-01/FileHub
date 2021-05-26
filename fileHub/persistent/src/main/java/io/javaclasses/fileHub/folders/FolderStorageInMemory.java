package io.javaclasses.fileHub.folders;

import io.javaclasses.fileHub.AbstractInMemoryStorage;
import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.users.UserID;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FolderStorageInMemory extends AbstractInMemoryStorage<FolderID, Folder> implements FolderStorage{
    @Override
    public List<Folder> findAllByUserID(UserID id) throws NotExistIDException {
        if(records().values().stream().noneMatch(file -> file.owner().equals(id)))
            throw new NotExistIDException("User with " + id + " not exist");
        return records().values().stream().filter(folder -> folder.owner().equals(id)).collect(Collectors.toList());
    }

    @Override
    public Optional<Folder> findFolderByName(String name, UserID owner) {
        return records().values().stream().
                filter(folder -> folder.name().equals(name) && folder.owner().equals(owner)).
                findFirst();
    }
}
