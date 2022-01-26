package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.NotExistedItemException;
import io.javaclasses.fileHub.persistent.files.Folder;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
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
 * Updates information about existed folder by {@link FolderId id}.
 */
@Component
public class UpdateFolder extends SecuredUserProcess<UpdateFolderCommand, FolderId> {

    private static final Logger logger = LoggerFactory.getLogger(UpdateFolder.class);

    private final FolderStorage folderStorage;

    private final AuthorizationStorage authorizationStorage;

    @Autowired
    public UpdateFolder(@Qualifier("folderStorageInDatabase") FolderStorage userStorage,
                        @Qualifier("authorizationStorageInDatabase") AuthorizationStorage authorizationStorage) {

        super(checkNotNull(authorizationStorage));

        this.folderStorage = checkNotNull(userStorage);

        this.authorizationStorage = checkNotNull(authorizationStorage);
    }

    @Override
    protected FolderId doHandle(UpdateFolderCommand inputCommand)
            throws FolderNotFoundException, UsersTokenNotFoundException, FolderNameAlreadyUsedException {

        if (folderStorage.isFolderNameAlreadyExist(inputCommand.name(), inputCommand.parentFolder())) {

            if (logger.isErrorEnabled()) {
                logger.error("Folder name: " + inputCommand.name() + " already used.");
            }

            throw new FolderNameAlreadyUsedException(inputCommand.name());
        }

        Optional<AuthorizationUsers> owner = authorizationStorage.
                findByID(new UserAuthToken(inputCommand.token().value()));

        if (owner.isPresent()) {
            if (logger.isInfoEnabled()) {
                logger.info("Start update information for folder " + inputCommand.id());
            }

            Folder folder = new Folder(inputCommand.id());
            folder.setParentFolder(inputCommand.parentFolder());
            folder.setOwner(owner.get().userID());
            folder.setItemsAmount(inputCommand.itemsAmount());
            folder.setName(inputCommand.name());

            try {

                folderStorage.update(folder);

                if (logger.isInfoEnabled()) {
                    logger.info("Updating folder was successful. id: " + folder.id().value());
                }

                return folder.id();

            } catch (NotExistedItemException e) {

                if (logger.isErrorEnabled()) {
                    logger.error(e.getMessage());
                }

                throw new FolderNotFoundException(inputCommand.id());
            }

        } else {

            if (logger.isErrorEnabled()) {

                logger.error("Cannot find user by token: " + inputCommand.token());
            }

            throw new UsersTokenNotFoundException(inputCommand.token());
        }
    }
}
