package com.java_server.request;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Hashtable;

/**
 * Created by Alex Codreanu on 11/19/14.
 */
public class Request {
    String paramsDelimiter = "\\?";
    String paramsSeparator = "\\&";
    private String method, url, body, requestLine;
    private Hashtable<String, String> headers = new Hashtable<String, String>();
    private Hashtable<String, String> params = new Hashtable<String, String>();

    public Request(String inMethod, String inUrl, String inBody, Hashtable<String, String> inHeaders) {
        this(inMethod, inUrl, inBody, inHeaders, inMethod + inUrl + "HTTP/1.1");
    }

    public Request(String inMethod,
                   String inUrl,
                   String inBody,
                   Hashtable<String,
                   String> inHeaders,
                   String inRequestLine) {

        this.method      = inMethod;
        this.url         = processUrl(inUrl);
        this.body        = inBody;
        this.headers     = inHeaders;
        this.requestLine = inRequestLine;
        processParams(inUrl);

    }

    public String getRequestLine() {
        return requestLine;
    }

    public Hashtable<String, String> getParams() {
        return this.params;
    }

    public String getParam(String paramName) {
        return this.params.get(paramName);
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

    public Hashtable<String, String> getHeaders() {
        return this.headers;
    }

    private void processParams(String inUrl) {
        String[] urlParams = inUrl.split(paramsDelimiter);
        if (urlParams.length > 1) {
            try {
                setParams(urlParams[1]);
            }
            catch (UnsupportedEncodingException e) {}
        }
    }

    private void setParams(String allParams) throws UnsupportedEncodingException {
        for(String paramPair: allParams.split(paramsSeparator)) {
            String[] paramFields = paramPair.split("=");
            String paramName = URLDecoder.decode(paramFields[0], "UTF-8");
            String paramValue = URLDecoder.decode(paramFields[1], "UTF-8");
            this.params.put(paramName, paramValue);
        }
    }

    private String processUrl(String inUrl) {
        return inUrl.split(paramsDelimiter)[0];
    }

}
