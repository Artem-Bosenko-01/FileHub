package io.javaclasses.fileHub.services.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.DuplicatedIdException;
import io.javaclasses.fileHub.persistent.files.Folder;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.users.User;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.services.OpenUserProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Service for first user registration in Filehub application.
 */
@Component
public class RegisterUser implements OpenUserProcess<RegistrationUserCommand, Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(RegisterUser.class);

    private final UserStorage userStorage;

    private final FolderStorage folderStorage;

    @Autowired
    public RegisterUser(@Qualifier("userStorageInDatabase") UserStorage userStorage,
                        @Qualifier("folderStorageInDatabase") FolderStorage folderStorage) {

        this.userStorage = Preconditions.checkNotNull(userStorage);
        this.folderStorage = Preconditions.checkNotNull(folderStorage);
    }

    @Override
    public Boolean handle(RegistrationUserCommand inputCommand) throws DuplicatedFieldValueException {

        if (logger.isInfoEnabled()) {
            logger.info("Start registration user process with id: " + inputCommand.loginName());
        }

        UserId id = new UserId(inputCommand.loginName());

        String password = PasswordEncoder.encode(inputCommand.password());

        User user = new User(id);
        user.setLogin(inputCommand.loginName());
        user.setPassword(password);

        try {

            userStorage.create(user);

            Folder folder = new Folder("Root" + user.id());
            folder.setItemsAmount(0L);
            folder.setName("Root");
            folder.setOwner(user.id());
            folder.setParentFolder(null);

            folderStorage.create(folder);

            if (logger.isInfoEnabled()) {
                logger.info("Registration was successful :" + user.id());
            }

            return true;

        } catch (DuplicatedIdException e) {

            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }

            throw new DuplicatedFieldValueException("email", e.getMessage());
        }
    }
}
