package io.javaclasses.fileHub.webservices;

public enum RESPONSE_STATUS {

    OK(200),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    VALIDATION_ERROR(422),
    SERVER_ERROR(500);

    private final Integer code;

    RESPONSE_STATUS(int statusCode) {

        this.code = statusCode;
    }

    public Integer code() {

        return code;
    }
}
