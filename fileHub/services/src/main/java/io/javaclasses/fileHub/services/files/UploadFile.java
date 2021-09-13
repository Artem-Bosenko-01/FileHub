package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.DuplicatedIdException;
import io.javaclasses.fileHub.persistent.files.File;
import io.javaclasses.fileHub.persistent.files.FileStorage;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.files.content.FIleContentStorage;
import io.javaclasses.fileHub.persistent.files.content.FileContent;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationUsers;
import io.javaclasses.fileHub.persistent.users.tokens.UserAuthToken;
import io.javaclasses.fileHub.services.SecuredUserProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Service to upload new file in authenticated user's directory.
 */
public class UploadFile extends SecuredUserProcess<UploadFileCommand, String> {

    private static final Logger logger = LoggerFactory.getLogger(UploadFile.class);

    private final AuthorizationStorage authorizationStorage;

    private final FolderStorage folderStorage;

    private final FIleContentStorage contentStorage;

    private final FileStorage fileStorage;

    public UploadFile(FIleContentStorage contentStorage, FileStorage fileStorage, FolderStorage folderStorage,
                      AuthorizationStorage authorizationStorage) {

        super(checkNotNull(authorizationStorage));

        this.contentStorage = checkNotNull(contentStorage);

        this.fileStorage = checkNotNull(fileStorage);

        this.authorizationStorage = authorizationStorage;

        this.folderStorage = checkNotNull(folderStorage);

    }


    @Override
    protected String doHandle(UploadFileCommand inputCommand) throws UsersTokenNotFoundException, DuplicatedFileNameException {

        Optional<AuthorizationUsers> owner = authorizationStorage.
                findByID(new UserAuthToken(inputCommand.token().value()));

        if (owner.isPresent()) {

            UserId userId = owner.get().userID();

            if (logger.isInfoEnabled()) {
                logger.info("Start upload new file to user's " + userId
                        + " directory: " + inputCommand.folder());
            }

            String fileId = inputCommand.name() + userId + inputCommand.folder();

            File file = new File(fileId);

            file.setUserID(userId);
            file.setMimeType(inputCommand.mimeType());
            file.setName(inputCommand.name());
            file.setFolder(inputCommand.folder());
            file.setSize(inputCommand.size());

            FileContent content = new FileContent(fileId);

            content.setContent(inputCommand.content());

            try {

                fileStorage.create(file);

                contentStorage.create(content);

                if (logger.isInfoEnabled()) {
                    logger.info("Uploading new file was successful: " + content.id());
                }

                folderStorage.increaseItemsAmount(file.folder());

                return fileId;

            } catch (DuplicatedIdException e) {

                if (logger.isErrorEnabled()) {
                    logger.error(e.getMessage());
                }

                throw new DuplicatedFileNameException(inputCommand.name());

            }

        } else {

            if (logger.isErrorEnabled()) {

                logger.error("Cannot find user by token: " + inputCommand.token());
            }

            throw new UsersTokenNotFoundException(inputCommand.token());
        }


    }
}
