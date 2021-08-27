package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.users.UserId;

import static com.google.common.base.Preconditions.*;


public final class InfoAboutUserDto {

    private final UserId id;
    private final String loginName;
    private final String password;

    public InfoAboutUserDto(UserId id, String loginName, String password) {

        this.id = checkNotNull(id);
        this.loginName = checkNotNull(loginName);
        this.password = checkNotNull(password);

    }

    public UserId id() {
        return id;
    }

    public String loginName() {
        return loginName;
    }

    public String password() {
        return password;
    }
}
