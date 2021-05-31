package io.javaclasses.fileHub.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.DuplicatedIDException;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.SecuredProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is service to create new file without file's content in authenticated user's directory.
 */
public class CreateFileProcess implements SecuredProcess<CreateFileCommand, CreateFileDTO> {

    private final FileStorage fileStorage;
    private final Logger logger = LoggerFactory.getLogger(CreateFileProcess.class);

    public CreateFileProcess(FileStorage fileStorage){
        this.fileStorage = Preconditions.checkNotNull(fileStorage);
    }

    @Override
    public CreateFileDTO handle(CreateFileCommand inputCommand) throws InvalidHandleCommandException {
        if(logger.isInfoEnabled()){
            logger.info("Start create file " + inputCommand.name());
        }

        File file = new File(new FileID(inputCommand.name(),inputCommand.owner(),inputCommand.folder()));
        file.setUserID(inputCommand.owner());
        file.setMimeType(inputCommand.mimeType());
        file.setName(inputCommand.name());
        file.setFolder(inputCommand.folder());
        file.setSize(0);

       try {
            fileStorage.create(file);

            if(logger.isInfoEnabled()){
                logger.info("Created file was successful. id: " + file.id());
            }

            return new CreateFileDTO(file.id(),file.name(),file.mimeType(), file.owner(), inputCommand.folder());
        } catch (DuplicatedIDException e) {
            if(logger.isErrorEnabled()){
                logger.error(e.getMessage());
            }
            throw new InvalidHandleCommandException(e.getMessage());
        }
    }
}
