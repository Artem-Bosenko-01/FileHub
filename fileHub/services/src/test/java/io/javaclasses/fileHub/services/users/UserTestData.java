package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;

public final class UserTestData {

    private static final String KevinLoginName = "kevin@gmail.com";
    private static final String KevinPassword = "acsa7csa4";
    private static final String JohnLoginName = "john@gmail.com";
    private static final String JohnPassword = "56498";


    public static UserId registerKevinUser(UserStorage userStorage) throws InvalidHandleCommandException {

        RegistrationUserCommand registrationUserCommand = new RegistrationUserCommand(KevinLoginName, KevinPassword);

        RegistrationUser registrationUser = new RegistrationUser(userStorage);

        registrationUser.handle(registrationUserCommand);
        return new UserId(registrationUserCommand.loginName());
    }

    public static AuthToken authenticateKevinUser(UserStorage userStorage, AuthorizationStorage authorizationStorage)
            throws InvalidHandleCommandException {

        AuthenticationUserCommand authenticationUserCommand = new AuthenticationUserCommand(KevinLoginName, KevinPassword);

        AuthenticationUser authenticationUser = new AuthenticationUser(userStorage, authorizationStorage);

        return authenticationUser.handle(authenticationUserCommand);

    }


    public static UserId registerJohnUser(UserStorage userStorage) throws InvalidHandleCommandException {

        RegistrationUserCommand registrationUserCommand = new RegistrationUserCommand(JohnLoginName, JohnPassword);

        RegistrationUser registrationUser = new RegistrationUser(userStorage);

        registrationUser.handle(registrationUserCommand);
        return new UserId(registrationUserCommand.loginName());
    }

    public static AuthToken authenticateJohnUser(UserStorage userStorage, AuthorizationStorage authorizationStorage)
            throws InvalidHandleCommandException {

        AuthenticationUserCommand authenticationUserCommand = new AuthenticationUserCommand(JohnLoginName, JohnPassword);

        AuthenticationUser authenticationUser = new AuthenticationUser(userStorage, authorizationStorage);

        return authenticationUser.handle(authenticationUserCommand);

    }
}
