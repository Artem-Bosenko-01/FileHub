package io.javaclasses.fileHub.webservices.user;

import io.javaclasses.fileHub.webservices.JsonResponse;

public class GetUserSuccessfulResponse extends JsonResponse {

    private final String id;
    private final String loginName;

    public GetUserSuccessfulResponse(String id, String loginName) {
        this.id = id;
        this.loginName = loginName;
    }
}
