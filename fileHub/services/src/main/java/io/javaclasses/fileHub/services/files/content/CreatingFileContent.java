package io.javaclasses.fileHub.services.files.content;

import io.javaclasses.fileHub.persistent.DuplicatedUserIdException;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.services.SecuredUserProcess;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.content.FIleContentStorage;
import io.javaclasses.fileHub.persistent.files.content.FileContent;

/**
 * This is service to create file's content to existed {@link FileId  file} in authenticated user's directory.
 */
public class CreatingFileContent extends SecuredUserProcess<CreateFileContentCommand, byte[]> {

    private final FIleContentStorage contentStorage;

    public CreatingFileContent(FIleContentStorage contentStorage, AuthorizationStorage authorizationStorage) {
        super(authorizationStorage);
        this.contentStorage = contentStorage;
    }


    @Override
    protected byte[] doHandle(CreateFileContentCommand inputCommand) throws InvalidHandleCommandException {

        try {

            FileContent content = new FileContent(inputCommand.fileID());
            content.setContent(inputCommand.content());
            contentStorage.create(content);

            return content.content();

        } catch (DuplicatedUserIdException e) {

            throw new InvalidHandleCommandException(e.getMessage());

        }
    }
}
