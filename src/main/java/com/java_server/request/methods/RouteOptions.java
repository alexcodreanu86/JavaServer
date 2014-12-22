package com.java_server.request.methods;

import com.java_server.response.Response;
import com.java_server.response.ResponseFactory;

import java.io.IOException;

/**
 * Created by Alex Codreanu on 11/21/14.
 */
public class RouteOptions extends RequestMethod {
    public Response getResponse() throws IOException {
        return ResponseFactory.OK();
    }
}
