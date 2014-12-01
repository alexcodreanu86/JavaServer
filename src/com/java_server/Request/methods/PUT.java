package com.java_server.request.methods;

import com.java_server.request.Request;
import com.java_server.response.Response;
import com.java_server.response.ResponseFactory;
import com.java_server.routing.Route;
import com.java_server.routing.RoutesDispatcher;

import java.io.IOException;

/**
 * Created by Alex Codreanu on 11/23/14.
 */
public class PUT extends RequestMethod {
    private Request request;

    public PUT(Request inRequest) {
        this.request = inRequest;
    }

    public Response getResponse() throws IOException {
        return createSuccessResponseWithData(request.getBody());
    }

    private Response createSuccessResponseWithData(String data) throws IOException {
        Response response = ResponseFactory.OK();
        Route route = RoutesDispatcher.getRoute(request.getUrl());
        route.setData(data.getBytes());
        return response;
    }
}
