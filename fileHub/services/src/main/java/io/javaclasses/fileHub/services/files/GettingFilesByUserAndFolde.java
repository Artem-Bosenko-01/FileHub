package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.NotExistUserIdException;
import io.javaclasses.fileHub.services.View;
import io.javaclasses.fileHub.persistent.files.File;
import io.javaclasses.fileHub.persistent.files.FileStorageInMemory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This is service to get existed file in authenticated user's directory by {@link FolderId folder}
 * and {@link UserId user}.
 */
public class GettingFilesByUserAndFolde
        implements View<GetFilesByUserAndFolderQuery, List<FileInformation>> {

    private static final Logger logger = LoggerFactory.getLogger(GettingFilesByUserAndFolde.class);

    private final FileStorageInMemory fileStorage;

    public GettingFilesByUserAndFolde(FileStorageInMemory fileStorage) {
        this.fileStorage = Preconditions.checkNotNull(fileStorage);
    }

    @Override
    public List<FileInformation> handle(GetFilesByUserAndFolderQuery query)
            throws InvalidHandleCommandException {

        if (logger.isInfoEnabled()) {
            logger.info("Start read all files for user: " + query.userID() + " and folder: " + query.folderID());
        }

        try {

            List<File> findFiles = fileStorage.findAllFilesByFolderIDAndUserID(query.folderID(), query.userID());
            List<FileInformation> findFilesDto = new ArrayList<>();

            findFiles.forEach(file -> Objects.requireNonNull(findFilesDto).add(new FileInformation(file.id(),
                    file.name(), file.owner(), file.mimeType(), file.folder())));

            return findFilesDto;

        } catch (NotExistUserIdException e) {

            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw new InvalidHandleCommandException(e.getMessage());

        } finally {

            if (logger.isInfoEnabled()) {
                logger.info("Read files by User: " + query.userID() + ". " +
                        "And folder: " + query.folderID() + ". Was successful");
            }
        }
    }
}
