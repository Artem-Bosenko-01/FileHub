package io.javaclasses.fileHub.webservices;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JsonResponseTest {

    @Test
    public void shouldConvertMockClassToJsonFormat() {

        String name = "user";
        String password = "secret";
        String expectedLine = "{\"name\":\"" + name + "\",\"password\":\"" + password + "\"}";

        ResponseMockTest mockTest = new ResponseMockTest(name, password);
        String jsonLine = mockTest.serialize();

        Assertions.assertEquals(jsonLine, expectedLine);
    }

    class ResponseMockTest extends JsonResponse{
        private final String name;
        private final String password;

        ResponseMockTest(String name, String password) {
            this.name = name;
            this.password = password;
        }
    }
}