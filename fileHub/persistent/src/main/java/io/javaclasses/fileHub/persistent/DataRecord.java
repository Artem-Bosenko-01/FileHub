package io.javaclasses.fileHub.persistent;

/**
 * This is abstract base for entity, that saved in {@link Storage storage}.
 *
 * @param <I> type of {@link RecordId id}.
 */
public interface DataRecord<I extends RecordId> {
    I id();
}
