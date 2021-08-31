package io.javaclasses.fileHub.services;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationUsers;
import io.javaclasses.fileHub.persistent.users.tokens.UserAuthToken;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * This is an abstract base, that makes it possible to handle commands from the authenticated user.
 *
 * @param <E> result entity.
 * @param <C> one of {@link AuthenticatedUserCommand command} from client.
 * */
public abstract class SecuredUserProcess<C extends  AuthenticatedUserCommand, E> implements UserProcess<C,E> {

    private final AuthorizationStorage storage;

    protected SecuredUserProcess(AuthorizationStorage storage) {

        this.storage = Preconditions.checkNotNull(storage);
    }


    @Override
    public E handle(C inputCommand) throws InvalidHandleCommandException {

        if(verifyPermission(inputCommand)) {

            return doHandle(inputCommand);

        }
        else {

            throw new InvalidHandleCommandException(
                    "User with token: " + inputCommand.token() + " doesn't have any permission");
        }

    }

    protected abstract E doHandle(C inputCommand) throws InvalidHandleCommandException;

    private boolean verifyPermission(C command){

        Optional<AuthorizationUsers> token = storage.findByID(new UserAuthToken(command.token().value()));
        return token.filter(this::isTokenNotExpire).isPresent();

    }

    private boolean isTokenNotExpire(AuthorizationUsers token){

        return token.expirationTime().isAfter(ZonedDateTime.now(ZoneId.of("America/Los_Angeles")));

    }
}
