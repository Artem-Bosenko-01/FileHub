package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.services.AnonymousUserCommand;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This is object, that contains data, that was inputted by user in the process of registration.
 */
public final class RegistrationUserCommand extends AnonymousUserCommand {


    private final String loginName;
    private final String firstName;
    private final String lastName;
    private final String password;

    public RegistrationUserCommand(String loginName, String firstName, String lastName, String password) {

        this.loginName = checkNotNull(loginName);
        this.firstName = checkNotNull(firstName);
        this.lastName = checkNotNull(lastName);
        this.password = checkNotNull(password);

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

        checkNotNull(o);
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;
        RegistrationUserCommand that = (RegistrationUserCommand) o;
        return loginName.equals(that.loginName) &&
                firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginName, firstName, lastName, password);
    }
}
