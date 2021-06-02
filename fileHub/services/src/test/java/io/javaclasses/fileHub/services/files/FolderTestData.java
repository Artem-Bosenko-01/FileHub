package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;

public final class FolderTestData {

    public static CreateFolderCommand createFolder (){

        return new CreateFolderCommand(
                new AuthToken("56"),
                "folder",
                new UserId("Artem"),
                null
        );
    }

}
