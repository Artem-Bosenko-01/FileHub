package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;
import com.google.common.net.MediaType;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Data for transferring information about {@link io.javaclasses.fileHub.persistent.files.Folder folder}
 * or {@link io.javaclasses.fileHub.persistent.files.File file} after successful handling processes
 */
public final class FileSystemItemDto {

    private final String id;

    private final String name;

    private final String type;

    private final Long size;

    private String mimeType;

    @Nullable
    private String parentFolderId;

    public FileSystemItemDto(String id, String name, Long itemSize, ItemType type, @Nullable String parentFolderId) {

        this.id = checkNotNull(id);

        this.name = checkNotNull(name);

        this.size = checkNotNull(itemSize);

        this.type = checkNotNull(type.typeName());

        if (parentFolderId != null) {

            this.parentFolderId = parentFolderId;
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

    public Long itemsAmount() {

        return size;
    }

    public String mimeType() {

        return mimeType;
    }

    @Nullable
    public String parentFolderId() {

        return parentFolderId;
    }
}
