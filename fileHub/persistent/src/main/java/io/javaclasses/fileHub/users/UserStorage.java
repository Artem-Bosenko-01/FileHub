package io.javaclasses.fileHub.users;

import io.javaclasses.fileHub.Storage;

import java.util.Optional;

public interface UserStorage extends Storage<UserID,User> {
    Optional<User> findByLoginAndPassword(String login, String password);
}
