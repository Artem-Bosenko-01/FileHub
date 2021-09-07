package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.DuplicatedUserIdException;
import io.javaclasses.fileHub.persistent.files.Folder;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationUsers;
import io.javaclasses.fileHub.persistent.users.tokens.UserAuthToken;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.SecuredUserProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Service to create a new empty folder by authenticated user.
 */
public class CreateFolder extends SecuredUserProcess<CreateFolderCommand, FolderId> {

    private static final Logger logger = LoggerFactory.getLogger(CreateFolder.class);

    private final FolderStorage folderStorageInMemory;

    private final AuthorizationStorage authorizationStorage;

    public CreateFolder(FolderStorage userStorage, AuthorizationStorage authorizationStorage) {

        super(authorizationStorage);

        this.folderStorageInMemory = checkNotNull(userStorage);
        this.authorizationStorage = checkNotNull(authorizationStorage);
    }

    @Override
    protected FolderId doHandle(CreateFolderCommand query) throws InvalidCommandHandlingException {

        Optional<AuthorizationUsers> owner = authorizationStorage.findByID(new UserAuthToken(query.token().value()));

        if (owner.isPresent()) {

            UserId userId = owner.get().userID();

            if (logger.isInfoEnabled()) {
                logger.info("Start create folder " + query.name());
            }

            FolderId id = new FolderId(query.name(), userId);
            Folder folder = new Folder(id);
            folder.setParentFolder(query.parentFolder());
            folder.setName(query.name());
            folder.setItemsAmount(query.itemsAmount());
            folder.setOwner(userId);

            try {

                folderStorageInMemory.create(folder);

                if (logger.isInfoEnabled()) {
                    logger.info("Created folder was successful. id: " + folder.id());
                }

                return folder.id();

            } catch (DuplicatedUserIdException e) {

                if (logger.isErrorEnabled()) {
                    logger.error(e.getMessage());
                }

                throw new InvalidCommandHandlingException(e.getMessage());
            }

        } else {

            if (logger.isErrorEnabled()) {

                logger.error("Cannot find user by token: " + query.token());
            }

            throw new UsersTokenNotFoundException(query.token());
        }
    }
}
