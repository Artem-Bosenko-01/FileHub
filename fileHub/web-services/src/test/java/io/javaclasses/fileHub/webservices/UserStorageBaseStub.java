package io.javaclasses.fileHub.webservices;

import io.javaclasses.fileHub.persistent.DuplicatedUserIdException;
import io.javaclasses.fileHub.persistent.NotExistUserIdException;
import io.javaclasses.fileHub.persistent.users.User;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.UserStorage;

import java.util.Optional;

public class UserStorageBaseStub implements UserStorage {

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public void create(User inputDataObject) {
    }

    @Override
    public void update(User inputDataObject) {
    }

    @Override
    public void delete(UserId dataRecordID) {
    }

    @Override
    public Optional<User> findByID(UserId dataRecordID) {
        return Optional.empty();
    }
}
