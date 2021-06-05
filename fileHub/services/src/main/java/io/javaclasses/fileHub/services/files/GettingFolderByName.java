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
public class GettingFolderByName extends View<GetFolderByNameQuery, GetFolderByNameDto> {

    private static final Logger logger = LoggerFactory.getLogger(GettingFolderByName.class);

    private final FolderStorage folderStorageInMemory;

    public GettingFolderByName(FolderStorage userStorage, AuthorizationStorage authorizationStorage) {

        super(Preconditions.checkNotNull(authorizationStorage));
        this.folderStorageInMemory = Preconditions.checkNotNull(userStorage);
    }

    @Override
    protected GetFolderByNameDto doHandle(GetFolderByNameQuery query) throws InvalidHandleCommandException {

        if (logger.isInfoEnabled()) {
            logger.info("Start get directory for user: " + query.owner() + " and name: " + query.name());
        }

        Optional<Folder> folder = folderStorageInMemory.findFolderByName(query.name(), query.owner());

        if (folder.isPresent()) {

            if (logger.isInfoEnabled()) {
                logger.info("Read folder by User: " + query.owner() +
                        ". And name: " + query.name() + ". Was successful");
            }

            return new GetFolderByNameDto(folder.get().id());

        } else {

            if (logger.isErrorEnabled()) {
                logger.error("Folder with name doesn't exist " + query.name() + ".User " + query.owner());
            }

            throw new InvalidHandleCommandException(
                    "Folder with name doesn't exist " + query.name() + ".User " + query.owner());
        }

    }
}
