package io.javaclasses.fileHub.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 *
 * */
public class FileSystemBrowsingViewByFolderAndUser implements View<FileSystemUserAndFolderBrowsingQuery, List<File>> {

    private final FileStorageInMemory fileStorage;
    private final Logger logger = LoggerFactory.getLogger(FileSystemBrowsingViewByFolderAndUser.class);

    public FileSystemBrowsingViewByFolderAndUser(FileStorageInMemory fileStorage){
        this.fileStorage = Preconditions.checkNotNull(fileStorage);
    }

    @Override
    public List<File> handle(FileSystemUserAndFolderBrowsingQuery query) throws InvalidHandleCommandException {

        if(logger.isInfoEnabled()){
            logger.info("Start read all files for user: " + query.userID()+" and folder: " + query.folderID());
        }

        try {
            return fileStorage.findAllFilesByFolderIDAndUserID(query.folderID(),query.userID());
        } catch (NotExistIDException e) {
            if(logger.isErrorEnabled()){
                logger.error(e.getMessage());
            }
            throw new InvalidHandleCommandException(e.getMessage());
        }
    }
}
