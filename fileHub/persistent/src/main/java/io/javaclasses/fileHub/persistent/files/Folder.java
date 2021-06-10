package io.javaclasses.fileHub.persistent.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.DataRecord;
import io.javaclasses.fileHub.persistent.users.UserId;

import javax.annotation.Nullable;
import java.util.Objects;

public final class Folder implements DataRecord<FolderId> {

    private final FolderId id;
    private String name;
    private UserId owner;
    @Nullable
    private FolderId parentFolder;

    public Folder(FolderId id) {
        this.id = Preconditions.checkNotNull(id);
    }

    @Override
    public FolderId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = Preconditions.checkNotNull(name);
    }

    public UserId owner() {
        return owner;
    }

    public void setOwner(UserId owner) {
        this.owner = Preconditions.checkNotNull(owner);
    }

    public FolderId parentFolder() {
        return parentFolder;
    }

    public void setParentFolder(@Nullable FolderId parentFolder) {
        this.parentFolder = parentFolder;
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
