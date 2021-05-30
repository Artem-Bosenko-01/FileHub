package io.javaclasses.fileHub.folders.content;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.files.File;
import io.javaclasses.fileHub.files.content.GetFileContentView;
import io.javaclasses.fileHub.folders.Folder;
import io.javaclasses.fileHub.folders.FolderID;

import java.util.List;

/**
 * This is object, that contains data after successful
 * execution {@link GetFolderContentView get folder's content process}.
 */
public final class GetFolderContentDTO {
    private final FolderID parentFolder;
    private final List<Folder> folders;
    private final List<File> files;

    public GetFolderContentDTO(FolderID parentFolder, List<Folder> folders, List<File> files) {
        this.parentFolder = Preconditions.checkNotNull(parentFolder);
        this.folders = Preconditions.checkNotNull(folders);
        this.files = Preconditions.checkNotNull(files);
    }

    public FolderID parentFolder() {
        return parentFolder;
    }

    public List<Folder> folders() {
        return folders;
    }

    public List<File> files() {
        return files;
    }
}
