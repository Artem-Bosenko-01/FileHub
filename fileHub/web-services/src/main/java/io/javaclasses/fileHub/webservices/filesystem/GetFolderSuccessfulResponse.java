package io.javaclasses.fileHub.webservices.filesystem;

import io.javaclasses.fileHub.services.files.FileSystemItemDto;
import io.javaclasses.fileHub.webservices.JsonResponse;

/**
 * Contains {@link FileSystemItemDto folder data} that comes after a successful get folder info query handling.
 */
public final class GetFolderSuccessfulResponse extends JsonResponse {

    private final FileSystemItemDto folder;

    public GetFolderSuccessfulResponse(FileSystemItemDto folder) {
        this.folder = folder;
    }
}
