package io.javaclasses.fileHub;

/**
 * This is abstract base for execute {@link Command user command} in Filehub application.
 *
 * @param <C> input {@link Command user command}.
 * @param <E> type of output entity, that generated as a result handling command.
 * */
public interface Process <C extends Command, E>{
    E handle(C inputCommand) throws InvalidHandleCommandException;
}
