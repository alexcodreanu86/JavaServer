package com.java_server.routing;


/**
 * Created by Alex Codreanu on 11/23/14.
 */
public class Route {
    String url, dataType, methods[];
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
        this.dataType = processDataType(inUrl);
    }

    public boolean requiresAuthentication() {
        return this.requiresAuth;
    }

    public String getDataType() {
        return this.dataType;
    }

    private String processDataType(String routeUrl) {
        String[] urlParts = routeUrl.split("\\.");

        if (urlParts.length > 1) {
            return urlParts[1];
        } else {
            return "";
        }
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
