package io.javaclasses.fileHub.persistent.files.content;

import io.javaclasses.fileHub.persistent.Storage;
import io.javaclasses.fileHub.persistent.files.FileId;

/**
 * The {@link Storage storage} that contains instruments for managing {@link FileContent files content} in the FileHub application.
 */
public interface FIleContentStorage extends Storage<FileId, FileContent> {
}
