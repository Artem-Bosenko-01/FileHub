package io.javaclasses.fileHub;

import com.google.common.testing.NullPointerTester;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnonymousUserCommandTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(AnonymousUserCommand.class.getConstructor());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}