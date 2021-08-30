package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.services.View;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.Folder;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * This is service to get existed file in authenticated user's directory by {@link FileId id}.
 */
public class GetFolderById extends View<GetFolderByIdQuery, GetFolderByIdDto> {

    private static final Logger logger = LoggerFactory.getLogger(GetFolderById.class);

    private final FolderStorage folderStorageInMemory;

    public GetFolderById(FolderStorage userStorage, AuthorizationStorage authorizationStorage) {

        super(Preconditions.checkNotNull(authorizationStorage));
        this.folderStorageInMemory = Preconditions.checkNotNull(userStorage);
    }

    @Override
    protected GetFolderByIdDto doHandle(GetFolderByIdQuery query) throws InvalidHandleCommandException {

        if (logger.isInfoEnabled()) {
            logger.info("Start get directory for user: " + query.owner() + " and name: " + query.id());
        }

        Optional<Folder> folder = folderStorageInMemory.findFolderById(query.id(), query.owner());

        if (folder.isPresent()) {

            if (logger.isInfoEnabled()) {
                logger.info("Read folder by User: " + query.owner() +
                        ". And name: " + query.id() + ". Was successful");
            }

            return new GetFolderByIdDto(folder.get().id());

        } else {

            if (logger.isErrorEnabled()) {
                logger.error("Folder with name doesn't exist " + query.id() + ".User " + query.owner());
            }

            throw new InvalidHandleCommandException(
                    "Folder with name doesn't exist " + query.id() + ".User " + query.owner());
        }

    }
}
