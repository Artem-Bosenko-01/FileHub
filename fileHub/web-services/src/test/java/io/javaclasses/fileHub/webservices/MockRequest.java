package io.javaclasses.fileHub.webservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Simulation real server behavior. Opens {@link HttpURLConnection url connection} by necessary path to local host.
 *
 * <code>
 * Example of url: "http://localhost:4567/FileHub/server/api/1.0/login"
 * </code>
 *
 * <p>
 * At opened connection sets request method and writes request body like JSON string
 * to {@link OutputStream connection output stream} in binary format.
 *
 * <code>
 * Example request body at JSON format: {"loginName": "artrms@kasc.com","password": "sdvdds"}
 * </code>
 *
 * <p>
 * As a result of sending request to existed {@link URL url}, creates {@link TestResponse response} with status code and body.
 * Response body can be got from one of two connection streams: an error stream (if response status code >= 400)
 * or an input stream that reads from this opened connection if response status code == 200.
 */
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
        StringBuilder body = new StringBuilder(10);

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

        connection.disconnect();

        return new TestResponse(responseStatus, body.toString());

    }
}
