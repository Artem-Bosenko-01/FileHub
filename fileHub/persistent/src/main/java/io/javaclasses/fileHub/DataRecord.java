package io.javaclasses.fileHub;

/**
 * This is abstract base for entity, that saved in {@link Storage storage}.
 *
 * @param <I> type of {@link RecordID id}.
 * */
public interface DataRecord <I extends RecordID>{
    I id();
}
