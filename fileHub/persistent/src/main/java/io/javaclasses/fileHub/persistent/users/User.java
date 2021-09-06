package io.javaclasses.fileHub.persistent.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.DataRecord;

import java.util.Objects;

public final class User implements DataRecord<UserId> {

    private final UserId userID;
    private String login;
    private String password;

    public User(UserId userID) {
        this.userID = Preconditions.checkNotNull(userID);
    }

    @Override
    public UserId id() {
        return userID;
    }

    public String login() {
        return login;
    }

    public void setLogin(String login) {
        this.login = Preconditions.checkNotNull(login);
    }

    public String password() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Preconditions.checkNotNull(password);
    }

    @Override
    public boolean equals(Object o) {

        Preconditions.checkNotNull(o);

        if (this == o) return true;

        if (getClass() != o.getClass()) return false;

        User user = (User) o;

        return userID.equals(user.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID);
    }
}
