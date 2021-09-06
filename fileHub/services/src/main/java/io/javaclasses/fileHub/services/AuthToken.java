package io.javaclasses.fileHub.services;


import com.google.common.base.Preconditions;

/**
 * It is the key that identifies authenticated users in the application and gives them some permissions.
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
