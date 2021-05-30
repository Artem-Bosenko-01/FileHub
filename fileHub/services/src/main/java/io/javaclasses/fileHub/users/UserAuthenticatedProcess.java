package io.javaclasses.fileHub.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.OpenProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.UUID;

/**
 * This is service for authentication user in Filehub application if he is been in {@link UserStorage user storage}.
 */
public class UserAuthenticatedProcess implements OpenProcess<UserAuthenticatedCommand, UserAuthenticatedDTO> {

    private final UserStorage userStorage;
    private final Logger logger = LoggerFactory.getLogger(UserAuthenticatedProcess.class);

    public UserAuthenticatedProcess(UserStorage userStorage){
        this.userStorage = Preconditions.checkNotNull(userStorage);
    }

    @Override
    public UserAuthenticatedDTO handle(UserAuthenticatedCommand inputCommand) throws InvalidHandleCommandException {

        if(logger.isInfoEnabled()){
            logger.info("Start authenticated process for user: " + inputCommand.loginName());
        }

        String password = PasswordEncoder.encode(inputCommand.password());
        Optional<User> user = userStorage.findByLoginAndPassword(inputCommand.loginName(),password);
        if(user.isPresent()){

            AuthToken token =new AuthToken(UUID.randomUUID().toString());

            if(logger.isInfoEnabled()){
                logger.info("Authenticate process was successful. User: " + user.get().login() + " have token: " + token);
            }
            return new UserAuthenticatedDTO(token, user.get().id());
        }
        else {
            if(logger.isErrorEnabled()){
                logger.error("User with " + inputCommand.loginName() + " not exist");
            }
            throw new InvalidHandleCommandException("User with " + inputCommand.loginName() + " not exist");
        }
    }
}
