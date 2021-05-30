package io.javaclasses.fileHub.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.SecuredProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is service to updating information about existed file in authenticated user's directory.
 */
public class UpdateFileProcess implements SecuredProcess<UpdateFileCommand, CreateFileDTO> {

    private final FileStorageInMemory fileStorage;
    private final Logger logger = LoggerFactory.getLogger(UpdateFileProcess.class);

    public UpdateFileProcess(FileStorageInMemory fileStorage){
        this.fileStorage = Preconditions.checkNotNull(fileStorage);
    }

    @Override
    public CreateFileDTO handle(UpdateFileCommand inputCommand) throws InvalidHandleCommandException {
        if(logger.isInfoEnabled()){
            logger.info("Start update information for file " + inputCommand.id());
        }
        File file = new File(inputCommand.id());
        file.setName(inputCommand.name());
        file.setSize(inputCommand.size());
        file.setMimeType(inputCommand.mimeType());
        file.setFolder(inputCommand.folder());
        file.setUserID(inputCommand.owner());

        try {
            fileStorage.update(file);

            if(logger.isInfoEnabled()){
                logger.info("Updating file was successful. id: " + file.id());
            }
            return new CreateFileDTO(file.id(),file.name(),file.mimeType(),file.owner(), file.folder());
        } catch (NotExistIDException e) {

            if(logger.isErrorEnabled()){
                logger.error(e.getMessage());
            }
            throw new InvalidHandleCommandException(e.getMessage());
        }
    }
}
