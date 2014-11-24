package com.java_server.routing.methods;

import com.java_server.request.Request;
import com.java_server.response.Response;
import com.java_server.response.ResponseCodes;
import com.java_server.response.ResponseFactory;
import com.java_server.routing.Route;
import com.java_server.routing.RoutesDispatcher;

import java.io.IOException;

/**
 * Created by Alex Codreanu on 11/20/14.
 */
public class POST extends RouteMethod {
    private Request request;
    public POST(Request inRequest) {
        this.request = inRequest;
    }

    public Response getResponse() throws IOException{
        return createSuccessResponseWithData(request.getBody());
    }

    private Response createSuccessResponseWithData(String data) throws IOException {
        Response response = ResponseFactory.OK();
        Route route = RoutesDispatcher.getRoute(request.getUrl());
        route.setData(data.getBytes());
        return response;
    }

}
