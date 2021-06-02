package io.javaclasses.fileHub.services.files.content;

import io.javaclasses.fileHub.persistent.files.File;
import io.javaclasses.fileHub.persistent.files.Folder;
import io.javaclasses.fileHub.persistent.files.FolderId;

import javax.annotation.Nullable;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This is object, that contains data after successful
 * execution {@link GettingFolderContent get folder's content process}.
 */
public final class GetFolderContentDTO {

    private final FolderId parentFolder;
    private final List<Folder> folders;
    private final List<File> files;

    public GetFolderContentDTO(@Nullable FolderId parentFolder, List<Folder> folders, List<File> files) {

        this.parentFolder = parentFolder;
        this.folders = checkNotNull(folders);
        this.files = checkNotNull(files);

    }

    public FolderId parentFolder() {
        return parentFolder;
    }

    public List<Folder> folders() {
        return folders;
    }

    public List<File> files() {
        return files;
    }
}
