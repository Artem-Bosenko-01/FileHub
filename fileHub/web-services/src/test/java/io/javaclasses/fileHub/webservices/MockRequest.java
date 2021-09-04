package io.javaclasses.fileHub.webservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public final class MockRequest {

    public TestResponse send(String method, String path, String jsonInputString) throws IOException {

        URL url = new URL("http://localhost:4567" + path);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod(method);
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int responseStatus = connection.getResponseCode();
        StringBuilder body = new StringBuilder();

        if (responseStatus >= 400) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    body.append(responseLine.trim());
                }
            }
        } else {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    body.append(responseLine.trim());
                }
            }
        }

        return new TestResponse(responseStatus, body.toString());

    }
}
