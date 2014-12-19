package com.java_server.routing;


import com.java_server.parser.XMLRouteWrapper;

/**
 * Created by Alex Codreanu on 11/23/14.
 */
public class Route {
    String url, dataType, redirectPath, methods[];
    byte[] data;
    private boolean requiresAuth;

    public Route(XMLRouteWrapper wrapper) {
        this(wrapper.getPath(),
             wrapper.getMethods(),
             new byte[0],
             wrapper.requiresAuth(),
             wrapper.getRedirectPath());
    }

    public Route(String inUrl, String[] inMethods, byte[] inData) {
        this(inUrl, inMethods, inData, false, null);
    }

    public Route(String inUrl, String[] inMethods, byte[] inData, boolean inRequiresAuth) {
        this(inUrl, inMethods, inData, inRequiresAuth, null);
    }

    public Route(String inUrl, String[] inMethods, byte[] inData, boolean inRequiresAuth, String inRedirectPath) {
        this.url = inUrl;
        this.methods = inMethods;
        this.data = inData;
        this.requiresAuth = inRequiresAuth;
        this.dataType = processDataType(inUrl);
        this.redirectPath = inRedirectPath;
    }

    public boolean requiresAuthentication() { return this.requiresAuth; }

    public boolean shouldRedirect() { return redirectPath != null; }

    public String getDataType() { return this.dataType; }

    public String getRedirectPath() { return this.redirectPath; }

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
