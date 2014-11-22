package com.java_server.routing;

import com.java_server.Request.Request;
import com.java_server.response.Response;
import com.java_server.response.ResponseCodes;
import com.java_server.response.ResponseSender;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Alex Codreanu on 11/21/14.
 */
public class NOTFOUND extends RouteMethod {
    private String notFoundCode = "404";
    private DataOutputStream outputStream;

    public NOTFOUND(Request request, DataOutputStream output) {
        this.outputStream = output;
    }
   public void processRequest() {
       Response response = new Response(notFoundCode, ResponseCodes.getReasonPhrase(notFoundCode));
       try {
           new ResponseSender(response, outputStream).send();
       }
       catch (IOException e) {
          e.printStackTrace();
       }
   }
}
