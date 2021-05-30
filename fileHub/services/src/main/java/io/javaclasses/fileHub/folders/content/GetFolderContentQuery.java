package io.javaclasses.fileHub.folders.content;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import io.javaclasses.fileHub.Query;
import io.javaclasses.fileHub.folders.FolderID;
import io.javaclasses.fileHub.users.UserID;

public final class GetFolderContentQuery extends Query {

    private final FolderID id;
    private final UserID owner;

    public GetFolderContentQuery(AuthToken token, FolderID id, UserID owner) {
        super(Preconditions.checkNotNull(token));
        this.id = Preconditions.checkNotNull(id);
        this.owner = Preconditions.checkNotNull(owner);
    }

    public FolderID id() {
        return id;
    }

    public UserID owner() {
        return owner;
    }
}
