package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.NotExistedItemException;
import io.javaclasses.fileHub.persistent.files.File;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.FileStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationUsers;
import io.javaclasses.fileHub.persistent.users.tokens.UserAuthToken;
import io.javaclasses.fileHub.services.SecuredUserProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Updates information about existed file in authenticated user's folder by {@link FileId id}.
 */
@Component
public class UpdateFile extends SecuredUserProcess<UpdateFileCommand, FileId> {

    private static final Logger logger = LoggerFactory.getLogger(UpdateFile.class);

    private final FileStorage fileStorage;

    private final AuthorizationStorage authorizationStorage;

    @Autowired
    public UpdateFile(@Qualifier("fileStorageInDatabase") FileStorage fileStorage,
                      @Qualifier("authorizationStorageInDatabase") AuthorizationStorage authorizationStorage) {

        super(checkNotNull(authorizationStorage));

        this.fileStorage = checkNotNull(fileStorage);

        this.authorizationStorage = checkNotNull(authorizationStorage);
    }

    @Override
    protected FileId doHandle(UpdateFileCommand inputCommand)
            throws FileNotFoundException, UsersTokenNotFoundException, FileNameAlreadyUsedException {

        if (fileStorage.isFIleNameAlreadyExist(inputCommand.name())) {

            if (logger.isErrorEnabled()) {
                logger.error("File name: " + inputCommand.name() + " already used.");
            }

            throw new FileNameAlreadyUsedException(inputCommand.name());
        }

        Optional<AuthorizationUsers> owner = authorizationStorage.
                findByID(new UserAuthToken(inputCommand.token().value()));

        if (owner.isPresent()) {

            if (logger.isInfoEnabled()) {
                logger.info("Start update information for file " + inputCommand.id());
            }

            File file = new File(inputCommand.id());
            file.setName(inputCommand.name());
            file.setSize(inputCommand.size());
            file.setMimeType(inputCommand.mimeType());
            file.setFolder(inputCommand.folder());
            file.setUserID(owner.get().userID());

            try {

                fileStorage.update(file);

                if (logger.isInfoEnabled()) {
                    logger.info("Updating file was successful. id: " + file.id());
                }

                return file.id();

            } catch (NotExistedItemException e) {

                if (logger.isErrorEnabled()) {
                    logger.error(e.getMessage());
                }

                throw new FileNotFoundException(inputCommand.id());
            }

        } else {

            if (logger.isErrorEnabled()) {

                logger.error("Cannot find user by token: " + inputCommand.token());
            }

            throw new UsersTokenNotFoundException(inputCommand.token());
        }
    }
}