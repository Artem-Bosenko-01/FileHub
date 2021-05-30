package io.javaclasses.fileHub.files.content;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import io.javaclasses.fileHub.Query;
import io.javaclasses.fileHub.files.FileID;

/**
 *  This is object, that contains data, that needs to get content for existed file's in Filehub application
 *  by {@link FileID id}.
 *
 * */
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
