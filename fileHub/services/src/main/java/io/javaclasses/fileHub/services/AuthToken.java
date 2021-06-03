package io.javaclasses.fileHub.services;


import com.google.common.base.Preconditions;

/**
 * This is key, that authenticated user in application and gives him some permissions.
 * This token may uses in services.
 * */
public final class AuthToken {

    private final String value;

    public AuthToken(String value) {
        this.value = Preconditions.checkNotNull(value);
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
