package com.java_server.request;

import java.util.Hashtable;

/**
 * Created by Alex Codreanu on 11/19/14.
 */
public class Request {
    private String method, url, body;
    private Hashtable headers = new Hashtable();
    private String HttpVersion = "HTTP/1.1";

    public Request(String inMethod, String inUrl, String inBody, Hashtable inHeaders) {
        this.method = inMethod;
        this.url = inUrl;
        this.body = inBody;
        this.headers = inHeaders;
    }

    public String getMethod() {
        return this.method;
    }

    public String getUrl() {
        return this.url;
    }

    public String getBody() {
        return this.body;
    }
    public Hashtable getHeaders() {
        return this.headers;
    }
}
