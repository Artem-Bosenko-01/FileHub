package io.javaclasses.fileHub.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.View;
import io.javaclasses.fileHub.folders.FolderID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FileSystemBrowsingViewByFolder implements View<FileSystemBrowsingQuery<FolderID>, List<File>> {

    private final FileStorageInMemory fileStorage;
    private final Logger logger = LoggerFactory.getLogger(FileSystemBrowsingViewByUser.class);

    public FileSystemBrowsingViewByFolder(FileStorageInMemory fileStorage){
        this.fileStorage = Preconditions.checkNotNull(fileStorage);
    }

    @Override
    public List<File> handle(FileSystemBrowsingQuery<FolderID> query) throws InvalidHandleCommandException {
        if(logger.isInfoEnabled()){
            logger.info("Start read all files for " + query.id());
        }
        try {
            return fileStorage.findAllFilesByFolderID(query.id());
        } catch (NotExistIDException e) {
            if(logger.isErrorEnabled()){
                logger.error(e.getMessage());
            }
            throw new InvalidHandleCommandException(e.getMessage());
        }

    }
}
