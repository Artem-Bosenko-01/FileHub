package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.AuthenticatedUserCommand;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Data that needs to create a new folder and put to {@link FolderStorage storage}
 * in Filehub application by {@link UserId authenticated user} .
 */
public final class CreateFolderCommand extends AuthenticatedUserCommand {

    private final String name;

    private final Integer itemsAmount;

    private final String parentFolder;

    public CreateFolderCommand(AuthToken token, String name, Integer itemsAmount, String parentFolder) {

        super(checkNotNull(token));

        this.name = checkNotNull(name);


        this.itemsAmount = checkNotNull(itemsAmount);

        this.parentFolder = checkNotNull(parentFolder);

    }

    public String name() {

        return name;
    }

    public Integer itemsAmount() {

        return itemsAmount;
    }

    public String parentFolder() {

        return parentFolder;
    }
}
