package com.java_server.response;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by Alex Codreanu on 11/19/14.
 */
public class Response {
    String HttpVersion = "HTTP/1.1";
    String lineDivider = "\r\n";
    String headerColon = ": ";
    private String body, code, reasonPhrase;
    private Hashtable<String, String> headers;
    public Response(String inCode, String phrase){
        this.code = inCode;
        this.reasonPhrase = phrase;
        this.body = "";
        this.headers = new Hashtable<String, String>();
    }

    public Response() {
        new Response("", "");
    }

    public String getResponseLine() {
        return this.HttpVersion + " " + getReplyCode();
    }

    public void addHeader(String name, String value) {
        this.headers.put(name, value);
    }

    public void addToBody(String bodyLine) {
        if (body.length() > 0) {
            this.body += lineDivider;
        }
        this.body += bodyLine;
    }

    public String getBody() {
        return this.body;
    }

    public String render() {
        return this.getResponseLine() + lineDivider + renderHeaders() + lineDivider + getBody();
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

    private String getReplyCode() {
        return code + " " + reasonPhrase;
    }
}
