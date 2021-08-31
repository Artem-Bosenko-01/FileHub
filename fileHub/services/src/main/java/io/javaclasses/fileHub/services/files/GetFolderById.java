package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.files.Folder;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationUsers;
import io.javaclasses.fileHub.persistent.users.tokens.UserAuthToken;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Service to get an existed folder in authenticated user's directory by {@link FolderId id}.
 */
public class GetFolderById extends View<GetFolderByIdQuery, GetFolderDto> {

    private static final Logger logger = LoggerFactory.getLogger(GetFolderById.class);

    private final FolderStorage folderStorageInMemory;

    private final AuthorizationStorage authorizationStorage;

    public GetFolderById(FolderStorage userStorage, AuthorizationStorage authorizationStorage) {

        super(Preconditions.checkNotNull(authorizationStorage));

        this.folderStorageInMemory = Preconditions.checkNotNull(userStorage);

        this.authorizationStorage = authorizationStorage;
    }

    @Override
    protected GetFolderDto doHandle(GetFolderByIdQuery query) throws InvalidCommandHandlingException {

        Optional<AuthorizationUsers> owner = authorizationStorage.findByID(new UserAuthToken(query.token().value()));

        if (owner.isPresent()) {

            if (logger.isInfoEnabled()) {
                logger.info("Start get directory for user: " + owner.get().userID() + " and name: " + query.id());
            }

            Optional<Folder> optionalFolder = folderStorageInMemory.findFolderById(query.id(), owner.get().userID());

            if (optionalFolder.isPresent()) {

                Folder folder = optionalFolder.get();

                if (logger.isInfoEnabled()) {
                    logger.info("Read folder by User: " + folder.owner() +
                            ". And name: " + folder.name() + ". Was successful");
                }

                return new GetFolderDto(
                        folder.id().toString(),
                        folder.name(),
                        folder.itemsAmount(),
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

            throw new UsersTokenNotFoundHandlingException(query.token());
        }

    }
}
