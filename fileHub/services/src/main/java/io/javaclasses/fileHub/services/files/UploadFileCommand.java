package io.javaclasses.fileHub.services.files;

import com.google.common.net.MediaType;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.AuthenticatedUserCommand;
import io.javaclasses.fileHub.services.InvalidValidationCommandDataException;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.javaclasses.fileHub.services.ValidationRules.validateFileSystemItemName;

/**
 * Data that needs to upload new file to Filehub application by authenticated user.
 */
public final class UploadFileCommand extends AuthenticatedUserCommand {

    private final String name;

    private final MediaType mimeType;

    private final String folder;

    private final byte[] content;

    public UploadFileCommand(AuthToken token, String name, MediaType mimeType, String folder, byte[] content)
            throws InvalidValidationCommandDataException {

        super(checkNotNull(token));

        validateFileSystemItemName(name);

        this.name = checkNotNull(name);

        this.mimeType = checkNotNull(mimeType);

        this.folder = checkNotNull(folder);

        this.content = checkNotNull(content);

    }

    public String name() {

        return name;
    }

    public MediaType mimeType() {

        return mimeType;
    }

    public String folder() {

        return folder;
    }

    public byte[] content() {

        return content.clone();
    }

    public Long size() {
        return (long) content.length;
    }
}
