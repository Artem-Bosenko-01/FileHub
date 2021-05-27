package io.javaclasses.fileHub.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.OpenProcess;

import java.util.Optional;
import java.util.UUID;

public class UserAuthenticatedProcess implements OpenProcess<UserAuthenticatedCommand, UserAuthenticatedDTO> {

    private final UserStorage userStorage;

    public UserAuthenticatedProcess(UserStorage userStorage){
        this.userStorage = Preconditions.checkNotNull(userStorage);
    }

    @Override
    public UserAuthenticatedDTO handle(UserAuthenticatedCommand inputCommand) throws InvalidHandleCommandException {
        String password = PasswordEncoder.encode(inputCommand.password());
        Optional<User> user = userStorage.findByLoginAndPassword(inputCommand.loginName(),password);
        if(user.isPresent()){
            return new UserAuthenticatedDTO(new AuthToken(UUID.randomUUID().toString()), user.get().id());
        }
        else throw new InvalidHandleCommandException("User with " + inputCommand.loginName() + " not exist");
    }
}
