package io.javaclasses.fileHub.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.Query;
import io.javaclasses.fileHub.RecordID;

public final class FileSystemBrowsingQuery<I extends RecordID>
        extends Query {

    private final I id;

    public FileSystemBrowsingQuery(AuthToken token, I id) {
        super(Preconditions.checkNotNull(token));
        this.id = Preconditions.checkNotNull(id);
    }

    public I id() {
        return id;
    }
}
