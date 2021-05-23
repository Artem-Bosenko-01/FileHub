package io.javaclasses.fileHub.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.OpenProcess;

import java.util.Optional;

public class UserAuthenticatedProcess implements OpenProcess<UserAuthenticatedCommand, UserAuthenticatedDTO> {

    private final UserStorageInMemory userStorage;

    public UserAuthenticatedProcess(UserStorageInMemory userStorage){
        this.userStorage = Preconditions.checkNotNull(userStorage);
    }

    @Override
    public UserAuthenticatedDTO handle(UserAuthenticatedCommand inputCommand) throws InvalidHandleCommandException {
        String password = PasswordEncoder.encode(inputCommand.password());
        Optional<User> user = userStorage.findByLoginAndPassword(inputCommand.loginName(),password);
        if(user.isPresent()){
            return new UserAuthenticatedDTO(user.get().login(),user.get().password());
        }
        else throw new InvalidHandleCommandException("User with " + inputCommand.loginName() + " not exist");
    }
}
