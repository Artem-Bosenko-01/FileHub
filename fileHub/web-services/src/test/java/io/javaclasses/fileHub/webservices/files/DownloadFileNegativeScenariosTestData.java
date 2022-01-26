package io.javaclasses.fileHub.webservices.files;

import io.javaclasses.fileHub.persistent.files.content.FIleContentStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.NotAuthorizedUserException;
import io.javaclasses.fileHub.services.files.content.GetFileContentDTO;
import io.javaclasses.fileHub.services.files.content.GetFileContentQuery;
import io.javaclasses.fileHub.webservices.AuthenticationStorageBaseStub;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public final class DownloadFileNegativeScenariosTestData implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {

        FileContentStorageBaseStub contentStorage = new FileContentStorageBaseStub();
        AuthenticationStorageBaseStub authenticationStorage = new AuthenticationStorageBaseStub();

        return Stream.of(

                Arguments.of(
                        "File with id not exist: id",
                        400,
                        new DownloadNotExistedFileStub(contentStorage, authenticationStorage)
                ),

                Arguments.of(
                        "User with token not authorized: userToken",
                        401,
                        new DownloadFileByNotAuthenticatedUserStub(contentStorage, authenticationStorage)
                ),

                Arguments.of(
                        "Internal server error.",
                        500,
                        new DownloadingProcessThrowNullPointerStub(contentStorage, authenticationStorage)
                )
        );
    }

    private static class DownloadFileByNotAuthenticatedUserStub extends GetFileContentBaseStub {

        public DownloadFileByNotAuthenticatedUserStub(FIleContentStorage contentStorage,
                                                      AuthorizationStorage authorizationStorage) {

            super(contentStorage, authorizationStorage);
        }

        @Override
        public GetFileContentDTO handle(GetFileContentQuery query)
                throws NotAuthorizedUserException {

            throw new NotAuthorizedUserException("User with token not authorized: " + query.token());
        }
    }

    private static class DownloadNotExistedFileStub extends GetFileContentBaseStub {

        public DownloadNotExistedFileStub(FIleContentStorage contentStorage,
                                          AuthorizationStorage authorizationStorage) {

            super(contentStorage, authorizationStorage);
        }

        @Override
        public GetFileContentDTO handle(GetFileContentQuery query)
                throws InvalidCommandHandlingException {

            throw new InvalidCommandHandlingException("File with id not exist: " + query.fileID());
        }
    }

    private static class DownloadingProcessThrowNullPointerStub extends GetFileContentBaseStub {

        public DownloadingProcessThrowNullPointerStub(FIleContentStorage contentStorage,
                                                      AuthorizationStorage authorizationStorage) {
            super(contentStorage, authorizationStorage);
        }

        @Override
        public GetFileContentDTO handle(GetFileContentQuery query) {

            throw new NullPointerException();
        }
    }
}
