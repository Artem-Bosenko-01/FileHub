package io.javaclasses.fileHub.webservices;

import io.javaclasses.fileHub.persistent.users.User;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.UserStorage;

import java.util.Optional;

public class UserStorageBaseStub implements UserStorage {

    @Override
    public void create(User inputDataObject) {

    }

    @Override
    public void update(User inputDataObject) {

    }

    @Override
    public void delete(String dataRecordID) {

    }

    @Override
    public Optional<User> findByID(UserId dataRecordID) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return Optional.empty();
    }
}
