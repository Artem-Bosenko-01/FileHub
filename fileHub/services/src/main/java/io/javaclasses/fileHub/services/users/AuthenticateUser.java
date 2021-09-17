package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.DuplicatedIdException;
import io.javaclasses.fileHub.persistent.users.User;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationUsers;
import io.javaclasses.fileHub.persistent.users.tokens.UserAuthToken;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.OpenUserProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Service to authenticate user in the Filehub application if he exist at {@link UserStorage user storage}.
 */
@Component
public class AuthenticateUser implements OpenUserProcess<AuthenticationUserCommand, AuthToken> {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticateUser.class);

    private final UserStorage userStorage;

    private final AuthorizationStorage authorizationStorage;

    @Autowired
    public AuthenticateUser(UserStorage userStorage, AuthorizationStorage authorizationStorage) {

        this.userStorage = checkNotNull(userStorage);

        this.authorizationStorage = checkNotNull(authorizationStorage);
    }

    @Override
    public AuthToken handle(AuthenticationUserCommand inputCommand)
            throws DuplicatedUserException, UserNotFoundException {

        if (logger.isInfoEnabled()) {
            logger.info("Start authenticated process for user: " + inputCommand.loginName());
        }

        String password = PasswordEncoder.encode(inputCommand.password());

        Optional<User> user = userStorage.findByLoginAndPassword(inputCommand.loginName(), password);

        if (user.isPresent()) {

            AuthToken token = GenerateUniqueUserToken.generateToken(authorizationStorage);

            if (logger.isInfoEnabled()) {
                logger.info("Authenticate process was successful. User: " + user.get().login() + " have token: " + token);
            }

            try {

                authorizationStorage.create(new AuthorizationUsers(new UserAuthToken(token.value()),
                        user.get().id(), ZonedDateTime.now(ZoneId.of("America/Los_Angeles")).plusHours(6)));

                if (logger.isInfoEnabled()) {
                    logger.info("Token for user " + user.get().login() + " was created. Value = " + token.value());
                }

                return token;

            } catch (DuplicatedIdException e) {

                if (logger.isErrorEnabled()) {
                    logger.error(e.getMessage());
                }

                throw new DuplicatedUserException("email", e.getMessage());

            }

        } else {

            if (logger.isErrorEnabled()) {
                logger.error("User with " + inputCommand.loginName() + " not exist");
            }

            throw new UserNotFoundException(inputCommand.loginName());
        }
    }
}
