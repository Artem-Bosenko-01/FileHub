package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.Query;
import io.javaclasses.fileHub.persistent.users.UserId;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This is object, that contains data, that needs to get information for existed folder by name
 * in Filehub application by {@link UserId authenticated user} .
 */
public final class GetFolderByIdQuery extends Query {
    private final FolderId id;
    private final UserId owner;

    public GetFolderByIdQuery(AuthToken token, FolderId id, UserId owner) {

        super(checkNotNull(token));
        this.id = checkNotNull(id);
        this.owner = checkNotNull(owner);

    }

    public FolderId id() {
        return id;
    }

    public UserId owner() {
        return owner;
    }
}
