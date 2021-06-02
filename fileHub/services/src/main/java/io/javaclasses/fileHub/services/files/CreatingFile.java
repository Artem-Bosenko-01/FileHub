package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.DuplicatedUserIdException;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.services.SecuredProcess;
import io.javaclasses.fileHub.persistent.files.File;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.FileStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This is service to create new file without file's content in authenticated user's directory.
 */
public class CreatingFile implements SecuredProcess<CreateFileCommand, FileId> {

    private static final Logger logger = LoggerFactory.getLogger(CreatingFile.class);

    private final FileStorage fileStorage;

    public CreatingFile(FileStorage fileStorage) {
        this.fileStorage = checkNotNull(fileStorage);
    }

    @Override
    public FileId handle(CreateFileCommand inputCommand) throws InvalidHandleCommandException {

        if (logger.isInfoEnabled()) {
            logger.info("Start create file " + inputCommand.name());
        }

        File file = new File(new FileId(inputCommand.name(), inputCommand.owner(), inputCommand.folder()));
        file.setUserID(inputCommand.owner());
        file.setMimeType(inputCommand.mimeType());
        file.setName(inputCommand.name());
        file.setFolder(inputCommand.folder());
        file.setSize(0);

        try {

            fileStorage.create(file);

            if (logger.isInfoEnabled()) {
                logger.info("Created file was successful. id: " + file.id());
            }

            return file.id();

        } catch (DuplicatedUserIdException e) {

            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw new InvalidHandleCommandException(e.getMessage());

        }

    }

}
