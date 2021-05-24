package io.javaclasses.fileHub.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 *
 *
 * */
public class ProfileReadManagementView implements View<ProfileReadQuery, UserRegisterDTO> {

    private final AbstractInMemoryStorage<UserID, User> userStorage;
    private final Logger logger = LoggerFactory.getLogger(ProfileReadManagementView.class);

    public ProfileReadManagementView(AbstractInMemoryStorage<UserID, User> userStorage){
        this.userStorage = Preconditions.checkNotNull(userStorage);
    }

    @Override
    public UserRegisterDTO handle(ProfileReadQuery query) throws InvalidHandleCommandException {

        if(logger.isInfoEnabled()){
            logger.info("Start read user process with id: " + query.id());
        }

        try {
            Optional<User> findUser = userStorage.findByID(query.id());
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
            else throw new InvalidHandleCommandException("UserId doesn't exist " + query.id());
        } catch (NotExistIDException e) {
            logger.error(e.getMessage());
            throw new InvalidHandleCommandException(e.getMessage());
        }
    }
}
