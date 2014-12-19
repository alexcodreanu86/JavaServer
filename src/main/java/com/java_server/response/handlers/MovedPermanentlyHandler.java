package com.java_server.response.handlers;

import com.java_server.args.GlobalArguments;
import com.java_server.response.Response;

/**
 * Created by Alex Codreanu on 11/27/14.
 */
public class MovedPermanentlyHandler {
    Response response;
    String redirectPath;
    public MovedPermanentlyHandler(Response inResponse, String inRedirectPath) {
        this.response = inResponse;
        this.redirectPath = inRedirectPath;
    }

    public void populateResponse() {
        response.addHeader("Location", "http://localhost:" + GlobalArguments.getPort() + redirectPath);
    }
}
