package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.Query;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Data that needs to get information for an existed folder by id in the Filehub application
 * by {@link UserId authenticated user}.
 */
public final class GetFolderByIdQuery extends Query {

    private final FolderId id;

    public GetFolderByIdQuery(AuthToken token, FolderId id) {

        super(checkNotNull(token));

        this.id = checkNotNull(id);

    }

    public FolderId id() {

        return id;
    }

}
