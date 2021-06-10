package io.javaclasses.fileHub.services.files.content;

import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.NotExistUserIdException;
import io.javaclasses.fileHub.services.View;
import io.javaclasses.fileHub.persistent.files.File;
import io.javaclasses.fileHub.persistent.files.FileStorage;
import io.javaclasses.fileHub.persistent.files.Folder;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * This is service to get folder's content for existed {@link FolderId folder}.
 */
public class GettingFolderContent extends View<GetFolderContentQuery, GetFolderContentDTO> {

    private static final Logger logger = LoggerFactory.getLogger(GettingFolderContent.class);

    private final FolderStorage folderStorage;
    private final FileStorage fileStorage;

    public GettingFolderContent(FolderStorage folderStorage, FileStorage fileStorage,
                                AuthorizationStorage authorizationStorage) {

        super(authorizationStorage);
        this.folderStorage = folderStorage;
        this.fileStorage = fileStorage;

    }


    @Override
    protected GetFolderContentDTO doHandle(GetFolderContentQuery inputCommand) throws InvalidHandleCommandException {

        if (logger.isInfoEnabled()) {
            logger.info("Start get folder's content by id " + inputCommand.id());
        }

        try {

            Optional<FolderId> parentFolder = folderStorage.findParentFolderByChildId(inputCommand.id());
            List<Folder> folders = folderStorage.findAllFoldersByParentFolderId(inputCommand.id());
            List<File> files = fileStorage.findAllFilesByFolderIDAndUserID(inputCommand.id(), inputCommand.owner());

            if (logger.isInfoEnabled()) {
                logger.info("Getting folder's content was successful by id " + inputCommand.id());
            }

            return new GetFolderContentDTO(parentFolder.orElse(null), folders, files);


        } catch (NotExistUserIdException e) {

            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw new InvalidHandleCommandException(e.getMessage());
        }

    }
}
