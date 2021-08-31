package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.Folder;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationUsers;
import io.javaclasses.fileHub.persistent.users.tokens.UserAuthToken;
import io.javaclasses.fileHub.services.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Service to get the root folder for authenticated user.
 */
public class GetRootFolder extends View<GetRootFolderQuery, GetFolderDto> {

    private static final Logger logger = LoggerFactory.getLogger(GetFolderById.class);

    private final FolderStorage folderStorage;

    private final AuthorizationStorage authorizationStorage;

    protected GetRootFolder(AuthorizationStorage authorizationStorage, FolderStorage folderStorage) {

        super(authorizationStorage);

        this.folderStorage = folderStorage;

        this.authorizationStorage = authorizationStorage;
    }

    @Override
    protected GetFolderDto doHandle(GetRootFolderQuery query)
            throws RootFolderNotFoundHandlingException, UsersTokenNotFoundHandlingException {

        Optional<AuthorizationUsers> owner = authorizationStorage.findByID(new UserAuthToken(query.token().value()));

        if (owner.isPresent()) {

            if (logger.isInfoEnabled()) {

                logger.info("Start get root folder for user: " + owner.get().userID());
            }

            Optional<Folder> rootFolder = folderStorage.findRootFolderByUserId(owner.get().userID());

            if (rootFolder.isPresent()) {

                if (logger.isInfoEnabled()) {
                    logger.info("Read root folder: " + rootFolder.get().name() + ". For user: "
                            + rootFolder.get().owner().toString() + ". Was successful");
                }

                return new GetFolderDto(rootFolder.get().id().toString(),
                        rootFolder.get().name(),
                        rootFolder.get().itemsAmount(),
                        rootFolder.get().parentFolder());

            } else {

                if (logger.isErrorEnabled()) {

                    logger.error("Root folder doesn't exist for user: " + owner.get().userID().toString());
                }

                throw new RootFolderNotFoundHandlingException(owner.get().userID());

            }

        } else {

            if (logger.isErrorEnabled()) {

                logger.error("Cannot find user by token: " + query.token());
            }

            throw new UsersTokenNotFoundHandlingException(query.token());

        }

    }
}