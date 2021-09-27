package io.javaclasses.fileHub.persistent;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Abstract base of CRUD operations for manages {@link DataRecord records}.
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
