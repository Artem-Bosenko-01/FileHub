package io.javaclasses.fileHub.users;

import com.google.common.base.Preconditions;

import java.util.Objects;

/**
 *  This is object, that contains full data information about user in Filehub application.
 * */
public final class UserDTO {
    private final UserID id;
    private final String loginName;
    private final String password;
    private final String firstName;
    private final String lastName;

    public UserDTO(UserID id, String loginName, String password, String firstName, String lastName) {
        this.id = Preconditions.checkNotNull(id);
        this.loginName = Preconditions.checkNotNull(loginName);
        this.password = Preconditions.checkNotNull(password);
        this.firstName = Preconditions.checkNotNull(firstName);
        this.lastName = Preconditions.checkNotNull(lastName);
    }

    public UserID id() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id.equals(userDTO.id) && loginName.equals(userDTO.loginName) && password.equals(userDTO.password) && firstName.equals(userDTO.firstName) && lastName.equals(userDTO.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loginName, password, firstName, lastName);
    }
}
