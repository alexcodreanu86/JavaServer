package com.java_server.request.methods;

import com.java_server.response.Response;
import com.java_server.response.ResponseFactory;

/**
 * Created by Alex Codreanu on 11/21/14.
 */
public class NotAllowed extends RequestMethod {
    public Response getResponse() { return ResponseFactory.MethodNotAllowed(); }
}
