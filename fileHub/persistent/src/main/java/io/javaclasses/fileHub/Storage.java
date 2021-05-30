package io.javaclasses.fileHub;

import java.util.Optional;

public interface Storage <I extends RecordID, E extends DataRecord<I>>{
    void create(E inputDataObject) throws DuplicatedIDException;
    void update(E inputDataObject) throws NotExistIDException;
    void delete(I dataRecordID) throws NotExistIDException;
    E findByID(I dataRecordID) throws NotExistIDException;
}
