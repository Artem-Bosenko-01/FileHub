package io.javaclasses.fileHub.services.files.content;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.Query;

/**
 * Data that needed to get content for existed folder's in the Filehub application
 * by {@link FolderId id} and {@link UserId owner}.
 */
public final class GetFolderContentQuery extends Query {

    private final String id;

    public GetFolderContentQuery(AuthToken token, String id) {

        super(Preconditions.checkNotNull(token));

        this.id = Preconditions.checkNotNull(id);

    }

    public String id() {

        return id;
    }
}
