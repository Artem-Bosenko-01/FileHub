package io.javaclasses.fileHub.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.SecuredProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;


/**
 * This is service for updating information about user in {@link UserStorage user table}.
 */
public class ProfileUpdateProcess implements SecuredProcess<ProfileUpdateCommand, UserDTO> {
    private final UserStorage userStorage;
    private final Logger logger = LoggerFactory.getLogger(ProfileUpdateProcess.class);

    public ProfileUpdateProcess(UserStorage userStorage) {
        this.userStorage = Preconditions.checkNotNull(userStorage);
    }

    @Override
    public UserDTO handle(ProfileUpdateCommand inputCommand) throws InvalidHandleCommandException {
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

            return new UserDTO(user.id(),
                    user.login(),
                    user.password(),
                    user.firstName(),
                    user.lastName());
        } catch (NotExistIDException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw new InvalidHandleCommandException(e.getMessage());
        }

    }
}

