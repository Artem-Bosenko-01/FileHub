package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.InvalidValidationCommandDataException;

public final class UserTestData {

    private static final String KevinLoginName = "kevin@gmail.com";
    private static final String KevinPassword = "acsa7csa4";
    private static final String JohnLoginName = "john@gmail.com";
    private static final String JohnPassword = "564988";


    public static UserId registerKevinUser(UserStorage userStorage) throws InvalidCommandHandlingException, InvalidValidationCommandDataException {

        RegistrationUserCommand registrationUserCommand = new RegistrationUserCommand(KevinLoginName, KevinPassword);

        RegisterUser registerUser = new RegisterUser(userStorage);

        registerUser.handle(registrationUserCommand);
        return new UserId(registrationUserCommand.loginName());
    }

    public static AuthToken authenticateKevinUser(UserStorage userStorage, AuthorizationStorage authorizationStorage)
            throws InvalidCommandHandlingException, InvalidValidationCommandDataException {

        AuthenticationUserCommand authenticationUserCommand = new AuthenticationUserCommand(KevinLoginName, KevinPassword);

        AuthenticateUser authenticateUser = new AuthenticateUser(userStorage, authorizationStorage);

        return authenticateUser.handle(authenticationUserCommand);

    }


    public static UserId registerJohnUser(UserStorage userStorage) throws InvalidCommandHandlingException, InvalidValidationCommandDataException {

        RegistrationUserCommand registrationUserCommand = new RegistrationUserCommand(JohnLoginName, JohnPassword);

        RegisterUser registerUser = new RegisterUser(userStorage);

        registerUser.handle(registrationUserCommand);
        return new UserId(registrationUserCommand.loginName());
    }

    public static AuthToken authenticateJohnUser(UserStorage userStorage, AuthorizationStorage authorizationStorage)
            throws InvalidCommandHandlingException, InvalidValidationCommandDataException {

        AuthenticationUserCommand authenticationUserCommand = new AuthenticationUserCommand(JohnLoginName, JohnPassword);

        AuthenticateUser authenticateUser = new AuthenticateUser(userStorage, authorizationStorage);

        return authenticateUser.handle(authenticationUserCommand);

    }
}
