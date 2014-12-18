package com.java_server.response;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Alex Codreanu on 11/20/14.
 */
public class ResponseSender {
    private OutputStream outputStream;
    private Response response;
    public ResponseSender(Response resp, OutputStream outStream) {
        this.outputStream = outStream;
        this.response = resp;
    }

    public void send() throws IOException{
        this.outputStream.write(response.render());
    }
}
