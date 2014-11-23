package com.java_server.response;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Alex Codreanu on 11/20/14.
 */
public class ResponseSender {
    private DataOutputStream outputStream;
    private Response response;
    public ResponseSender(Response resp, DataOutputStream outStream) {
        this.outputStream = outStream;
        this.response = resp;
    }

    public void send() throws IOException{
        this.outputStream.write(response.render());
    }
}
