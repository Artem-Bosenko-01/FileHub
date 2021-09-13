package io.javaclasses.fileHub.persistent;

/**
 * Abstract base for entity. Represents main persistent object that contains some data about entity.
 *
 * @param <I> type of {@link RecordId id}.
 */
public interface DataRecord<I extends RecordId> {
    I id();
}
