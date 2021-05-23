package io.javaclasses.fileHub.files;

import io.javaclasses.fileHub.AbstractInMemoryStorage;
import io.javaclasses.fileHub.NotExistIDException;
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
    public List<File> findAllByUserID(UserID id) throws NotExistIDException {
        if(records().values().stream().noneMatch(file -> file.owner().equals(id))) throw new NotExistIDException("User with " + id + " not exist");
        else return records().values().stream().filter(file -> file.owner().equals(id)).collect(Collectors.toList());
    }

}
