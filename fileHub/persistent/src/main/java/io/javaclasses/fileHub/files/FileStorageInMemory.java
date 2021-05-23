package io.javaclasses.fileHub.files;

import io.javaclasses.fileHub.AbstractInMemoryStorage;

import java.util.Optional;

public class FileStorageInMemory extends AbstractInMemoryStorage<FileID,File>
        implements FileStorage {
    @Override
    public Optional<File> findByName(String name) {
        return records().values().
                stream().
                filter(file -> file.name().equals(name)).
                findFirst();
    }
}
