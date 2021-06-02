package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.MimeType;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.users.RegistrationUserCommand;

public final class FileTestData {

    public static CreateFileCommand createFile (String name){

        UserId userID = new UserId("artem@gmail.com");

        return new CreateFileCommand(
                new AuthToken("56"),
                name,
                MimeType.TEXT,
                userID,
                new FolderId("folder", userID)
        );
    }

    public static DeleteFileCommand deleteFile (String name){

        UserId userID = new UserId("artem@gmail.com");

        return new DeleteFileCommand(
                new AuthToken("6956"),
                new FileId(name, userID, new FolderId("folder", userID))

        );
    }

    public static UploadFileCommand uploadFile (){

        UserId userID = new UserId("artem@gmail.com");

        return new UploadFileCommand(
                new AuthToken("651"),
                "file.txt",
                MimeType.TEXT,
                userID,
                new FolderId("folder", userID),
                new byte[]{1, 5, 8, 7, 5}
        );
    }
}
