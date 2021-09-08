package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.DuplicatedUserIdException;
import io.javaclasses.fileHub.persistent.files.File;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.FileStorage;
import io.javaclasses.fileHub.persistent.files.content.FIleContentStorage;
import io.javaclasses.fileHub.persistent.files.content.FileContent;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.SecuredUserProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service to uploading new file in authenticated user's directory.
 */
public class UploadFile extends SecuredUserProcess<UploadFileCommand, FileId> {

    private static final Logger logger = LoggerFactory.getLogger(UploadFile.class);

    private final FIleContentStorage contentStorage;

    private final FileStorage fileStorage;

    public UploadFile(FIleContentStorage contentStorage, FileStorage fileStorage,
                      AuthorizationStorage authorizationStorage) {

        super(authorizationStorage);

        this.contentStorage = Preconditions.checkNotNull(contentStorage);

        this.fileStorage = Preconditions.checkNotNull(fileStorage);

    }


    @Override
    protected FileId doHandle(UploadFileCommand inputCommand) throws InvalidCommandHandlingException {

        if (logger.isInfoEnabled()) {
            logger.info("Start upload new file to user's " + inputCommand.owner()
                    + " directory: " + inputCommand.folder());
        }

        FileId fileId = new FileId(inputCommand.name(), inputCommand.owner(), inputCommand.folder());

        File file = new File(fileId);

        file.setUserID(inputCommand.owner());
        file.setMimeType(inputCommand.mimeType());
        file.setName(inputCommand.name());
        file.setFolder(inputCommand.folder().toString());
        file.setSize(inputCommand.size());

        FileContent content = new FileContent(fileId);

        content.setContent(inputCommand.content());

        try {

            fileStorage.create(file);

            contentStorage.create(content);

            if (logger.isInfoEnabled()) {
                logger.info("Uploading new file was successful: " + content.id());
            }

            return fileId;

        } catch (DuplicatedUserIdException e) {

            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }

            throw new InvalidCommandHandlingException(e.getMessage());

        }


    }
}
