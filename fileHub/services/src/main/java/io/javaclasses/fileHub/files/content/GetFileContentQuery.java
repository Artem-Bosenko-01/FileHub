package io.javaclasses.fileHub.files.content;

import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.Query;
import io.javaclasses.fileHub.files.FileID;

public class GetFileContentQuery extends Query {

    private final FileID fileID;

    public GetFileContentQuery(AuthToken token, FileID fileID) {
        super(token);
        this.fileID = fileID;
    }

    public FileID fileID() {
        return fileID;
    }
}
