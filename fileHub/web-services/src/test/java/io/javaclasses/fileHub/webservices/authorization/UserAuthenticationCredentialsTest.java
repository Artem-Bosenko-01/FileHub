package io.javaclasses.fileHub.webservices.authorization;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.services.users.AuthenticationUserCommand;
import io.javaclasses.fileHub.webservices.authentication.UserAuthenticationCredentials;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserAuthenticationCredentialsTest {
    @Test
    public void checkForNullPointerInConstructor() {

        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicConstructors(UserAuthenticationCredentials.class);

    }

    @Test
    public void shouldDeserializeInputStringToCommand() {

        String loginName = "login";
        String password = "password";
        String inputJsonLine = "{\nloginName:" + loginName + ",\npassword:" + password + "\n}";
        UserAuthenticationCredentials userAuthenticationCredentials = new UserAuthenticationCredentials(inputJsonLine);

        AuthenticationUserCommand command = userAuthenticationCredentials.deserialize();

        Assertions.assertEquals(command.loginName(), loginName);
        Assertions.assertEquals(command.password(), password);

    }
}