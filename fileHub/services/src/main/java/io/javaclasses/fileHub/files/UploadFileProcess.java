package io.javaclasses.fileHub.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.SecuredProcess;
import io.javaclasses.fileHub.files.content.CreateFileContentCommand;
import io.javaclasses.fileHub.files.content.CreateFileContentDTO;
import io.javaclasses.fileHub.files.content.CreateFileContentProcess;
import io.javaclasses.fileHub.files.content.FIleContentStorage;

/**
 * This is service to uploading new file in authenticated user's directory.
 */
public class UploadFileProcess implements SecuredProcess<UploadFileCommand, UploadFileDTO> {

    private final FIleContentStorage contentStorage;
    private final FileStorage fileStorage;

    public UploadFileProcess(FIleContentStorage contentStorage, FileStorage fileStorage) {
        this.contentStorage = Preconditions.checkNotNull(contentStorage);
        this.fileStorage = Preconditions.checkNotNull(fileStorage);
    }


    @Override
    public UploadFileDTO handle(UploadFileCommand inputCommand) throws InvalidHandleCommandException {
        CreateFileCommand createFileCommand = new CreateFileCommand(inputCommand.token(),
                inputCommand.name(), inputCommand.mimeType(), inputCommand.owner(), inputCommand.folder());
        CreateFileProcess createFileManagementProcess = new CreateFileProcess(fileStorage);

        CreateFileDTO fileDTO = createFileManagementProcess.handle(createFileCommand);

        CreateFileContentCommand contentCommand = new CreateFileContentCommand(inputCommand.token(), fileDTO.fileID(), inputCommand.content());
        CreateFileContentProcess createFileContentProcess = new CreateFileContentProcess(contentStorage);

        CreateFileContentDTO fileContentDTO = createFileContentProcess.handle(contentCommand);

        return new UploadFileDTO(fileDTO.fileID(), fileContentDTO.content());
    }
}
