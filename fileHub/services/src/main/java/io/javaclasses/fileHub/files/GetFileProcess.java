package io.javaclasses.fileHub.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.SecuredProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 *
 */
public class GetFileProcess implements SecuredProcess<GetFileCommand, CreateFileDTO> {

    private final FileStorageInMemory fileStorage;
    private final Logger logger = LoggerFactory.getLogger(GetFileProcess.class);

    public GetFileProcess(FileStorageInMemory fileStorage) {
        this.fileStorage = Preconditions.checkNotNull(fileStorage);
    }

    @Override
    public CreateFileDTO handle(GetFileCommand inputCommand) throws InvalidHandleCommandException {
        if (logger.isInfoEnabled()) {
            logger.info("Start read info about " + inputCommand.id() + " file");
        }

        try {
            Optional<File> file = fileStorage.findByID(inputCommand.id());
            if (file.isPresent()) {
                if (logger.isInfoEnabled()) {
                    logger.info("Read info was successful: " + file.get().name());
                }
                return new CreateFileDTO(file.get().id(), file.get().name(), file.get().mimeType(), file.get().owner());
            } else {
                if (logger.isErrorEnabled()) logger.error("File with " + inputCommand.id() + " not exist");
                throw new InvalidHandleCommandException("File with " + inputCommand.id() + " not exist");
            }
        } catch (NotExistIDException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw new InvalidHandleCommandException(e.getMessage());
        }

    }
}
