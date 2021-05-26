package io.javaclasses.fileHub.folders.content;

import io.javaclasses.fileHub.AbstractInMemoryStorage;
import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.folders.FolderID;

public class FolderContentStorageInMemory extends AbstractInMemoryStorage<FolderID, FolderContent>
        implements FolderContentStorage {

    @Override
    public FolderContent getAllFolderContentByID(FolderID id) throws NotExistIDException{
        if(records().values().stream().noneMatch(folderContent -> folderContent.id().equals(id)))
            throw new NotExistIDException("Folder doesn't exist id " + id);
        return records().values().stream().filter(folderContent -> folderContent.id().equals(id)).findFirst().orElse(null);
    }
}
