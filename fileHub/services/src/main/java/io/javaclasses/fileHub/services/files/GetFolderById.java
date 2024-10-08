package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.files.Folder;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationUsers;
import io.javaclasses.fileHub.persistent.users.tokens.UserAuthToken;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Service for getting an existed folder in authenticated user's directory by {@link FolderId id}.
 */
@Component
public class GetFolderById extends View<GetFolderByIdQuery, FileSystemItemDto> {

    private static final Logger logger = LoggerFactory.getLogger(GetFolderById.class);

    private final FolderStorage folderStorageInMemory;

    private final AuthorizationStorage authorizationStorage;

    @Autowired
    public GetFolderById(@Qualifier("folderStorageInDatabase") FolderStorage userStorage,
                         @Qualifier("authorizationStorageInDatabase") AuthorizationStorage authorizationStorage) {

        super(Preconditions.checkNotNull(authorizationStorage));

        this.folderStorageInMemory = Preconditions.checkNotNull(userStorage);

        this.authorizationStorage = authorizationStorage;
    }

    @Override
    protected FileSystemItemDto doHandle(GetFolderByIdQuery query) throws InvalidCommandHandlingException {

        Optional<AuthorizationUsers> owner = authorizationStorage.findByID(new UserAuthToken(query.token().value()));

        if (owner.isPresent()) {

            UserId userId = owner.get().userID();

            if (logger.isInfoEnabled()) {
                logger.info("Start get directory for user: " + userId.value() + " and name: " + query.id());
            }

            Optional<Folder> optionalFolder = folderStorageInMemory.findByID(new FolderId(query.id()));

            if (optionalFolder.isPresent()) {

                Folder folder = optionalFolder.get();

                if (logger.isInfoEnabled()) {
                    logger.info("Read folder by User: " + folder.owner().value() +
                            ". And name: " + folder.name() + ". Was successful");
                }

                return new FileSystemItemDto(
                        folder.id().value(),
                        folder.name(),
                        folder.itemsAmount(),
                        ItemType.FOLDER,
                        folder.parentFolder());

            } else {

                if (logger.isErrorEnabled()) {

                    logger.error("Cannot find folder by id: " + query.id());
                }

                throw new FolderByIdNotFoundHandlingException(query.id());

            }

        } else {

            if (logger.isErrorEnabled()) {

                logger.error("Cannot find user by token: " + query.token());
            }

            throw new UsersTokenNotFoundException(query.token());
        }

    }
}
