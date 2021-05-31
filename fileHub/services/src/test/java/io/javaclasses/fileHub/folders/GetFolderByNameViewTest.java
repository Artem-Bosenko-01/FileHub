package io.javaclasses.fileHub.folders;

import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.users.UserID;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;


class GetFolderByNameViewTest {

    private CreateFolderDTO createFolder(FolderStorage folderStorage, String name, UserID userID, FolderID folderID){
        CreateFolderCommand createFolderCommand = new CreateFolderCommand(new AuthToken(UUID.randomUUID().toString()),
                name, userID, folderID);

        CreateFolderProcess createFolderProcess = new CreateFolderProcess(folderStorage);

        try {
            return createFolderProcess.handle(createFolderCommand);
        } catch (InvalidHandleCommandException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    @Test
    public void readInfoAboutFolderByIdTest(){
        FolderStorage folderStorage = new FolderStorageInMemory();
        UserID userID = new UserID("Artem");
        FolderID folderID = new FolderID("parent", userID);
        CreateFolderDTO createFolderDTO = createFolder(folderStorage, "folder", userID, folderID);

        GetFolderByNameQuery query = new GetFolderByNameQuery(new AuthToken("1"),
                "folder", userID);
        GetFolderByNameView view = new GetFolderByNameView(folderStorage);

        try {
            GetFolderByNameDTO folderByNameDTO = view.handle(query);
            Assertions.assertEquals(folderByNameDTO.folderID(), createFolderDTO.folderID());

        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void failedReadFolderInfoByNotExistIdTest(){
        FolderStorage folderStorage = new FolderStorageInMemory();
        UserID userID = new UserID("Artem");
        FolderID folderID = new FolderID("parent", userID);
        createFolder(folderStorage, "folder", userID, folderID);

        Assertions.assertEquals(folderStorage.getSizeRecordsList(), 1);

        GetFolderByNameQuery query = new GetFolderByNameQuery(new AuthToken("1"),
                "JHGF", userID);
        GetFolderByNameView view = new GetFolderByNameView(folderStorage);


        Assertions.assertThrows(InvalidHandleCommandException.class, () -> view.handle(query));
    }
}