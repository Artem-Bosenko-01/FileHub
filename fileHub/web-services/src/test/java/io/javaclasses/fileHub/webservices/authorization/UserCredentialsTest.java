package io.javaclasses.fileHub.webservices.authorization;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.services.users.AuthenticationUserCommand;
import io.javaclasses.fileHub.webservices.authentication.UserCredentials;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserCredentialsTest {
    @Test
    public void checkForNullPointerInConstructor() {

        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicConstructors(UserCredentials.class);

    }

    @Test
    public void shouldDeserializeInputStringToCommand() {

        String loginName = "login";
        String password = "password";
        String inputJsonLine = "{\nloginName:" + loginName + ",\npassword:" + password + "\n}";
        UserCredentials userCredentials = new UserCredentials(inputJsonLine);

        AuthenticationUserCommand command = userCredentials.deserialize();

        Assertions.assertEquals(command.loginName(), loginName);
        Assertions.assertEquals(command.password(), password);

    }
}