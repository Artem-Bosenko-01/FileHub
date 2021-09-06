package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.users.UserId;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * User's data after successful execution {@link GetUserInfo get user info process}.
 */
public final class InfoAboutUserDto {

    private final UserId id;

    private final String loginName;

    public InfoAboutUserDto(UserId id, String loginName) {

        this.id = checkNotNull(id);

        this.loginName = checkNotNull(loginName);

    }

    public UserId id() {

        return id;
    }

    public String loginName() {

        return loginName;
    }

}
