package com.java_server.response;

import com.java_server.utils.ArrayJoiner;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by Alex Codreanu on 11/19/14.
 */
public class Response {
    String HttpVersion = "HTTP/1.1";
    String lineDivider = "\r\n";
    String headerColon = ": ";

    private byte[] body;

    private String code, reasonPhrase;
    private Hashtable<String, String> headers;
    public Response(String inCode, String phrase){
        this.code = inCode;
        this.reasonPhrase = phrase;
        this.body = new byte[0];
        this.headers = new Hashtable<String, String>();
    }

    public Response() { this("", ""); }

    public String getResponseLine() { return this.HttpVersion + " " + getReplyCode(); }

    public void addHeader(String name, String value) { this.headers.put(name, value); }

    public void addToBody(String bodyLine) { addToBody(bodyLine.getBytes()); }

    public void addToBody(byte[] bodyLine) {
        if (body.length > 0) {
            this.body = addByteArrays(this.body, lineDivider.getBytes());
        }
        this.body = addByteArrays(this.body, bodyLine);
    }

    private byte[] addByteArrays(byte[] original, byte[] toBeAdded) { return ArrayJoiner.join(original, toBeAdded); }

    public byte[] getBody() { return this.body; }

    public byte[] render() {
        String headContent = this.getResponseLine() + lineDivider + renderHeaders() + lineDivider;
        return  addByteArrays(headContent.getBytes(),getBody());
    }

    private String renderHeaders() {
        String headersBody = "";
        Enumeration<String> headerNames =  this.headers.keys();
        while(headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headersBody += (headerName + headerColon + this.headers.get(headerName) + lineDivider);
        }

        return headersBody;
    }

    private String getReplyCode() { return code + " " + reasonPhrase; }
}
