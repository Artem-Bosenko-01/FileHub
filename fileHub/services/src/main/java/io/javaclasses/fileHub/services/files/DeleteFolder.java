package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.NotExistedItemException;
import io.javaclasses.fileHub.persistent.files.FileStorage;
import io.javaclasses.fileHub.persistent.files.Folder;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.SecuredUserProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Service to delete an existed folder in Filehub application by authenticated user.
 */
@Component
public class DeleteFolder extends SecuredUserProcess<DeleteFolderCommand, String> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteFolder.class);

    private final FolderStorage folderStorage;

    private final FileStorage fileStorage;

    @Autowired
    public DeleteFolder(@Qualifier("folderJDBCStorage") FolderStorage folderStorage,
                        @Qualifier("fileJDBCStorage") FileStorage fileStorage,
                        @Qualifier("authorizationJDBCStorage") AuthorizationStorage authorizationStorage) {

        super(authorizationStorage);

        this.folderStorage = Preconditions.checkNotNull(folderStorage);

        this.fileStorage = Preconditions.checkNotNull(fileStorage);
    }

    @Override
    protected String doHandle(DeleteFolderCommand inputCommand) throws FolderNotFoundException {

        if (logger.isInfoEnabled()) {
            logger.info("Start delete folder " + inputCommand.folderID());
        }

        try {

            Optional<Folder> folder = folderStorage.findByID(new FolderId(inputCommand.folderID()));

            removeFolderAndNestedItems(inputCommand.folderID());

            if (logger.isInfoEnabled()) {
                logger.info("Deleted folder" + inputCommand.folderID() + " was successful");
            }

            folder.ifPresent(value -> folderStorage.decreaseItemsAmount(Objects.requireNonNull(value.parentFolder())));

            return inputCommand.folderID();

        } catch (NotExistedItemException e) {

            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }

            throw new FolderNotFoundException(inputCommand.folderID());
        }

    }

    private void removeFolderAndNestedItems(String id) throws NotExistedItemException {

        fileStorage.deleteFilesByParentFolderId(id);

        folderStorage.delete(id);

        List<Folder> nestedFolders = folderStorage.getNestedFolders(id);

        nestedFolders.forEach(folder -> {

            try {

                removeFolderAndNestedItems(folder.id().toString());

            } catch (NotExistedItemException notExistedItemException) {

                throw new RuntimeException("Something go wrong!");
            }

        });
    }
}
