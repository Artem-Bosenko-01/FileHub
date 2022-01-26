package io.javaclasses.fileHub.persistent.files;

import io.javaclasses.fileHub.persistent.DataRecord;
import io.javaclasses.fileHub.persistent.users.UserId;

import javax.annotation.Nullable;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The implementation of base {@link DataRecord record} for managing data about folder with {@link FolderId id}
 * in the FileHub file system.
 */
public final class Folder implements DataRecord<FolderId> {

    private final FolderId id;
    private String name;
    private UserId owner;
    private Long itemsAmount;
    @Nullable
    private String parentFolder;

    public Folder(String id) {

        this.id = new FolderId(checkNotNull(id));
    }

    @Override
    public FolderId id() {

        return id;
    }

    public String name() {

        return name;
    }

    public void setName(String name) {

        this.name = checkNotNull(name);
    }

    public UserId owner() {

        return owner;
    }

    public void setOwner(UserId owner) {

        this.owner = checkNotNull(owner);
    }

    @Nullable
    public String parentFolder() {

        return parentFolder;
    }

    public void setParentFolder(@Nullable String parentFolder) {

        this.parentFolder = parentFolder;
    }

    public Long itemsAmount() {

        return itemsAmount;
    }

    public void setItemsAmount(Long itemsAmount) {

        this.itemsAmount = checkNotNull(itemsAmount);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Folder folder = (Folder) o;

        return id.equals(folder.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
