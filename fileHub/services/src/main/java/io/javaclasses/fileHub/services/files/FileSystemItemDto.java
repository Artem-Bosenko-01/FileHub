package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;
import com.google.common.net.MediaType;
import io.javaclasses.fileHub.persistent.files.FolderId;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Data about folder after successful execution {@link GetFolderById get folder} or
 * {@link GetRootFolder get root folder} processes.
 */
public final class FileSystemItemDto {

    private final String id;

    private final String name;

    private final String type;

    private final Integer size;

    private String mimeType;

    @Nullable
    private String parentFolderId;

    public FileSystemItemDto(String id, String name, Integer itemSize, ItemType type, @Nullable FolderId parentFolderId) {

        this.id = checkNotNull(id);

        this.name = checkNotNull(name);

        this.size = checkNotNull(itemSize);

        this.type = checkNotNull(type.typeName());

        if (parentFolderId != null) {

            this.parentFolderId = parentFolderId.toString();
        }

    }

    public FileSystemItemDto setMimeType(MediaType mimeType) {

        this.mimeType = Preconditions.checkNotNull(mimeType).toString();
        return this;
    }

    public String id() {

        return id;
    }

    public String name() {

        return name;
    }

    public String type() {

        return type;
    }

    public Integer itemsAmount() {

        return size;
    }

    public String parentFolderId() {

        return parentFolderId;
    }
}
