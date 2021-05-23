package io.javaclasses.fileHub.users;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public final class PasswordEncoder {

    public static String encode(String password){
        HashFunction function = Hashing.sha256();
        HashCode code = function.hashBytes(password.getBytes(StandardCharsets.UTF_8));
        return code.toString();
    }

}
