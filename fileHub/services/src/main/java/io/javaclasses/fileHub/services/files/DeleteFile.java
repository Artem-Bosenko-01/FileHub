package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.NotExistedItemException;
import io.javaclasses.fileHub.persistent.files.File;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.FileStorage;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.SecuredUserProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Service to delete an existed file from authenticated user's directory.
 */
@Component
public class DeleteFile extends SecuredUserProcess<DeleteFileCommand, String> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteFile.class);

    private final FileStorage fileStorage;

    private final FolderStorage folderStorage;

    @Autowired
    public DeleteFile(@Qualifier("fileStorageInDatabase") FileStorage fileStorage,
                      @Qualifier("folderStorageInDatabase") FolderStorage folderStorage,
                      @Qualifier("authorizationStorageInDatabase") AuthorizationStorage authorizationStorage) {

        super(checkNotNull(authorizationStorage));

        this.fileStorage = checkNotNull(fileStorage);

        this.folderStorage = checkNotNull(folderStorage);
    }


    @Override
    protected String doHandle(DeleteFileCommand inputCommand) throws FileNotFoundException {

        if (logger.isInfoEnabled()) {
            logger.info("Start delete file " + inputCommand.id());
        }

        try {

            Optional<File> file = fileStorage.findByID(new FileId(inputCommand.id()));

            fileStorage.delete(inputCommand.id());

            if (logger.isInfoEnabled()) {
                logger.info("Deleted " + inputCommand.id() + " was successful");
            }

            folderStorage.decreaseItemsAmount(file.get().folder());

            return inputCommand.id();

        } catch (NotExistedItemException e) {

            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }

            throw new FileNotFoundException(inputCommand.id());
        }

    }
}
