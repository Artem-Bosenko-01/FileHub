package io.javaclasses.fileHub.persistent.users;

import io.javaclasses.fileHub.persistent.Storage;

import java.util.Optional;

/**
 * The {@link Storage storage} that contains instruments for managing {@link User users} in the FileHub application.
 */
public interface UserStorage extends Storage<UserId, User> {

    Optional<User> findByLoginAndPassword(String login, String password);

}
