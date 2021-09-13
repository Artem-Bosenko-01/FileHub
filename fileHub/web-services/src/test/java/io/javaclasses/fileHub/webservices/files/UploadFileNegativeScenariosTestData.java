package io.javaclasses.fileHub.webservices.files;

import io.javaclasses.fileHub.persistent.files.FileStorage;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.files.content.FIleContentStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.InvalidValidationCommandDataException;
import io.javaclasses.fileHub.services.NotAuthorizedUserException;
import io.javaclasses.fileHub.services.files.DuplicatedFileNameException;
import io.javaclasses.fileHub.services.files.UploadFileCommand;
import io.javaclasses.fileHub.webservices.AuthenticationStorageBaseStub;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UploadFileNegativeScenariosTestData implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {

        String DEFAULT_FILE_NAME = "fileName.txt";

        FIleContentStorage fIleContentStorage = new FileContentStorageBaseStub();
        FileStorage fileStorage = new FileStorageBaseStub();
        FolderStorage folderStorage = new FolderStorageBaseStub();
        AuthorizationStorage authorizationStorage = new AuthenticationStorageBaseStub();

        return Stream.of(

                Arguments.of(
                        DEFAULT_FILE_NAME,
                        "Uploaded file name already exist: id",
                        400,
                        new UploadFileWithAlreadyUsedNameStub(fIleContentStorage, fileStorage, folderStorage, authorizationStorage)
                ),

                Arguments.of(
                        "file/name",
                        "Error: Invalid name of file.",
                        422,
                        new UploadFileBaseStub(fIleContentStorage, fileStorage, folderStorage, authorizationStorage)
                ),

                Arguments.of(
                        DEFAULT_FILE_NAME,
                        "User with token not authorized: userToken",
                        401,
                        new UploadFileByNotAuthenticatedUserStub(fIleContentStorage, fileStorage, folderStorage, authorizationStorage)
                ),

                Arguments.of(
                        DEFAULT_FILE_NAME,
                        "Internal server error.",
                        500,
                        new UploadingProcessThrowNullPointerStub(fIleContentStorage, fileStorage, folderStorage, authorizationStorage)
                )
        );
    }

    private static class UploadFileByNotAuthenticatedUserStub extends UploadFileBaseStub {

        public UploadFileByNotAuthenticatedUserStub(FIleContentStorage contentStorage, FileStorage fileStorage,
                                                    FolderStorage folderStorage,
                                                    AuthorizationStorage authorizationStorage) {

            super(contentStorage, fileStorage, folderStorage, authorizationStorage);
        }

        @Override
        public String handle(UploadFileCommand inputCommand)
                throws NotAuthorizedUserException {

            throw new NotAuthorizedUserException("User with token not authorized: " + inputCommand.token());
        }
    }

    private static class UploadFileWithAlreadyUsedNameStub extends UploadFileBaseStub {

        public UploadFileWithAlreadyUsedNameStub(FIleContentStorage contentStorage, FileStorage fileStorage,
                                                 FolderStorage folderStorage,
                                                 AuthorizationStorage authorizationStorage) {

            super(contentStorage, fileStorage, folderStorage, authorizationStorage);
        }

        @Override
        public String handle(UploadFileCommand inputCommand) throws DuplicatedFileNameException {

            throw new DuplicatedFileNameException("id");
        }
    }

    private static class UploadingProcessThrowNullPointerStub extends UploadFileBaseStub {

        public UploadingProcessThrowNullPointerStub(FIleContentStorage contentStorage, FileStorage fileStorage,
                                                    FolderStorage folderStorage,
                                                    AuthorizationStorage authorizationStorage) {

            super(contentStorage, fileStorage, folderStorage, authorizationStorage);
        }

        @Override
        public String handle(UploadFileCommand inputCommand) {

            throw new NullPointerException();
        }
    }
}
