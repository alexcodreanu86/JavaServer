package com.java_server.request.methods;

import com.java_server.request.Request;
import com.java_server.response.Response;
import com.java_server.response.ResponseFactory;
import com.java_server.response.handlers.MovedPermanentlyHandler;
import com.java_server.response.handlers.OKHandler;
import com.java_server.response.handlers.PartialContentHandler;
import com.java_server.routing.Route;
import com.java_server.routing.RoutesDispatcher;

import java.io.IOException;

/**
 * Created by Alex Codreanu on 11/20/14.
 */
public class GET extends RequestMethod {
    private Request request;

    public GET(Request inRequest) {
        this.request = inRequest;
    }

    public Response getResponse() throws IOException {
        Route route = RoutesDispatcher.getRoute(request.getUrl());
        if (route.getUrl().equals("/redirect")) {
            return createRedirectResponse();
        } else if (request.getHeaders().get("Range") != null){
            return createPartialResponse(request.getHeaders().get("Range"), route);
        } else {
            return createSuccessResponse(route);
        }
    }

    private Response createPartialResponse(String range, Route route) {
        Response response = ResponseFactory.PartialContent();
        new PartialContentHandler(request, response, route).populateResponse();

        return response;
    }

    private Response createRedirectResponse() {
        Response response = ResponseFactory.MovedPermanently();
        new MovedPermanentlyHandler(response).populateResponse();

        return response;
    }

    private Response createSuccessResponse(Route route) throws IOException {
        Response response = ResponseFactory.OK();
        new OKHandler(request, response, route).populateResponse();
        return response;
    }
}
