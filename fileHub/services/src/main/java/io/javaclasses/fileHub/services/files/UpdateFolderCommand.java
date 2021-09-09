package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.AuthenticatedUserCommand;
import io.javaclasses.fileHub.services.InvalidValidationCommandDataException;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.javaclasses.fileHub.services.ValidationRules.validateItemName;

/**
 * Data that needs to update an existed folder.
 */
public final class UpdateFolderCommand extends AuthenticatedUserCommand {

    private final String id;

    private final String name;

    private final Long itemsAmount;

    private final String parentFolder;

    public UpdateFolderCommand(AuthToken token, String id, String name, Long itemsAmount, @Nullable String parentFolder)
            throws InvalidValidationCommandDataException {

        super(checkNotNull(token));

        validateItemName(name);

        this.id = checkNotNull(id);

        this.name = checkNotNull(name);

        this.itemsAmount = checkNotNull(itemsAmount);

        this.parentFolder = parentFolder;

    }

    public String id() {

        return id;
    }

    public String name() {

        return name;
    }

    public String parentFolder() {

        return parentFolder;
    }

    public Long itemsAmount() {
        return itemsAmount;
    }
}