package com.java_server.routing.methods;

import com.java_server.request.Request;
import com.java_server.response.Response;
import com.java_server.response.ResponseCodes;

/**
 * Created by Alex Codreanu on 11/21/14.
 */
public class NOTFOUND extends RouteMethod {
    private String notFoundCode = "404";
    private Request request;
    public NOTFOUND(Request request) {
        this.request = request;
    }
   public Response getResponse() {
       return new Response(notFoundCode, ResponseCodes.getReasonPhrase(notFoundCode));
   }
}
