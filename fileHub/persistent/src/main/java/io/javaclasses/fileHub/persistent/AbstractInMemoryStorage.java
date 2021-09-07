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

        if (records.containsKey(inputDataObject.id())) {
            throw new DuplicatedUserIdException("Duplicate id " + inputDataObject.id());
        }

        records.put(inputDataObject.id(), inputDataObject);
    }

    @Override
    public Optional<E> findByID(I dataRecordID) {

        return records.values().stream().filter(e -> e.id().equals(dataRecordID)).findFirst();

    }

    @Override
    public void update(E inputDataObject) throws NotExistedItem {

        if (!records.containsKey(inputDataObject.id())) {
            throw new NotExistedItem("Id doesn't exist " + inputDataObject.id());
        }

        records.put(inputDataObject.id(), inputDataObject);
    }

    @Override
    public void delete(String dataRecordID) throws NotExistedItem {

        Optional<I> foundKey = records.keySet().stream().filter(key -> key.toString().equals(dataRecordID)).findFirst();

        if (foundKey.isPresent()) {

            records.remove(foundKey.get());

        } else {

            throw new NotExistedItem("Id doesn't exist " + dataRecordID);
        }
    }

    protected Map<I, E> records() {
        return records;
    }

    public int getRecordsSize() {
        return records.size();
    }
}
