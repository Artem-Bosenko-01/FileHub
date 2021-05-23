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
 *
 * */
public class ProfileReadManagementProcess implements SecuredProcess<ProfileReadManagementCommand, UserRegisterDTO> {

    private final AbstractInMemoryStorage<UserID, User> userStorage;
    private final Logger logger = LoggerFactory.getLogger(ProfileReadManagementProcess.class);

    public ProfileReadManagementProcess(AbstractInMemoryStorage<UserID, User> userStorage){
        this.userStorage = Preconditions.checkNotNull(userStorage);
    }

    @Override
    public UserRegisterDTO handle(ProfileReadManagementCommand inputCommand) throws InvalidHandleCommandException {

        if(logger.isInfoEnabled()){
            logger.info("Start read user process with id: " + inputCommand.id());
        }

        Optional<User> findUser = Optional.empty();
        try {
            findUser = userStorage.findByID(inputCommand.id());
        } catch (NotExistIDException e) {
            e.printStackTrace();
        }
        if(findUser.isPresent()){
            if(logger.isInfoEnabled()){
                logger.info("User " + findUser.get().login() + " exist!");
            }
            return new UserRegisterDTO(findUser.get().id(),
                    findUser.get().login(),
                    findUser.get().password(),
                    findUser.get().firstName(),
                    findUser.get().lastName());
        }
        else {
            logger.error("User with " + inputCommand.id() + " doesn't exist");
            throw new InvalidHandleCommandException("UserId doesn't exist " + inputCommand.id());
        }
    }
}
