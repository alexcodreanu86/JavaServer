package com.java_server.request.methods;


import com.java_server.response.Response;

import java.io.IOException;

/**
 * Created by Alex Codreanu on 11/21/14.
 */
public abstract class RequestMethod {
    public abstract Response getResponse() throws IOException;
}
