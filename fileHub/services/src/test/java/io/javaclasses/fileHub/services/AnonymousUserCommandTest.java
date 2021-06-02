package io.javaclasses.fileHub.services;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.services.AnonymousUserCommand;
import org.junit.jupiter.api.Test;

class AnonymousUserCommandTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();
        tester.testConstructor(AnonymousUserCommand.class.getConstructor());

    }
}