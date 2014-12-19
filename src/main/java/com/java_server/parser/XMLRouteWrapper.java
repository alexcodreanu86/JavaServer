package com.java_server.parser;

/**
 * Created by Alex Codreanu on 12/17/14.
 */
public interface XMLRouteWrapper {
    public String getPath();
    public Boolean requiresAuth();
    public String[] getMethods();
    public String getRedirectPath();
}
