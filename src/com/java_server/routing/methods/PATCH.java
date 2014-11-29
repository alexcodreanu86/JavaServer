package com.java_server.routing.methods;

import com.java_server.request.Request;
import com.java_server.response.Response;
import com.java_server.response.ResponseFactory;
import com.java_server.routing.Route;
import com.java_server.routing.RoutesDispatcher;

import java.io.IOException;

/**
 * Created by Alex Codreanu on 11/26/14.
 */
public class PATCH extends RouteMethod {
    private Request request;

    public PATCH(Request inRequest) {
        this.request = inRequest;
    }

    public Response getResponse() throws IOException{
        return createSuccessResponseWithData(request.getBody());
    }

    private Response createSuccessResponseWithData(String data) throws IOException {
        Response response = ResponseFactory.NoContent();
        Route route = RoutesDispatcher.getRoute(request.getUrl());
        route.setData(data.getBytes());
        return response;
    }
}
