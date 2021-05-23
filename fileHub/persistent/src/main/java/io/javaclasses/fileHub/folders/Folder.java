package io.javaclasses.fileHub.folders;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.DataRecord;
import io.javaclasses.fileHub.users.UserID;

import java.util.Objects;

public final class Folder implements DataRecord<FolderID> {

    private final FolderID id;
    private String name;
    private UserID owner;
    private FolderID parentFolder;

    public Folder(FolderID id) {
        this.id = Preconditions.checkNotNull(id);
    }

    @Override
    public FolderID id() {
        return id;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = Preconditions.checkNotNull(name);
    }

    public UserID owner() {
        return owner;
    }

    public void setOwner(UserID owner) {
        this.owner = Preconditions.checkNotNull(owner);
    }

    public FolderID getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(FolderID parentFolder) {
        this.parentFolder = Preconditions.checkNotNull(parentFolder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Folder folder = (Folder) o;
        return id.equals(folder.id) && name.equals(folder.name) && Objects.equals(owner, folder.owner) && Objects.equals(parentFolder, folder.parentFolder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner, parentFolder);
    }
}
