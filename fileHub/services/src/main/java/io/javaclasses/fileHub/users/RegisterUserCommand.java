package io.javaclasses.fileHub.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.AnonymousUserCommand;

import java.util.Objects;

/**
 *  This is object, that contains data, that was inputted by user in the process of registration.
 *
 * */
public final class RegisterUserCommand extends AnonymousUserCommand {


    private final String loginName;
    private final String firstName;
    private final String lastName;
    private final String password;

    public RegisterUserCommand(String loginName, String firstName, String lastName, String password) {
        this.loginName = Preconditions.checkNotNull(loginName);
        this.firstName = Preconditions.checkNotNull(firstName);
        this.lastName = Preconditions.checkNotNull(lastName);
        this.password = Preconditions.checkNotNull(password);
    }

    public String loginName() {
        return loginName;
    }

    public String password() {
        return password;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {

        Preconditions.checkNotNull(o);
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;
        RegisterUserCommand that = (RegisterUserCommand) o;
        return loginName.equals(that.loginName) && firstName.equals(that.firstName) && lastName.equals(that.lastName) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginName, firstName, lastName, password);
    }
}
