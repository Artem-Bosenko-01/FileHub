package io.javaclasses.fileHub.persistent.files.content;

import io.javaclasses.fileHub.persistent.AbstractInMemoryStorage;
import io.javaclasses.fileHub.persistent.files.FileId;
import org.springframework.stereotype.Component;

/**
 * A repository for managing the content of files that are stored in the RAM.
 */
@Component
public class FileContentStorageInMemory extends AbstractInMemoryStorage<FileId, FileContent> implements FIleContentStorage {
}
