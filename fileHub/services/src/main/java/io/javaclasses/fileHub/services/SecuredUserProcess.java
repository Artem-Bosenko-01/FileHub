package io.javaclasses.fileHub.services;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationUsers;
import io.javaclasses.fileHub.persistent.users.tokens.UserAuthToken;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

/**
 * Abstract base that handles commands from the authenticated user.
 *
 * @param <E> result entity.
 * @param <C> one of {@link AuthenticatedUserCommand command} from client.
 */
public abstract class SecuredUserProcess<C extends AuthenticatedUserCommand, E> implements UserProcess<C, E> {

    private final AuthorizationStorage storage;

    protected SecuredUserProcess(AuthorizationStorage storage) {

        this.storage = Preconditions.checkNotNull(storage);
    }


    @Override
    public E handle(C inputCommand) throws NotAuthorizedUserException, InvalidCommandHandlingException {

        if (verifyPermission(inputCommand)) {

            return doHandle(inputCommand);

        } else {

            throw new NotAuthorizedUserException(
                    "User with token: " + inputCommand.token() + " doesn't have any permission");
        }

    }

    protected abstract E doHandle(C inputCommand) throws InvalidCommandHandlingException;

    private boolean verifyPermission(C command) {

        Optional<AuthorizationUsers> token = storage.findByID(new UserAuthToken(command.token().value()));
        return token.filter(SecuredUserProcess::isTokenNotExpire).isPresent();

    }

    private static boolean isTokenNotExpire(AuthorizationUsers token) {

        return token.expirationTime().isAfter(LocalDateTime.now(ZoneId.of("America/Los_Angeles")));

    }
}
