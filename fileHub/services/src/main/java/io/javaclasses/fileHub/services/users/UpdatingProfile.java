package io.javaclasses.fileHub.services.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.NotExistUserIdException;
import io.javaclasses.fileHub.services.SecuredProcess;
import io.javaclasses.fileHub.persistent.users.User;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This is service for updating information about user in {@link UserStorage user table}.
 */
public class UpdatingProfile implements SecuredProcess<UpdatingProfileCommand, String> {

    private static final Logger logger = LoggerFactory.getLogger(UpdatingProfile.class);

    private final UserStorage userStorage;


    public UpdatingProfile(UserStorage userStorage) {
        this.userStorage = Preconditions.checkNotNull(userStorage);
    }

    @Override
    public String handle(UpdatingProfileCommand inputCommand) throws InvalidHandleCommandException {

        if (logger.isInfoEnabled()) {
            logger.info("Start update user process with id: " + inputCommand.userID());
        }

        try {

            User user = userStorage.findByID(inputCommand.userID());

            user.setLogin(inputCommand.newLoginName());
            user.setFirstName(inputCommand.firstName());
            user.setLastName(inputCommand.lastName());
            user.setPassword(PasswordEncoder.encode(inputCommand.password()));

            userStorage.update(user);

            if (logger.isInfoEnabled()) {
                logger.info("Update was successful :" + inputCommand.userID());
            }

            return user.login();

        } catch (NotExistUserIdException e) {

            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }

            throw new InvalidHandleCommandException(e.getMessage());
        }

    }
}

