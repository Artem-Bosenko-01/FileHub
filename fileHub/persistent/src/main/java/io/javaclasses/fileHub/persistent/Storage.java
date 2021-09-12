package io.javaclasses.fileHub.persistent;

import java.util.Optional;

/**
 * This is abstract base of CRUD operations for data manipulations.
 *
 * @param <E> type of {@link DataRecord record}.
 * @param <I> identifier key for record.
 */
public interface Storage<I extends RecordId, E extends DataRecord<I>> {

    void create(E inputDataObject) throws DuplicatedIdException;

    void update(E inputDataObject) throws NotExistedItemException;

    void delete(String dataRecordID) throws NotExistedItemException;

    Optional<E> findByID(I dataRecordID);

}
