package io.javaclasses.fileHub.files;

import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.Query;

public final class GetFileQuery extends Query {

    private final FileID id;

    public GetFileQuery(AuthToken token, FileID id) {
        super(token);
        this.id = id;
    }

    public FileID id() {
        return id;
    }
}
