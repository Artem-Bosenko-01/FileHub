package io.javaclasses.fileHub.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.AbstractInMemoryStorage;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.OpenProcess;

import java.util.Optional;

public class UserAuthenticatedProcess implements OpenProcess<UserAuthenticatedCommand, UserAuthenticatedDTO> {

    private final AbstractInMemoryStorage<UserID, User> userStorage;

    public UserAuthenticatedProcess(AbstractInMemoryStorage<UserID, User> userStorage){
        this.userStorage = Preconditions.checkNotNull(userStorage);
    }

    @Override
    public UserAuthenticatedDTO handle(UserAuthenticatedCommand inputCommand) throws InvalidHandleCommandException {
        String password = PasswordEncoder.encode(inputCommand.password());
        Optional<User> findUser = userStorage.records().values().stream().
                filter(user -> user.login().equals(inputCommand.loginName())&&user.password().equals(password)).findFirst();
        return findUser.map(user -> new UserAuthenticatedDTO(user.login(), user.password())).orElse(null);
    }
}
