package io.javaclasses.fileHub;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This is abstract storage, that saved some records in java memory.
 *
 * @param <E> type of {@link DataRecord record}.
 * @param <I> identifier {@link RecordID id} for record.
 * */
public abstract class AbstractInMemoryStorage<I extends RecordID, E extends DataRecord<I>> implements Storage<I,E>{

    private final Map<I,E> records = new HashMap<>();

    @Override
    public void create(E inputDataObject) throws DuplicatedIDException {
        if(records.containsKey(inputDataObject.id())) throw new DuplicatedIDException("Duplicate id " + inputDataObject.id());
        records.put(inputDataObject.id(),inputDataObject);
    }

    @Override
    public E findByID(I dataRecordID) throws NotExistIDException{
        Optional<E> user = records.values().stream().filter(e -> e.id().equals(dataRecordID)).findFirst();
        if(user.isPresent()){
            return user.get();
        }
        else throw new NotExistIDException("Id doesn't exist " + dataRecordID);
    }

    @Override
    public void update(E inputDataObject) throws NotExistIDException {
        if(!records.containsKey(inputDataObject.id())) throw new NotExistIDException("Id doesn't exist " + inputDataObject.id());
        records.remove(inputDataObject.id());
        records.put(inputDataObject.id(),inputDataObject);
    }

    @Override
    public void delete(I dataRecordID) throws NotExistIDException {
        if(!records.containsKey(dataRecordID)) throw new NotExistIDException("Id doesn't exist " + dataRecordID);
        records.remove(dataRecordID);
    }

    protected Map<I,E> records(){return records;}
    public int getRecordsSize(){return records.size();}
}
