package io.javaclasses.fileHub;


import com.google.common.base.Preconditions;

/**
 * This is key, that gives to object some permissions.
 * */
public final class AuthToken {

    private final String value;
    public AuthToken(String value) {
        this.value = Preconditions.checkNotNull(value);
    }

    public String value() {
        return value;
    }
}
