package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.AuthenticatedUserCommand;
import io.javaclasses.fileHub.services.InvalidValidationCommandDataException;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.javaclasses.fileHub.services.ValidationRules.validateFileSystemItemName;

/**
 * Data that needs to create a new folder and put to {@link FolderStorage storage}
 * in Filehub application by {@link UserId authenticated user} .
 */
public final class CreateFolderCommand extends AuthenticatedUserCommand {

    private final String name;

    private final Long itemsAmount;

    @Nullable
    private final String parentFolder;

    public CreateFolderCommand(AuthToken token, String name, Integer itemsAmount, @Nullable String parentFolder)
            throws InvalidValidationCommandDataException {

        super(checkNotNull(token));

        validateFileSystemItemName(name);

        this.name = checkNotNull(name);

        this.itemsAmount = Long.valueOf(checkNotNull(itemsAmount));

        this.parentFolder = parentFolder;

    }

    public String name() {

        return name;
    }

    public Long itemsAmount() {

        return itemsAmount;
    }

    @Nullable
    String parentFolder() {

        return parentFolder;
    }
}
