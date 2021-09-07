package io.javaclasses.fileHub.webservices.foldercontent;

import io.javaclasses.fileHub.services.files.FileSystemItemDto;
import io.javaclasses.fileHub.webservices.JsonResponse;

import java.util.List;

public class GetFolderContentSuccessfulResponse extends JsonResponse {

    private final List<FileSystemItemDto> items;

    public GetFolderContentSuccessfulResponse(List<FileSystemItemDto> items) {
        this.items = items;
    }
}
