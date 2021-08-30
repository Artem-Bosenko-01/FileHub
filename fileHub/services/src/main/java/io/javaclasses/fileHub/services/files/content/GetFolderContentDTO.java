package io.javaclasses.fileHub.services.files.content;

import io.javaclasses.fileHub.persistent.files.File;
import io.javaclasses.fileHub.persistent.files.Folder;
import io.javaclasses.fileHub.persistent.files.FolderId;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This is object, that contains data after successful
 * execution {@link GetFolderContent get folder's content process}.
 */
public final class GetFolderContentDTO {

    private final List<Folder> folders;
    private final List<File> files;

    public GetFolderContentDTO(List<Folder> folders, List<File> files) {

        this.folders = checkNotNull(folders);
        this.files = checkNotNull(files);

    }

    public List<Folder> folders() {

        return Collections.unmodifiableList(folders);
    }

    public List<File> files() {

        return Collections.unmodifiableList(files);
    }
}
