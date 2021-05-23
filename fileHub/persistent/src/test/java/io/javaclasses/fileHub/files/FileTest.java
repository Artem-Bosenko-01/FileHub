package io.javaclasses.fileHub.files;

import com.google.common.testing.NullPointerTester;
import org.junit.jupiter.api.Test;

class FileTest {

    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(File.class.getConstructor(FileID.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkForNullPointerInSetters(){
        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(File.class);
    }
}