package io.javaclasses.fileHub.folders.content;

import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.View;
import io.javaclasses.fileHub.files.File;
import io.javaclasses.fileHub.files.FileStorage;
import io.javaclasses.fileHub.folders.Folder;
import io.javaclasses.fileHub.folders.FolderID;
import io.javaclasses.fileHub.folders.FolderStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * This is service to get folder's content for existed {@link FolderID folder}.
 */
public class GetFolderContentView implements View<GetFolderContentQuery, GetFolderContentDTO> {

    private final FolderStorage folderStorage;
    private final FileStorage fileStorage;
    private final Logger logger = LoggerFactory.getLogger(GetFolderContentView.class);

    public GetFolderContentView(FolderStorage folderStorageInMemory, FileStorage fileStorage) {
        this.folderStorage = folderStorageInMemory;
        this.fileStorage = fileStorage;
    }

    @Override
    public GetFolderContentDTO handle(GetFolderContentQuery inputCommand) throws InvalidHandleCommandException {
        if(logger.isInfoEnabled()){
            logger.info("Start get folder's content by id " + inputCommand.id());
        }
        try {

            FolderID parentFolder = folderStorage.findParentFolderByChildId(inputCommand.id());

            List<Folder> folders = folderStorage.findAllFoldersByParentFolderId(inputCommand.id());
            List<File> files = fileStorage.findAllFilesByFolderIDAndUserID(inputCommand.id(), inputCommand.owner());

            if(logger.isInfoEnabled()){
                logger.info("Getting folder's content was successful by id " + inputCommand.id());
            }

            return new GetFolderContentDTO(parentFolder,folders,files);

        } catch (NotExistIDException e) {

            if(logger.isErrorEnabled()){
                logger.error(e.getMessage());
            }
            throw new InvalidHandleCommandException(e.getMessage());
        }
    }
}
