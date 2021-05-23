package io.javaclasses.fileHub.files;

import io.javaclasses.fileHub.Storage;

import java.util.Optional;

public interface FileStorage extends Storage<FileID,File> {
    Optional<File> findByName(String name);
}
