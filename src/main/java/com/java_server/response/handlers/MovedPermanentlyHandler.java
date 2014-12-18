package com.java_server.response.handlers;

import com.java_server.args.GlobalArguments;
import com.java_server.response.Response;

/**
 * Created by Alex Codreanu on 11/27/14.
 */
public class MovedPermanentlyHandler {
    Response response;
    public MovedPermanentlyHandler(Response inResponse) {
        this.response = inResponse;
    }

    public void populateResponse() {
        response.addHeader("Location", "http://localhost:" + GlobalArguments.getPort() + "/");
    }
}
