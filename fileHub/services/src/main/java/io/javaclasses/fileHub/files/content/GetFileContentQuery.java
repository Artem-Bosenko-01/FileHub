package io.javaclasses.fileHub.files.content;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.Query;
import io.javaclasses.fileHub.files.FileID;

public final class GetFileContentQuery extends Query {

    private final FileID fileID;

    public GetFileContentQuery(AuthToken token, FileID fileID) {
        super(Preconditions.checkNotNull(token));
        this.fileID = Preconditions.checkNotNull(fileID);
    }

    public FileID fileID() {
        return fileID;
    }
}
