package io.javaclasses.fileHub.services.users;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

/**
 * Util to encode input password in hashed string.
 */
final class PasswordEncoder {

    private PasswordEncoder() {
    }

    public static String encode(String password) {

        HashFunction function = Hashing.sha256();
        HashCode code = function.hashBytes(password.getBytes(StandardCharsets.UTF_8));
        return code.toString();

    }

}
