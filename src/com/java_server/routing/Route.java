package com.java_server.routing;


/**
 * Created by Alex Codreanu on 11/23/14.
 */
public class Route {
    String url, methods[];
    byte[] data;
    private boolean requiresAuth;

    public Route(String inUrl, String[] inMethods, byte[] inData) {
        this(inUrl, inMethods, inData, false);
    }
    public Route(String inUrl, String[] inMethods, byte[] inData, boolean inRequiresAuth) {
        this.url = inUrl;
        this.methods = inMethods;
        this.data = inData;
        this.requiresAuth = inRequiresAuth;
    }

    public boolean requiresAuthentication() {
        return this.requiresAuth;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] newData) {
        this.data = newData;
    }

    public String getUrl() {
        return this.url;
    }

    public String[] getMethods() {
        return this.methods;
    }

    public void setMethods(String[] newMethods) {
        this.methods = newMethods;
    }
}
