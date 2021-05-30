package io.javaclasses.fileHub.users.tokens;


import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.RecordID;

/**
 * This is key, that authenticated user in application and gives him some permissions.
 * This token may uses in services.
 * */
public final class AuthToken  implements RecordID {

    private final String value;
    public AuthToken(String value) {
        this.value = Preconditions.checkNotNull(value);
    }

    public AuthToken(){value = "";}

    public String value() {
        return value;
    }
}
