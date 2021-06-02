/*
package io.javaclasses.fileHub.persistent.users.tokens;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.DataRecord;
import io.javaclasses.fileHub.persistent.users.UserId;

import java.util.Date;
import java.util.Objects;

public final class AuthorizationUsers implements DataRecord<AuthToken> {

    private final AuthToken token;
    private final UserId userID;
    private Date expirationTime;

    public AuthorizationUsers(AuthToken token, UserId userID) {
        this.token = Preconditions.checkNotNull(token);
        this.userID = Preconditions.checkNotNull(userID);
    }

    public AuthorizationUsers(AuthToken token, UserId userID, Date expirationTime) {
        this.token = Preconditions.checkNotNull(token);
        this.userID = Preconditions.checkNotNull(userID);
        this.expirationTime = Preconditions.checkNotNull(expirationTime);
    }

    public void setDate(Date expirationTime) {
        this.expirationTime = Preconditions.checkNotNull(expirationTime);
    }

    public UserId userID() {
        return userID;
    }

    public Date date() {
        return expirationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorizationUsers that = (AuthorizationUsers) o;
        return token.equals(that.token) && userID.equals(that.userID) && Objects.equals(expirationTime, that.expirationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, userID, expirationTime);
    }

    @Override
    public AuthToken id() {
        return token;
    }
}
*/
