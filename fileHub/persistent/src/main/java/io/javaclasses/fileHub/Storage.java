package io.javaclasses.fileHub;

/**
 * This is abstract base of CRUD operations for data manipulations.
 *
 * @param <E> type of {@link DataRecord record}.
 * @param <I> identifier key for record.
 * */
public interface Storage <I extends RecordID, E extends DataRecord<I>>{
    void create(E inputDataObject) throws DuplicatedIDException;
    void update(E inputDataObject) throws NotExistIDException;
    void delete(I dataRecordID) throws NotExistIDException;
    E findByID(I dataRecordID) throws NotExistIDException;
}
