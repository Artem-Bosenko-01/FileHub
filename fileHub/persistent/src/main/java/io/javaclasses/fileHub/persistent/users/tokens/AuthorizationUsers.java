package io.javaclasses.fileHub.persistent.users.tokens;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.DataRecord;
import io.javaclasses.fileHub.persistent.users.UserId;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * This is entity for storing user authentication history.
 * <p>
 * Lines unique defines by {@link UserAuthToken user token}.
 */
public final class AuthorizationUsers implements DataRecord<UserAuthToken> {

    private final UserAuthToken token;
    private final UserId userID;
    private LocalDateTime expirationTime;

    public AuthorizationUsers(UserAuthToken token, UserId userID, LocalDateTime expirationTime) {
        this.token = Preconditions.checkNotNull(token);
        this.userID = Preconditions.checkNotNull(userID);
        this.expirationTime = Preconditions.checkNotNull(expirationTime);
    }

    public UserId userID() {
        return userID;
    }

    public LocalDateTime expirationTime() {
        return expirationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorizationUsers that = (AuthorizationUsers) o;
        return token.equals(that.token) && userID.equals(that.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, userID);
    }

    @Override
    public UserAuthToken id() {
        return token;
    }
}
