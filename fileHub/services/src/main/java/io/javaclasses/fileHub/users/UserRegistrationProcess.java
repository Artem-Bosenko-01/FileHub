package io.javaclasses.fileHub.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.AbstractInMemoryStorage;
import io.javaclasses.fileHub.DuplicatedIDException;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.OpenProcess;

/**
 *
 * */
public class UserRegistrationProcess implements OpenProcess<RegisterUserCommand,UserRegisterDTO> {

    private final UserStorage userStorage;

    public UserRegistrationProcess(UserStorage userStorage){
        this.userStorage = Preconditions.checkNotNull(userStorage);
    }

    @Override
    public UserRegisterDTO handle(RegisterUserCommand inputCommand) throws InvalidHandleCommandException {

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
        } catch (DuplicatedIDException e) {
            throw new InvalidHandleCommandException("");
        }
        return new UserRegisterDTO(id,login,password,firstName,lastName);
    }

}
