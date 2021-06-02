package io.javaclasses.fileHub.persistent;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This is abstract storage, that saved some records in runtime memory.
 *
 * @param <E> type of {@link DataRecord record}.
 * @param <I> identifier {@link RecordId id} for record.
 */
public abstract class AbstractInMemoryStorage<I extends RecordId, E extends DataRecord<I>> implements Storage<I, E> {

    private final Map<I, E> records = new HashMap<>();

    @Override
    public void create(E inputDataObject) throws DuplicatedUserIdException {

        if (records.containsKey(inputDataObject.id())){
            throw new DuplicatedUserIdException("Duplicate id " + inputDataObject.id());
        }

        records.put(inputDataObject.id(), inputDataObject);
    }

    @Override
    public E findByID(I dataRecordID) throws NotExistUserIdException {

        Optional<E> user = records.values().stream().filter(e -> e.id().equals(dataRecordID)).findFirst();

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NotExistUserIdException("Id doesn't exist " + dataRecordID);
        }

    }

    @Override
    public void update(E inputDataObject) throws NotExistUserIdException {

        if (!records.containsKey(inputDataObject.id())) {
            throw new NotExistUserIdException("Id doesn't exist " + inputDataObject.id());
        }

        records.put(inputDataObject.id(), inputDataObject);
    }

    @Override
    public void delete(I dataRecordID) throws NotExistUserIdException {

        if (!records.containsKey(dataRecordID)) {
            throw new NotExistUserIdException("Id doesn't exist " + dataRecordID);
        }

        records.remove(dataRecordID);
    }

    protected Map<I, E> records() {
        return records;
    }

    public int getRecordsSize() {
        return records.size();
    }
}
