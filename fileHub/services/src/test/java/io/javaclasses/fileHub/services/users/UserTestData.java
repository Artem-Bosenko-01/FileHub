package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;

public final class UserTestData {

    public static RegistrationUserCommand registerUser (String loginName){

        return new RegistrationUserCommand(
                loginName,
                "bbb",
                "ccc",
                "56478"
        );
    }

    public static AuthenticationUserCommand authenticateUser (String loginName){

        return new AuthenticationUserCommand(
                loginName,
                "56478"
        );
    }

    public static UpdatingProfileCommand updateUser(UserId id){

        return new UpdatingProfileCommand(
                new AuthToken("564"),
                id,
                "aaa@h.com",
                "cas",
                "abc",
                "56478"
                );
    }

}
