package com.java_server.request.methods;

import com.java_server.request.Request;
import com.java_server.response.Response;
import com.java_server.response.ResponseCodes;
import java.io.IOException;

/**
 * Created by Alex Codreanu on 11/21/14.
 */
public class OPTIONS extends RequestMethod {
    private Request request;
    private final String okCode = "200";

    public OPTIONS(Request inRequest) {
        this.request = inRequest;
    }
    public Response getResponse() throws IOException {
        return  new Response(okCode, ResponseCodes.getReasonPhrase(okCode));
    }
}
