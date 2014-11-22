package com.java_server.routing;

import com.java_server.Request.Request;
import com.java_server.response.Response;
import com.java_server.response.ResponseCodes;
import com.java_server.response.ResponseSender;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Alex Codreanu on 11/20/14.
 */
public class POST {
    private Request request;
    private final String okCode = "200";
    private final String notFound = "404";
    public POST(Request inRequest) {
        this.request = inRequest;
    }

    public Response getResponse() throws IOException{
        if (request.getUrl().equals("/form")) {
            return createSuccessResponseWithData(request.getBody());
        } else {
            return createFailResponse();
        }
    }

    private Response createSuccessResponseWithData(String data) throws IOException {
        Response response = new Response(okCode, ResponseCodes.getReasonPhrase(okCode));
        response.addToBody(data);
        return response;
    }

    private Response createFailResponse() throws IOException {
        return new Response(notFound, ResponseCodes.getReasonPhrase(notFound));
    }
}
