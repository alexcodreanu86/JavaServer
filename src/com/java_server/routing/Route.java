package com.java_server.routing;


/**
 * Created by Alex Codreanu on 11/23/14.
 */
public class Route {
    String url, methods[];
    byte[] data;

    public Route(String inUrl, String[] inMethods, byte[] inData) {
        this.url = inUrl;
        this.methods = inMethods;
        this.data = inData;
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
