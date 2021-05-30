package io.javaclasses.fileHub.files.content;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import io.javaclasses.fileHub.AuthenticatedUserCommand;
import io.javaclasses.fileHub.files.FileID;

/**
 *  This is object, that contains data, that needs to create content for existed file's in Filehub application
 *  by authenticated user.
 *
 * */
public final class CreateFileContentCommand extends AuthenticatedUserCommand {

    private final FileID fileID;
    private final byte[] content;

    public CreateFileContentCommand(AuthToken token, FileID fileID, byte[] content) {
        super(Preconditions.checkNotNull(token));
        this.fileID = Preconditions.checkNotNull(fileID);
        this.content = Preconditions.checkNotNull(content);
    }

    public FileID fileID() {
        return fileID;
    }

    public byte[] content() {
        return content;
    }
}
