package io.javaclasses.fileHub.folders;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.Query;
import io.javaclasses.fileHub.users.UserID;

public final class GetFolderByNameQuery extends Query {
    private final String name;
    private final UserID owner;

    public GetFolderByNameQuery(AuthToken token, String name, UserID owner) {
        super(Preconditions.checkNotNull(token));
        this.name = Preconditions.checkNotNull(name);
        this.owner = Preconditions.checkNotNull(owner);
    }

    public String name() {
        return name;
    }

    public UserID owner() {
        return owner;
    }
}
