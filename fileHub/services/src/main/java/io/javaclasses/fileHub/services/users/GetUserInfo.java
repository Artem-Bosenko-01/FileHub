package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.users.User;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationUsers;
import io.javaclasses.fileHub.persistent.users.tokens.UserAuthToken;
import io.javaclasses.fileHub.services.View;
import io.javaclasses.fileHub.services.files.UsersTokenNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Service to get information about the authenticated user.
 */
@Component
public class GetUserInfo extends View<GetUserQuery, InfoAboutUserDto> {

    private static final Logger logger = LoggerFactory.getLogger(GetUserInfo.class);

    private final UserStorage userStorage;
    private final AuthorizationStorage authorizationStorage;

    @Autowired
    public GetUserInfo(@Qualifier("userJDBCStorage") UserStorage userStorage,
                       @Qualifier("authorizationJDBCStorage") AuthorizationStorage authorizationStorage) {

        super(checkNotNull(authorizationStorage));

        this.userStorage = checkNotNull(userStorage);
        this.authorizationStorage = checkNotNull(authorizationStorage);
    }

    @Override
    protected InfoAboutUserDto doHandle(GetUserQuery query) throws UserNotFoundException, UsersTokenNotFoundException {

        Optional<AuthorizationUsers> authenticatedUser = authorizationStorage.
                findByID(new UserAuthToken(query.token().value()));

        if (authenticatedUser.isPresent()) {

            UserId userId = authenticatedUser.get().userID();

            if (logger.isInfoEnabled()) {
                logger.info("Start read user process with id: " + userId);
            }


            Optional<User> findUser = userStorage.findByID(userId);

            if (findUser.isPresent()) {

                if (logger.isInfoEnabled()) {
                    logger.info("User " + findUser.get().login() + " exist!");
                }

                return new InfoAboutUserDto(
                        findUser.get().id(),
                        findUser.get().login());

            } else {

                if (logger.isErrorEnabled()) {
                    logger.error("User with id doesn't exist " + userId);
                }

                throw new UserNotFoundException(userId.toString());
            }

        } else {

            if (logger.isErrorEnabled()) {
                logger.error("Cannot find user by token: " + query.token());
            }

            throw new UsersTokenNotFoundException(query.token());
        }
    }
}
