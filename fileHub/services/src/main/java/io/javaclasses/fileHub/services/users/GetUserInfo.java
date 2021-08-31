package io.javaclasses.fileHub.services.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.users.User;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Service to get information about the authenticated user.
 */
public class GetUserInfo extends View<GetUserQuery, InfoAboutUserDto> {

    private static final Logger logger = LoggerFactory.getLogger(GetUserInfo.class);

    private final UserStorage userStorage;

    public GetUserInfo(UserStorage userStorage, AuthorizationStorage authorizationStorage) {

        super(Preconditions.checkNotNull(authorizationStorage));

        this.userStorage = Preconditions.checkNotNull(userStorage);
    }

    @Override
    protected InfoAboutUserDto doHandle(GetUserQuery query) throws InvalidCommandHandlingException {

        if (logger.isInfoEnabled()) {
            logger.info("Start read user process with id: " + query.id());
        }


        Optional<User> findUser = userStorage.findByID(query.id());

        if (findUser.isPresent()) {

            if (logger.isInfoEnabled()) {
                logger.info("User " + findUser.get().login() + " exist!");
            }

            return new InfoAboutUserDto(
                    findUser.get().id(),
                    findUser.get().login());

        } else {

            if (logger.isErrorEnabled()) {
                logger.error("User with id doesn't exist " + query.id());
            }

            throw new InvalidCommandHandlingException("User with id doesn't exist " + query.id());
        }

    }
}
