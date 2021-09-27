package io.javaclasses.fileHub.services.files.content;

import io.javaclasses.fileHub.services.files.FileSystemItemDto;

import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Result data after successful execution {@link GetFolderContent get folder's content process}.
 */
public final class GetFolderContentDTO {

    private final List<FileSystemItemDto> folderContent;

    public GetFolderContentDTO(List<FileSystemItemDto> folders) {

        this.folderContent = checkNotNull(folders);

    }

    public List<FileSystemItemDto> content() {

        return Collections.unmodifiableList(folderContent);
    }
}
