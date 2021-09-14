package io.javaclasses.fileHub.webservices.filesystem;

import io.javaclasses.fileHub.services.files.FileSystemItemDto;
import io.javaclasses.fileHub.webservices.JsonResponse;

/**
 *
 */
public class GetFolderSuccessfulResponse extends JsonResponse {

    private final FileSystemItemDto folder;

    public GetFolderSuccessfulResponse(FileSystemItemDto folder) {
        this.folder = folder;
    }
}
