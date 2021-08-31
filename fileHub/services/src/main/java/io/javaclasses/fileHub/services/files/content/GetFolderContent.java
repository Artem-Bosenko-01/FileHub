package io.javaclasses.fileHub.services.files.content;

import io.javaclasses.fileHub.persistent.NotExistUserIdException;
import io.javaclasses.fileHub.persistent.files.*;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.services.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Service to get folder's content for existed {@link FolderId folder}.
 */
public class GetFolderContent extends View<GetFolderContentQuery, GetFolderContentDTO> {

    private static final Logger logger = LoggerFactory.getLogger(GetFolderContent.class);

    private final FolderStorage folderStorage;
    private final FileStorage fileStorage;

    public GetFolderContent(FolderStorage folderStorage, FileStorage fileStorage,
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

            List<Folder> folders = folderStorage.findAllFoldersByParentFolderId(inputCommand.id());
            List<File> files = fileStorage.findAllFilesByFolderIdAndUserId(inputCommand.id(), inputCommand.owner());

            if (logger.isInfoEnabled()) {
                logger.info("Getting folder's content was successful by id " + inputCommand.id());
            }

            return new GetFolderContentDTO(folders, files);


        } catch (NotExistUserIdException e) {

            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }

            throw new InvalidHandleCommandException(e.getMessage());
        }

    }
}
