package com.java_server.utils;

/**
 * Created by Alex Codreanu on 12/18/14.
 */
public interface ConfigParser {
    String getDefaultDirPath();

    String getDefaultPort();

    XMLRouteWrapper[] getRoutes();
}
