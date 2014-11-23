package com.java_server.args;

/**
 * Created by Alex Codreanu on 11/22/14.
 */
public class GlobalArguments {
    private static ArgumentProcessor argumentProcessor;
    static final String defaultPort = "5000";
    static final String defaultDirectoryPath = "/Users/alextsveta2012/8thLight/server/cob_spec/public/";

    public static void setArgs(String[] args) {
        argumentProcessor = new ArgumentProcessor(args, defaultPort, defaultDirectoryPath);
    }

    public static int getPort() {
        return argumentProcessor.getPort();
    }

    public static String getRootDirectory() {
        return argumentProcessor.getDirectoryPath();
    }
}
