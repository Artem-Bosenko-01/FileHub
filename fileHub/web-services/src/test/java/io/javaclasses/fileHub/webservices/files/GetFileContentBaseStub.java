package io.javaclasses.fileHub.webservices.files;

import io.javaclasses.fileHub.persistent.files.content.FIleContentStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.files.content.GetFileContent;

public class GetFileContentBaseStub extends GetFileContent {

    public GetFileContentBaseStub(FIleContentStorage contentStorage, AuthorizationStorage authorizationStorage) {

        super(contentStorage, authorizationStorage);
    }
}
