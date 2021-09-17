package io.javaclasses.fileHub.webservices.filesystem;

import io.javaclasses.fileHub.services.files.FileSystemItemDto;
import io.javaclasses.fileHub.webservices.JsonResponse;

import java.util.List;

/**
 * Contains list of {@link FileSystemItemDto items} that comes after a successful
 * {@link io.javaclasses.fileHub.services.files.content.GetFolderContentQuery query} handling.
 */
public final class GetFolderContentSuccessfulResponse extends JsonResponse {

    private final List<FileSystemItemDto> items;

    public GetFolderContentSuccessfulResponse(List<FileSystemItemDto> items) {
        this.items = items;
    }
}
