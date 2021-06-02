package io.javaclasses.fileHub.persistent.users;

import io.javaclasses.fileHub.persistent.Storage;

import java.util.Optional;

public interface UserStorage extends Storage<UserId, User> {

    Optional<User> findByLoginAndPassword(String login, String password);

    Optional<User> findByLogin(String login);

}
