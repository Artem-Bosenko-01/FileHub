package io.javaclasses.fileHub.persistent.files.content;

import io.javaclasses.fileHub.persistent.AbstractInMemoryStorage;
import io.javaclasses.fileHub.persistent.files.FileId;

/**
 * A repository for managing the content of files that are stored in the RAM.
 */
public class FileContentStorageInMemory extends AbstractInMemoryStorage<FileId, FileContent> implements FIleContentStorage {
}
