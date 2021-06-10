package io.javaclasses.fileHub.persistent.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.DataRecord;

import java.util.Objects;

public final class User implements DataRecord<UserId> {

    private final UserId userID;
    private String login;
    private String password;
    private String firstName;
    private String lastName;

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

    public String firstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = Preconditions.checkNotNull(firstName);
    }

    public String lastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = Preconditions.checkNotNull(lastName);
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
