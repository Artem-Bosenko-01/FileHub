package io.javaclasses.fileHub.folders;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.users.UserID;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetFolderByNameQueryTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(GetFolderByNameQuery.class.getConstructor(AuthToken.class, String.class,
                    UserID.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkForNullPointerInSetters(){
        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(GetFolderByNameQuery.class.getMethods());
    }
}