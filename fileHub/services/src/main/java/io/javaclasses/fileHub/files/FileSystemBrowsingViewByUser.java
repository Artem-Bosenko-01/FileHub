package io.javaclasses.fileHub.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.View;
import io.javaclasses.fileHub.users.UserID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 *
 * */
public class FileSystemBrowsingViewByUser implements View<FileSystemBrowsingQuery<UserID>, List<File>> {

    private final FileStorageInMemory fileStorage;
    private final Logger logger = LoggerFactory.getLogger(FileSystemBrowsingViewByUser.class);

    public FileSystemBrowsingViewByUser(FileStorageInMemory fileStorage){
        this.fileStorage = Preconditions.checkNotNull(fileStorage);
    }

    @Override
    public List<File> handle(FileSystemBrowsingQuery<UserID> query) throws InvalidHandleCommandException {
        if(logger.isInfoEnabled()){
            logger.info("Start read all files for " + query.id());
        }
        try {
            return fileStorage.findAllFilesByUserID(query.id());
        } catch (NotExistIDException e) {
            if(logger.isErrorEnabled()){
                logger.error(e.getMessage());
            }
            throw new InvalidHandleCommandException(e.getMessage());
        }

    }
}
