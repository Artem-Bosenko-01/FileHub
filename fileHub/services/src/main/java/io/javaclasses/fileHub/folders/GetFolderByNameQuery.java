package io.javaclasses.fileHub.folders;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import io.javaclasses.fileHub.Query;
import io.javaclasses.fileHub.users.UserID;

/**
 *  This is object, that contains data, that needs to get information for existed folder by name
 *  in Filehub application by {@link UserID authenticated user} .
 *
 * */
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
