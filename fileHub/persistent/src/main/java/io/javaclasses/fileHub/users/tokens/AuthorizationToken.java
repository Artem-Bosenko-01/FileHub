package io.javaclasses.fileHub.users.tokens;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.users.UserID;

import java.util.Date;
import java.util.Objects;

public final class AuthorizationToken {

    private final AuthToken token;
    private final UserID userID;
    private Date expirationTime;

    public AuthorizationToken(AuthToken token, UserID userID) {
        this.token = Preconditions.checkNotNull(token);
        this.userID = Preconditions.checkNotNull(userID);
    }

    public AuthorizationToken(AuthToken token, UserID userID, Date expirationTime) {
        this.token = Preconditions.checkNotNull(token);
        this.userID = Preconditions.checkNotNull(userID);
        this.expirationTime = Preconditions.checkNotNull(expirationTime);
    }

    public void setDate(Date expirationTime) {
        this.expirationTime = Preconditions.checkNotNull(expirationTime);
    }

    public AuthToken token() {
        return token;
    }

    public UserID userID() {
        return userID;
    }

    public Date date() {
        return expirationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorizationToken that = (AuthorizationToken) o;
        return token.equals(that.token) && userID.equals(that.userID) && Objects.equals(expirationTime, that.expirationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, userID, expirationTime);
    }
}
