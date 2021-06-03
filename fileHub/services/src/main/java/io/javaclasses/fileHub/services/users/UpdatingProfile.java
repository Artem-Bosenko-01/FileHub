package io.javaclasses.fileHub.services.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.NotExistUserIdException;
import io.javaclasses.fileHub.services.SecuredUserProcess;
import io.javaclasses.fileHub.persistent.users.User;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;


/**
 * This is service for updating information about user in {@link UserStorage user table}.
 */
public class UpdatingProfile implements SecuredUserProcess<UpdatingProfileCommand, String> {

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

        Optional<User> user = userStorage.findByID(inputCommand.userID());

        try {

            if (user.isPresent()) {

                user.get().setLogin(inputCommand.newLoginName());
                user.get().setFirstName(inputCommand.firstName());
                user.get().setLastName(inputCommand.lastName());
                user.get().setPassword(PasswordEncoder.encode(inputCommand.password()));


                userStorage.update(user.get());

                if (logger.isInfoEnabled()) {
                    logger.info("Update was successful : " + user.get().id());
                }

                return user.get().login();

            } else {

                if (logger.isErrorEnabled()) {
                    logger.error("User with id doesn't exist " + inputCommand.userID());
                }

                throw new InvalidHandleCommandException("User with id doesn't exist " + inputCommand.userID());
            }

        } catch (NotExistUserIdException e) {

            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }

            throw new InvalidHandleCommandException(e.getMessage());

        }

    }

}

