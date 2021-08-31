package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.DuplicatedUserIdException;
import io.javaclasses.fileHub.persistent.files.File;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.FileStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.SecuredUserProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Service to create a new file without file's content in authenticated user's directory.
 */
public class CreateFile extends SecuredUserProcess<CreateFileCommand, FileId> {

    private static final Logger logger = LoggerFactory.getLogger(CreateFile.class);

    private final FileStorage fileStorage;

    public CreateFile(FileStorage fileStorage, AuthorizationStorage authorizationStorage) {

        super(Preconditions.checkNotNull(authorizationStorage));

        this.fileStorage = checkNotNull(fileStorage);

    }


    @Override
    protected FileId doHandle(CreateFileCommand inputCommand) throws InvalidCommandHandlingException {

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

            throw new InvalidCommandHandlingException(e.getMessage());

        }

    }

}
