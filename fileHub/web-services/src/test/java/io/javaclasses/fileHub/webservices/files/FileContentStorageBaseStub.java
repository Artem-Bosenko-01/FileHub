package io.javaclasses.fileHub.webservices.files;

import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.content.FIleContentStorage;
import io.javaclasses.fileHub.persistent.files.content.FileContent;

import java.util.Optional;

public class FileContentStorageBaseStub implements FIleContentStorage {
    @Override
    public void create(FileContent inputDataObject) {

    }

    @Override
    public void update(FileContent inputDataObject) {

    }

    @Override
    public void delete(String dataRecordID) {

    }

    @Override
    public Optional<FileContent> findByID(FileId dataRecordID) {

        FileContent fileContent = new FileContent(dataRecordID.toString());

        fileContent.setContent(new byte[]{1, 2, 3});

        return Optional.of(fileContent);
    }
}
