package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.Query;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Data that needs to get information about an existed folder by id in the Filehub application
 * by {@link UserId authenticated user}.
 */
public final class GetFolderByIdQuery extends Query {

    private final String id;

    public GetFolderByIdQuery(AuthToken token, String id) {

        super(checkNotNull(token));

        this.id = checkNotNull(id);

    }

    public String id() {

        return id;
    }

}
