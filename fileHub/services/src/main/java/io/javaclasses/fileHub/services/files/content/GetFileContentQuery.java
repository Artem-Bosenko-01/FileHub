package io.javaclasses.fileHub.services.files.content;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.Query;

/**
 * Data that needed to get content for existing file's by {@link FileId id}.
 */
public final class GetFileContentQuery extends Query {

    private final String fileID;

    public GetFileContentQuery(AuthToken token, String fileID) {

        super(Preconditions.checkNotNull(token));

        this.fileID = Preconditions.checkNotNull(fileID);

    }

    public String fileID() {

        return fileID;
    }
}
