package io.javaclasses.fileHub.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.DuplicatedIDException;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.OpenProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is service for first registration user in Filehub application.
 */
public class UserRegistrationProcess implements OpenProcess<RegisterUserCommand, UserDTO> {

    private final UserStorage userStorage;
    private final Logger logger = LoggerFactory.getLogger(UserRegistrationProcess.class);

    public UserRegistrationProcess(UserStorage userStorage){
        this.userStorage = Preconditions.checkNotNull(userStorage);
    }

    @Override
    public UserDTO handle(RegisterUserCommand inputCommand) throws InvalidHandleCommandException {
        if (logger.isInfoEnabled()) {
            logger.info("Start registration user process with id: " + inputCommand.loginName());
        }

        UserID id = new UserID(inputCommand.loginName());
        String login = inputCommand.loginName();
        String password = PasswordEncoder.encode(inputCommand.password());
        String firstName = inputCommand.firstName();
        String lastName = inputCommand.lastName();

        User user = new User(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setPassword(password);
        try {
            userStorage.create(user);
            if (logger.isInfoEnabled()) {
                logger.info("Registration was successful :" + user.id());
            }
            return new UserDTO(id,login,password,firstName,lastName);
        } catch (DuplicatedIDException e) {
            if(logger.isErrorEnabled()){
                logger.error(e.getMessage());
            }
            throw new InvalidHandleCommandException(e.getMessage());
        }
    }
}
