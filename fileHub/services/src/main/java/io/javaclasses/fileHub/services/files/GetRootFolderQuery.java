package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.Query;

/**
 * Data that needs to get information about root folder in the Filehub application by the
 * {@link UserId authenticated user}.
 */
public final class GetRootFolderQuery extends Query {

    public GetRootFolderQuery(AuthToken token) {

        super(token);
    }

}
