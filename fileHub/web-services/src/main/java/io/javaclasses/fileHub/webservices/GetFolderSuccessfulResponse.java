package io.javaclasses.fileHub.webservices;

import io.javaclasses.fileHub.services.files.GetFolderDto;

public class GetFolderSuccessfulResponse extends JsonResponse {

    private final GetFolderDto folder;

    public GetFolderSuccessfulResponse(GetFolderDto folder) {
        this.folder = folder;
    }
}
