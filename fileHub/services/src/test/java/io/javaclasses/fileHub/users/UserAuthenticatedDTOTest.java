package io.javaclasses.fileHub.users;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import org.junit.jupiter.api.Test;

class UserAuthenticatedDTOTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(UserAuthenticatedDTO.class.getConstructor(AuthToken.class, UserID.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}