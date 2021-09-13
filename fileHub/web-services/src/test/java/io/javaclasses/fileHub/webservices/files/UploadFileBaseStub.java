package io.javaclasses.fileHub.webservices.files;

import io.javaclasses.fileHub.persistent.files.FileStorage;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.files.content.FIleContentStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.files.UploadFile;

public class UploadFileBaseStub extends UploadFile {

    public UploadFileBaseStub(FIleContentStorage contentStorage, FileStorage fileStorage,
                              FolderStorage folderStorage, AuthorizationStorage authorizationStorage) {

        super(contentStorage, fileStorage, folderStorage, authorizationStorage);
    }
}
