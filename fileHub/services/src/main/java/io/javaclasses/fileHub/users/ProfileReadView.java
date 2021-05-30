package io.javaclasses.fileHub.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * This is service for getting information about authenticated user in Filehub application.
 */
public class ProfileReadView implements View<ProfileReadQuery, UserDTO> {

    private final AbstractInMemoryStorage<UserID, User> userStorage;
    private final Logger logger = LoggerFactory.getLogger(ProfileReadView.class);

    public ProfileReadView(AbstractInMemoryStorage<UserID, User> userStorage) {
        this.userStorage = Preconditions.checkNotNull(userStorage);
    }

    @Override
    public UserDTO handle(ProfileReadQuery query) throws InvalidHandleCommandException {

        if (logger.isInfoEnabled()) {
            logger.info("Start read user process with id: " + query.id());
        }

        try {
            User findUser = userStorage.findByID(query.id());

            if (logger.isInfoEnabled()) {
                logger.info("User " + findUser.login() + " exist!");
            }
            return new UserDTO(findUser.id(),
                    findUser.login(),
                    findUser.password(),
                    findUser.firstName(),
                    findUser.lastName());
        } catch (NotExistIDException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw new InvalidHandleCommandException(e.getMessage());
        }
    }
}
