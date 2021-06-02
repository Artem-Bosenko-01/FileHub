package io.javaclasses.fileHub.services;


import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.RecordId;

/**
 * This is key, that authenticated user in application and gives him some permissions.
 * This token may uses in services.
 * */
public final class AuthToken  implements RecordId {

    private final String value;

    public AuthToken(String value) {
        this.value = Preconditions.checkNotNull(value);
    }

    public AuthToken(){value = "";}

    public String value() {
        return value;
    }
}
