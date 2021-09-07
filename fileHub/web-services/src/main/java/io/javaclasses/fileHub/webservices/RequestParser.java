package io.javaclasses.fileHub.webservices;

import com.google.common.base.Preconditions;
import spark.Request;

/**
 * Util that allows getting necessary data for {@link spark.Route routes} from {@link Request requests}.
 */
public final class RequestParser {

    private final Request request;

    public RequestParser(Request request) {

        this.request = Preconditions.checkNotNull(request);
    }

    public String getToken() {

        return request.headers("Authorization").split(" ")[1];
    }

    public String getId() {

        return request.uri().split("/")[6];
    }

    public String body() {

        return request.body();
    }
}
