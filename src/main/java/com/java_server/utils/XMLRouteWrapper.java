package com.java_server.utils;

/**
 * Created by Alex Codreanu on 12/17/14.
 */
public interface XMLRouteWrapper {
    public String getPath();
    public Boolean requiresAuth();
    public String[] getMethods();
}
