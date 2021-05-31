package io.javaclasses.fileHub.folders;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.View;
import io.javaclasses.fileHub.files.FileID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * This is service to get existed file in authenticated user's directory by {@link FileID id}.
 */
public class GetFolderByNameView implements View<GetFolderByNameQuery, GetFolderByNameDTO> {


    private final FolderStorage folderStorageInMemory;
    private final Logger logger = LoggerFactory.getLogger(GetFolderByNameView.class);

    public GetFolderByNameView(FolderStorage userStorage) {
        this.folderStorageInMemory = Preconditions.checkNotNull(userStorage);
    }

    @Override
    public GetFolderByNameDTO handle(GetFolderByNameQuery query) throws InvalidHandleCommandException {

        if(logger.isInfoEnabled()){
            logger.info("Start get directory for user: " + query.owner()+" and name: " + query.name());
        }

        Optional<Folder> folder = folderStorageInMemory.findFolderByName(query.name(), query.owner());
        if(folder.isPresent()){

            if(logger.isInfoEnabled()){
                logger.info("Read folder by User: " +query.owner() + ". And name: " + query.name() + ". Was successful");
            }

            return new GetFolderByNameDTO(folder.get().id());
        }
        else {
            if(logger.isErrorEnabled()){
                logger.error("Folder with name doesn't exist " + query.name()+".User " + query.owner());
            }

            throw new InvalidHandleCommandException(
                    "Folder with name doesn't exist " + query.name()+".User " + query.owner());
        }
    }
}
