package io.javaclasses.fileHub.folders.content;

import io.javaclasses.fileHub.DataRecord;
import io.javaclasses.fileHub.files.File;
import io.javaclasses.fileHub.folders.Folder;
import io.javaclasses.fileHub.folders.FolderID;

import java.util.List;

public final class FolderContent implements DataRecord<FolderID> {

    private final FolderID id;
    private final FolderID parentFolder;
    private final List<Folder> folders;
    private final List<File> files;

    public FolderContent(FolderID id, FolderID parentFolder, List<Folder> folders, List<File> files) {
        this.id = id;
        this.parentFolder = parentFolder;
        this.folders = folders;
        this.files = files;
    }

    public List<Folder> folders() {
        return folders;
    }

    public List<File> files() {
        return files;
    }

    public FolderID parentFolder() {
        return parentFolder;
    }

    @Override
    public FolderID id() {
        return id;
    }
}
