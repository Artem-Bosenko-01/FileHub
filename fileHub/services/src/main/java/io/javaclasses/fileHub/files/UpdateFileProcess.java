package io.javaclasses.fileHub.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.SecuredProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateFileProcess implements SecuredProcess<UpdateFileCommand, CreateFileDTO> {

    private final FileStorageInMemory fileStorage;
    private final Logger logger = LoggerFactory.getLogger(UpdateFileProcess.class);

    public UpdateFileProcess(FileStorageInMemory fileStorage){
        this.fileStorage = Preconditions.checkNotNull(fileStorage);
    }

    @Override
    public CreateFileDTO handle(UpdateFileCommand inputCommand) throws InvalidHandleCommandException {
        File file = new File(inputCommand.id());
        file.setName(inputCommand.name());
        file.setSize(inputCommand.size());
        file.setMimeType(inputCommand.mimeType());
        file.setFolder(inputCommand.folder());

        try {
            fileStorage.update(file);
            return new CreateFileDTO(file.id(),file.name(),file.mimeType(),file.owner(), file.folder());
        } catch (NotExistIDException e) {
            throw new InvalidHandleCommandException(e.getMessage());
        }
    }
}
