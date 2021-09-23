package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.DuplicatedIdException;
import io.javaclasses.fileHub.persistent.NotExistedItemException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Service to create a new empty folder by authenticated user.
 */
@Component
public class CreateFolder extends SecuredUserProcess<CreateFolderCommand, FolderId> {

    private static final Logger logger = LoggerFactory.getLogger(CreateFolder.class);

    private final FolderStorage folderStorage;

    private final AuthorizationStorage authorizationStorage;

    @Autowired
    public CreateFolder(@Qualifier("folderStorageInDatabase") FolderStorage userStorage,
                        @Qualifier("authorizationStorageInDatabase") AuthorizationStorage authorizationStorage) {

        super(authorizationStorage);

        this.folderStorage = checkNotNull(userStorage);
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

            String folderId = query.name() + userId.value() + query.parentFolder();

            Folder folder = new Folder(folderId);
            folder.setParentFolder(query.parentFolder());
            folder.setName(query.name());
            folder.setItemsAmount(query.itemsAmount());
            folder.setOwner(userId);

            try {

                folderStorage.create(folder);

                if (logger.isInfoEnabled()) {
                    logger.info("Created folder was successful. id: " + folder.id().value());
                }

                folderStorage.increaseItemsAmount(Objects.requireNonNull(folder.parentFolder()));

                return folder.id();

            } catch (DuplicatedIdException | NotExistedItemException e) {

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
