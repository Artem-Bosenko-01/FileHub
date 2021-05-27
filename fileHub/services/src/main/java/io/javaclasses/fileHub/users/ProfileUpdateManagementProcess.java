package io.javaclasses.fileHub.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.AbstractInMemoryStorage;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.SecuredProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;


/**
 *
 */
public class ProfileUpdateManagementProcess implements SecuredProcess<ProfileUpdateManagementCommand, UserRegisterDTO> {
    private final UserStorage userStorage;
    private final Logger logger = LoggerFactory.getLogger(ProfileUpdateManagementProcess.class);

    public ProfileUpdateManagementProcess(UserStorage userStorage) {
        this.userStorage = Preconditions.checkNotNull(userStorage);
    }

    @Override
    public UserRegisterDTO handle(ProfileUpdateManagementCommand inputCommand) throws InvalidHandleCommandException {
        if (logger.isInfoEnabled()) {
            logger.info("Start update user process with id: " + inputCommand.userID());
        }

        try {
            Optional<User> user = userStorage.findByID(inputCommand.userID());
            if(user.isPresent()){
                user.get().setLogin(inputCommand.newLoginName());
                user.get().setFirstName(inputCommand.firstName());
                user.get().setLastName(inputCommand.lastName());
                user.get().setPassword(PasswordEncoder.encode(inputCommand.password()));

                userStorage.update(user.get());

                if (logger.isInfoEnabled()) {
                    logger.info("Update was successful :" + inputCommand.userID());
                }

                return new UserRegisterDTO(user.get().id(),
                        user.get().login(),
                        user.get().password(),
                        user.get().firstName(),
                        user.get().lastName());
            }
            else throw new InvalidHandleCommandException("Do not find id " + inputCommand.userID());
        } catch (NotExistIDException e) {
            if(logger.isErrorEnabled()){

                logger.error("User with " + inputCommand.userID() + " doesn't exist");

            }throw new InvalidHandleCommandException("Do not find id " + inputCommand.userID());
        }

    }
}

