package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.Query;
import io.javaclasses.fileHub.persistent.users.UserId;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This is object, that contains data, that needs to get information for existed folder by name
 * in Filehub application by {@link UserId authenticated user} .
 */
public final class GetFolderByNameQuery extends Query {
    private final String name;
    private final UserId owner;

    public GetFolderByNameQuery(AuthToken token, String name, UserId owner) {

        super(checkNotNull(token));
        this.name = checkNotNull(name);
        this.owner = checkNotNull(owner);

    }

    public String name() {
        return name;
    }

    public UserId owner() {
        return owner;
    }
}
