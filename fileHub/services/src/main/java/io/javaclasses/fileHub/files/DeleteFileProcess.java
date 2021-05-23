package io.javaclasses.fileHub.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.SecuredProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * */
public class DeleteFileProcess implements SecuredProcess<DeleteFileCommand, DeleteFileDTO> {

    private final FileStorageInMemory fileStorage;
    private final Logger logger = LoggerFactory.getLogger(DeleteFileProcess.class);

    public DeleteFileProcess(FileStorageInMemory fileStorage){
        this.fileStorage = Preconditions.checkNotNull(fileStorage);
    }

    @Override
    public DeleteFileDTO handle(DeleteFileCommand inputCommand) throws InvalidHandleCommandException {
        if(logger.isInfoEnabled()){
            logger.info("Start delete file " + inputCommand.id());
        }
        try {
            fileStorage.delete(inputCommand.id());
            if(logger.isInfoEnabled()){
                logger.info("Deleted"+ inputCommand.id() +" was successful");
            }
            return new DeleteFileDTO(inputCommand.id());
        } catch (NotExistIDException e) {
            if(logger.isErrorEnabled()){
                logger.error(e.getMessage());
            }
            throw new InvalidHandleCommandException(e.getMessage());
        }
    }
}
