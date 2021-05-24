package io.javaclasses.fileHub.files;

import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.Query;
import io.javaclasses.fileHub.users.UserID;

public final class FileSystemBrowsingQuery
        extends Query {

    private final UserID id;

    public FileSystemBrowsingQuery(AuthToken token, UserID id) {
        super(token);
        this.id = id;
    }

    public UserID id() {
        return id;
    }
}
