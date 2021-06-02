package io.javaclasses.fileHub.services.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.users.User;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.services.OpenProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.UUID;

/**
 * This is service for authentication user in Filehub application if he is been in {@link UserStorage user storage}.
 */
public class AuthenticationUser implements OpenProcess<AuthenticationUserCommand, AuthToken> {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationUser.class);

    private final UserStorage userStorage;

    public AuthenticationUser(UserStorage userStorage) {
        this.userStorage = Preconditions.checkNotNull(userStorage);
    }

    @Override
    public AuthToken handle(AuthenticationUserCommand inputCommand) throws InvalidHandleCommandException {

        if (logger.isInfoEnabled()) {
            logger.info("Start authenticated process for user: " + inputCommand.loginName());
        }

        String password = PasswordEncoder.encode(inputCommand.password());

        Optional<User> user = userStorage.findByLoginAndPassword(inputCommand.loginName(), password);

        if (user.isPresent()) {

            AuthToken token = new AuthToken(UUID.randomUUID().toString());

            if (logger.isInfoEnabled()) {
                logger.info("Authenticate process was successful. User: " + user.get().login() + " have token: " + token);
            }

            return inputCommand.token();

        } else {

            if (logger.isErrorEnabled()) {
                logger.error("User with " + inputCommand.loginName() + " not exist");
            }

            throw new InvalidHandleCommandException("User with " + inputCommand.loginName() + " not exist");
        }
    }
}
