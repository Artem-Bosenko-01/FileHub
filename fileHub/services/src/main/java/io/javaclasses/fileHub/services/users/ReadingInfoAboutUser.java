package io.javaclasses.fileHub.services.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.AbstractInMemoryStorage;
import io.javaclasses.fileHub.persistent.NotExistUserIdException;
import io.javaclasses.fileHub.persistent.users.User;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.services.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is service for getting information about authenticated user in Filehub application.
 */
public class ReadingInfoAboutUser implements View<ReadUserProfileQuery, InfoAboutUserDto> {

    private static final Logger logger = LoggerFactory.getLogger(ReadingInfoAboutUser.class);

    private final AbstractInMemoryStorage<UserId, User> userStorage;

    public ReadingInfoAboutUser(AbstractInMemoryStorage<UserId, User> userStorage) {
        this.userStorage = Preconditions.checkNotNull(userStorage);
    }

    @Override
    public InfoAboutUserDto handle(ReadUserProfileQuery query) throws InvalidHandleCommandException {

        if (logger.isInfoEnabled()) {
            logger.info("Start read user process with id: " + query.id());
        }

        try {

            User findUser = userStorage.findByID(query.id());

            if (logger.isInfoEnabled()) {
                logger.info("User " + findUser.login() + " exist!");
            }

            return new InfoAboutUserDto(
                    findUser.id(),
                    findUser.login(),
                    findUser.password(),
                    findUser.firstName(),
                    findUser.lastName()
            );

        } catch (NotExistUserIdException e) {

            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }

            throw new InvalidHandleCommandException(e.getMessage());
        }
    }
}
