package io.javaclasses.fileHub.persistent;

/**
 * This is abstract base of CRUD operations for data manipulations.
 *
 * @param <E> type of {@link DataRecord record}.
 * @param <I> identifier key for record.
 */
public interface Storage<I extends RecordId, E extends DataRecord<I>> {

    void create(E inputDataObject) throws DuplicatedUserIdException;

    void update(E inputDataObject) throws NotExistUserIdException;

    void delete(I dataRecordID) throws NotExistUserIdException;

    E findByID(I dataRecordID) throws NotExistUserIdException;

}
