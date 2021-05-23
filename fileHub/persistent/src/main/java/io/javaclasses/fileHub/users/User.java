package io.javaclasses.fileHub.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.DataRecord;

import java.util.Objects;

public final class User implements DataRecord<UserID> {

    private final UserID userID;
    private String login;
    private String password;
    private String firstName;
    private String lastName;

    public User(UserID userID) {
        this.userID = Preconditions.checkNotNull(userID);
    }

    @Override
    public UserID id() {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userID.equals(user.userID) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, login, password, firstName, lastName);
    }
}
