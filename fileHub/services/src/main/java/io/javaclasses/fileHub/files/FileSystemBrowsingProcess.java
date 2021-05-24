package io.javaclasses.fileHub.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.SecuredProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 *
 * */
public class FileSystemBrowsingProcess implements SecuredProcess<FileSystemBrowsingQuery, List<File>> {

    private final FileStorageInMemory fileStorage;
    private final Logger logger = LoggerFactory.getLogger(FileSystemBrowsingProcess.class);

    public FileSystemBrowsingProcess(FileStorageInMemory fileStorage){
        this.fileStorage = Preconditions.checkNotNull(fileStorage);
    }

    @Override
    public List<File> handle(FileSystemBrowsingQuery query) throws InvalidHandleCommandException {
        if(logger.isInfoEnabled()){
            logger.info("Start read all files for " + query.id());
        }
        try {
            return fileStorage.findAllByUserID(query.id());
        } catch (NotExistIDException e) {
            e.printStackTrace();
        }
        if(logger.isErrorEnabled()){
            logger.error("User with " + query.id() + " not exist");
        }
        throw new InvalidHandleCommandException("User with " + query.id() + " not exist");
    }
}
