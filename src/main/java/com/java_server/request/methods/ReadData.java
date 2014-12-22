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
public class ReadData extends RequestMethod {
    private Request request;

    public ReadData(Request inRequest) { this.request = inRequest; }

    public Response getResponse() throws IOException {
        Route route = RoutesDispatcher.getRoute(request.getUrl());
        if (route.shouldRedirect()) {
            return createRedirectResponse(route.getRedirectPath());
        } else if (request.isPartial()){
            return createPartialResponse(request.getHeader("Range"), route);
        } else {
            return createSuccessResponse(route);
        }
    }

    private Response createPartialResponse(String range, Route route) {
        Response response = ResponseFactory.PartialContent();
        new PartialContentHandler(range, response, route).populateResponse();

        return response;
    }

    private Response createRedirectResponse(String redirectPath) {
        Response response = ResponseFactory.MovedPermanently();
        new MovedPermanentlyHandler(response, redirectPath).populateResponse();

        return response;
    }

    private Response createSuccessResponse(Route route) throws IOException {
        Response response = ResponseFactory.OK();
        new OKHandler(request, response, route).populateResponse();
        return response;
    }
}