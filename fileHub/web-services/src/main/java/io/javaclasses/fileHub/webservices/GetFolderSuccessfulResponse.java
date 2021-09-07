package io.javaclasses.fileHub.webservices;

import io.javaclasses.fileHub.services.files.FileSystemItemDto;

public class GetFolderSuccessfulResponse extends JsonResponse {

    private final FileSystemItemDto folder;

    public GetFolderSuccessfulResponse(FileSystemItemDto folder) {
        this.folder = folder;
    }
}
