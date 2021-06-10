package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.services.SecuredUserProcess;
import io.javaclasses.fileHub.persistent.files.FileStorage;
import io.javaclasses.fileHub.services.files.content.CreateFileContentCommand;
import io.javaclasses.fileHub.services.files.content.CreatingFileContent;
import io.javaclasses.fileHub.persistent.files.content.FIleContentStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is service to uploading new file in authenticated user's directory.
 */
public class UploadingFile extends SecuredUserProcess<UploadFileCommand, FileId> {

    private static final Logger logger = LoggerFactory.getLogger(UploadingFile.class);

    private final FIleContentStorage contentStorage;
    private final FileStorage fileStorage;
    private final AuthorizationStorage authorizationStorage;

    public UploadingFile(FIleContentStorage contentStorage, FileStorage fileStorage,
                         AuthorizationStorage authorizationStorage) {
        super(authorizationStorage);
        this.contentStorage = Preconditions.checkNotNull(contentStorage);
        this.fileStorage = Preconditions.checkNotNull(fileStorage);
        this.authorizationStorage = Preconditions.checkNotNull(authorizationStorage);
    }


    @Override
    protected FileId doHandle(UploadFileCommand inputCommand) throws InvalidHandleCommandException {

        if (logger.isInfoEnabled()) {
            logger.info("Start upload new file to user's " + inputCommand.owner()
                    + " directory: " + inputCommand.folder());
        }

        CreateFileCommand createFileCommand = new CreateFileCommand(inputCommand.token(),
                inputCommand.name(), inputCommand.mimeType(), inputCommand.owner(), inputCommand.folder());

        CreatingFile creatingFile = new CreatingFile(fileStorage, authorizationStorage);

        FileId id = creatingFile.handle(createFileCommand);

        CreateFileContentCommand contentCommand = new CreateFileContentCommand(
                inputCommand.token(),
                id,
                inputCommand.content()
        );

        CreatingFileContent creatingFileContent = new CreatingFileContent(contentStorage, authorizationStorage);

        creatingFileContent.handle(contentCommand);

        if (logger.isInfoEnabled()) {
            logger.info("Uploading new file was successful " + id);
        }

        return id;

    }
}
