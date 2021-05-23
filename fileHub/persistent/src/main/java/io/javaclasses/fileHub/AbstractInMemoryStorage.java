package io.javaclasses.fileHub;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * */
public abstract class AbstractInMemoryStorage<I extends RecordID, E extends DataRecord<I>> implements Storage<I,E>{

    private final Map<I,E> records = new HashMap<>();

    @Override
    public void create(E inputDataObject) throws DuplicatedIDException {
        if(records.containsKey(inputDataObject.id())) throw new DuplicatedIDException("Duplicate id" + inputDataObject.id());
        records.put(inputDataObject.id(),inputDataObject);
    }

    @Override
    public Optional<E> findByID(I dataRecordID) {
        return records.values().stream().filter(e -> e.id().equals(dataRecordID)).findFirst();
    }

    @Override
    public void update(E inputDataObject) throws NotExistIDException {
        if(!records.containsKey(inputDataObject.id())) throw new NotExistIDException("Id doest exist" + inputDataObject.id());
        records.remove(inputDataObject.id());
        records.put(inputDataObject.id(),inputDataObject);
    }

    @Override
    public E read(I inputIDObject) throws NotExistIDException {
        if(!records.containsKey(inputIDObject)) throw new NotExistIDException("Id doest exist" + inputIDObject);
        return records.get(inputIDObject);
    }

    public Map<I,E> records(){return records;}
}
