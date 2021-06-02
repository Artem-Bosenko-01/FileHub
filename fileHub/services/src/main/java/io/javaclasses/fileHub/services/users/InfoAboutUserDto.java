package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.users.UserId;

import static com.google.common.base.Preconditions.*;

/**
 *
 * */
public final class InfoAboutUserDto {

    private final UserId id;
    private final String loginName;
    private final String password;
    private final String firstName;
    private final String lastName;

    public InfoAboutUserDto(UserId id, String loginName, String password, String firstName, String lastName) {

        this.id = checkNotNull(id);
        this.loginName = checkNotNull(loginName);
        this.password = checkNotNull(password);
        this.firstName = checkNotNull(firstName);
        this.lastName = checkNotNull(lastName);

    }

    public UserId id() {
        return id;
    }

    public String loginName() {
        return loginName;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public String password() {
        return password;
    }
}
