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

    private final FolderId id;
    private final UserId owner;

    public GetFolderContentQuery(AuthToken token, FolderId id, UserId owner) {

        super(Preconditions.checkNotNull(token));

        this.id = Preconditions.checkNotNull(id);

        this.owner = Preconditions.checkNotNull(owner);

    }

    public FolderId id() {

        return id;
    }

    public UserId owner() {

        return owner;
    }
}
