package io.javaclasses.fileHub.webservices.user;

import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.services.users.RegisterUser;

public class RegisterUserBaseStub extends RegisterUser {

    public RegisterUserBaseStub(UserStorage userStorage) {

        super(userStorage);
    }
}
