package io.javaclasses.fileHub.services.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.DuplicatedUserIdException;
import io.javaclasses.fileHub.persistent.users.User;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.OpenUserProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service for first user registration in Filehub application.
 */
public class RegisterUser implements OpenUserProcess<RegistrationUserCommand, Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(RegisterUser.class);

    private final UserStorage userStorage;

    public RegisterUser(UserStorage userStorage) {

        this.userStorage = Preconditions.checkNotNull(userStorage);
    }

    @Override
    public Boolean handle(RegistrationUserCommand inputCommand) throws InvalidCommandHandlingException {

        if (logger.isInfoEnabled()) {
            logger.info("Start registration user process with id: " + inputCommand.loginName());
        }

        UserId id = new UserId(inputCommand.loginName());

        String password = PasswordEncoder.encode(inputCommand.password());

        User user = new User(id);
        user.setLogin(inputCommand.loginName());
        user.setPassword(password);

        try {

            userStorage.create(user);

            if (logger.isInfoEnabled()) {
                logger.info("Registration was successful :" + user.id());
            }

            return true;

        } catch (DuplicatedUserIdException e) {

            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }

            throw new InvalidCommandHandlingException(e.getMessage());
        }
    }
}
