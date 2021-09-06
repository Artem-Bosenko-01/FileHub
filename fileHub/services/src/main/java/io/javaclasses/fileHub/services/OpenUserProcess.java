package io.javaclasses.fileHub.services;

/**
 * This is base for execute {@link AnonymousUserCommand user command} in Filehub application.
 *
 * @param <C> input {@link AnonymousUserCommand user command}.
 * @param <E> type of output entity, that generated as a result handling command.
 * */
public interface OpenUserProcess<C extends AnonymousUserCommand, E> extends UserProcess<C,E> { }
