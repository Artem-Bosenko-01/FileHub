package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.AuthenticatedUserCommand;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Data that needs to create a new folder and put to {@link FolderStorage storage}
 * in Filehub application by {@link UserId authenticated user} .
 */
public final class CreateFolderCommand extends AuthenticatedUserCommand {

    private final String name;

    private final UserId owner;

    private final Integer itemsAmount;

    private final FolderId parentFolder;

    public CreateFolderCommand(AuthToken token, String name, UserId owner, Integer itemsAmount,
                               @Nullable FolderId parentFolder) {

        super(checkNotNull(token));

        this.name = checkNotNull(name);

        this.owner = checkNotNull(owner);

        this.itemsAmount = checkNotNull(itemsAmount);

        this.parentFolder = parentFolder;

    }

    public String name() {

        return name;
    }

    public UserId owner() {

        return owner;
    }

    public Integer itemsAmount() {

        return itemsAmount;
    }

    public FolderId parentFolder() {

        return parentFolder;
    }
}
