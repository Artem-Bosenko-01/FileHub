package io.javaclasses.fileHub.services.files;

import com.google.common.net.MediaType;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.AuthenticatedUserCommand;
import io.javaclasses.fileHub.services.InvalidValidationCommandDataException;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.javaclasses.fileHub.services.ValidationRules.validateItemName;

/**
 * Data that needs to update an existed file.
 */
public final class UpdateFileCommand extends AuthenticatedUserCommand {

    private final String id;

    private final String name;

    private final MediaType mimeType;

    private final Long size;

    private final String folder;

    public UpdateFileCommand(AuthToken token, String id, String name, MediaType mimeType,
                             Long size, String folder) throws InvalidValidationCommandDataException {

        super(checkNotNull(token));

        validateItemName(name);

        this.id = checkNotNull(id);

        this.name = checkNotNull(name);

        this.mimeType = checkNotNull(mimeType);

        this.size = checkNotNull(size);

        this.folder = checkNotNull(folder);

    }

    public String id() {

        return id;
    }

    public String name() {

        return name;
    }

    public MediaType mimeType() {

        return mimeType;
    }

    public Long size() {

        return size;
    }

    public String folder() {

        return folder;
    }
}