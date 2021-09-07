package io.javaclasses.fileHub.services.files.content;

import io.javaclasses.fileHub.persistent.NotExistedItem;
import io.javaclasses.fileHub.persistent.files.*;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationUsers;
import io.javaclasses.fileHub.persistent.users.tokens.UserAuthToken;
import io.javaclasses.fileHub.services.View;
import io.javaclasses.fileHub.services.files.FileSystemItemDto;
import io.javaclasses.fileHub.services.files.ItemType;
import io.javaclasses.fileHub.services.files.UsersTokenNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service to get folder's content for existed {@link FolderId folder}.
 */
public class GetFolderContent extends View<GetFolderContentQuery, GetFolderContentDTO> {

    private static final Logger logger = LoggerFactory.getLogger(GetFolderContent.class);

    private final FolderStorage folderStorage;
    private final FileStorage fileStorage;
    private final AuthorizationStorage authorizationStorage;

    public GetFolderContent(FolderStorage folderStorage, FileStorage fileStorage,
                            AuthorizationStorage authorizationStorage) {

        super(authorizationStorage);

        this.folderStorage = folderStorage;
        this.fileStorage = fileStorage;
        this.authorizationStorage = authorizationStorage;

    }


    @Override
    protected GetFolderContentDTO doHandle(GetFolderContentQuery query)
            throws UsersTokenNotFoundException, InvalidFolderContentGetting {

        Optional<AuthorizationUsers> owner = authorizationStorage.findByID(new UserAuthToken(query.token().value()));

        if (owner.isPresent()) {

            String userID = owner.get().userID().toString();

            if (logger.isInfoEnabled()) {
                logger.info("Start get folder's content by id " + query.id());
            }

            try {

                List<Folder> folders = folderStorage.findAllFoldersByParentFolderId(query.id(), userID);
                List<File> files = fileStorage.findAllFilesByFolderIdAndUserId(query.id(), userID);

                if (logger.isInfoEnabled()) {
                    logger.info("Getting folder's content was successful by id " + query.id());
                }

                return new GetFolderContentDTO(convertToResultDto(folders, files));


            } catch (NotExistedItem e) {

                if (logger.isErrorEnabled()) {
                    logger.error(e.getMessage());
                }

                throw new InvalidFolderContentGetting(e.getMessage());
            }
        } else {

            if (logger.isErrorEnabled()) {

                logger.error("Cannot find user by token: " + query.token());
            }

            throw new UsersTokenNotFoundException(query.token());
        }
    }

    private List<FileSystemItemDto> convertToResultDto(List<Folder> folders, List<File> files) {

        List<FileSystemItemDto> convertedContent = new ArrayList<>();

        if (folders.size() > 0) {

            folders.forEach(folder -> convertedContent.add(new FileSystemItemDto(folder.id().toString(),
                    folder.name(), folder.itemsAmount(), ItemType.FOLDER, folder.parentFolder())));
        }

        if (files.size() > 0) {

            files.forEach(file -> convertedContent.add(new FileSystemItemDto(file.id().toString(),
                    file.name(), file.size(), ItemType.FILE, file.folder()).setMimeType(file.mimeType())));
        }

        return convertedContent;
    }
}
