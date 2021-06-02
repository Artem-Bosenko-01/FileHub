package io.javaclasses.fileHub.persistent.users;

import io.javaclasses.fileHub.persistent.AbstractInMemoryStorage;

import java.util.Optional;

public class UserStorageInMemory extends AbstractInMemoryStorage<UserId, User> implements UserStorage {

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {

        return records().
                values().
                stream().
                filter((user) -> user.login().equals(login) && user.password().equals(password)).
                findFirst();

    }

    @Override
    public Optional<User> findByLogin(String login) {

        return records().
                values().
                stream().
                filter((user) -> user.login().equals(login)).
                findFirst();

    }
}
