package io.javaclasses.fileHub.services.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.DuplicatedUserIdException;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.services.OpenProcess;
import io.javaclasses.fileHub.persistent.users.User;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is service for first registration user in Filehub application.
 */
public class RegistrationUser implements OpenProcess<RegistrationUserCommand, UserId> {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationUser.class);

    private final UserStorage userStorage;

    public RegistrationUser(UserStorage userStorage){
        this.userStorage = Preconditions.checkNotNull(userStorage);
    }

    @Override
    public UserId handle(RegistrationUserCommand inputCommand) throws InvalidHandleCommandException {

        if (logger.isInfoEnabled()) {
            logger.info("Start registration user process with id: " + inputCommand.loginName());
        }

        UserId id = new UserId(inputCommand.loginName());

        String password = PasswordEncoder.encode(inputCommand.password());

        User user = new User(id);
        user.setFirstName(inputCommand.firstName());
        user.setLastName(inputCommand.lastName());
        user.setLogin(inputCommand.loginName());
        user.setPassword(password);

        try {

            userStorage.create(user);

            if (logger.isInfoEnabled()) {
                logger.info("Registration was successful :" + user.id());
            }

            return user.id();

        } catch (DuplicatedUserIdException e) {

            if(logger.isErrorEnabled()){
                logger.error(e.getMessage());
            }

            throw new InvalidHandleCommandException(e.getMessage());
        }
    }
}
