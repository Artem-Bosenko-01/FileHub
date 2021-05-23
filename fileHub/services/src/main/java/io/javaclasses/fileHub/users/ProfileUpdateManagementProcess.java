package io.javaclasses.fileHub.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.AbstractInMemoryStorage;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.SecuredProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 */
public class ProfileUpdateManagementProcess implements SecuredProcess<ProfileUpdateManagementCommand, UserRegisterDTO> {
    private final AbstractInMemoryStorage<UserID, User> userStorage;
    private final Logger logger = LoggerFactory.getLogger(ProfileUpdateManagementProcess.class);

    public ProfileUpdateManagementProcess(AbstractInMemoryStorage<UserID, User> userStorage) {
        this.userStorage = Preconditions.checkNotNull(userStorage);
    }

    @Override
    public UserRegisterDTO handle(ProfileUpdateManagementCommand inputCommand) throws InvalidHandleCommandException {
        if (logger.isInfoEnabled()) {
            logger.info("Start update user process with id: " + inputCommand.id());
        }
        User user = new User(inputCommand.id());
        user.setFirstName(inputCommand.firstName());
        user.setLastName(inputCommand.lastName());
        user.setLogin(inputCommand.loginName());
        user.setPassword(PasswordEncoder.encode(inputCommand.password()));

        try {
            userStorage.update(user);

            if (logger.isInfoEnabled()) {
                logger.info("Update was successful :" + inputCommand.id());
            }

            return new UserRegisterDTO(user.userID(),
                    user.login(),
                    user.password(),
                    user.firstName(),
                    user.lastName());
        } catch (NotExistIDException e) {
            logger.error("User with " + inputCommand.id() + " doesn't exist");
            throw new InvalidHandleCommandException("Do not find id " + inputCommand.id());
        }

    }
}

