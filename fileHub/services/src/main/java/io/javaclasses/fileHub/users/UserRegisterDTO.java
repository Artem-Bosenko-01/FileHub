package io.javaclasses.fileHub.users;

import java.util.Objects;

/**
 *
 * */
public final class UserRegisterDTO {
    private final UserID id;
    private final String loginName;
    private final String Password;
    private final String firstName;
    private final String lastName;

    public UserRegisterDTO(UserID id, String loginName, String password, String firstName, String lastName) {
        this.id = id;
        this.loginName = loginName;
        Password = password;
        this.firstName = firstName;
        this.lastName = lastName;
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
        UserRegisterDTO userDTO = (UserRegisterDTO) o;
        return id.equals(userDTO.id) && loginName.equals(userDTO.loginName) && Password.equals(userDTO.Password) && firstName.equals(userDTO.firstName) && lastName.equals(userDTO.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loginName, Password, firstName, lastName);
    }
}
