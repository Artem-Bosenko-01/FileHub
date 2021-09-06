package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FolderId;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Data about folder after successful execution {@link GetFolderById get folder} or
 * {@link GetRootFolder get root folder} processes.
 */
public final class GetFolderDto {

    private final String id;

    private final String name;

    private final String type = "folder";

    private final Integer itemsAmount;

    @Nullable
    private final FolderId parentFolderId;

    public GetFolderDto(String id, String name, Integer itemsAmount, @Nullable FolderId parentFolderId) {

        this.id = checkNotNull(id);

        this.name = checkNotNull(name);

        this.itemsAmount = checkNotNull(itemsAmount);

        this.parentFolderId = parentFolderId;

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

        return itemsAmount;
    }

    public FolderId parentFolderId() {

        return parentFolderId;
    }
}
