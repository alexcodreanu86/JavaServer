package com.java_server.routing.methods;

import com.java_server.request.Request;
import com.java_server.response.Response;
import com.java_server.response.ResponseFactory;
import com.java_server.routing.Route;
import com.java_server.routing.RoutesDispatcher;

import java.io.IOException;

/**
 * Created by Alex Codreanu on 11/20/14.
 */
public class GET extends RouteMethod {
    private Request request;

    public GET(Request inRequest) {
        this.request = inRequest;
    }

    public Response getResponse() throws IOException {
        Route route = RoutesDispatcher.getRoute(request.getUrl());
        byte[] responseBody = route.getData();

        return createSuccessResponse(responseBody);
    }

    private Response createSuccessResponse(byte[] responseBody) throws IOException {
        Response response = ResponseFactory.OK();
        response.addHeader("Content-Type", "text/html");
        response.addToBody(responseBody);

        return response;
    }
}
