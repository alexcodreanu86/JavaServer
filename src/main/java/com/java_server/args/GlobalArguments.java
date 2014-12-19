package com.java_server.args;

import com.java_server.parser.ConfigParser;

/**
 * Created by Alex Codreanu on 11/22/14.
 */
public class GlobalArguments {
    private static ArgumentProcessor argumentProcessor;

    public static void setArgs(String[] args, ConfigParser configParser) {
        String currentDir = System.getProperty("user.dir");
        argumentProcessor = new ArgumentProcessor(args,
                                                  configParser.getDefaultPort(),
                                                  currentDir + configParser.getDefaultDirPath());
    }

    public static int getPort() {
        return argumentProcessor.getPort();
    }

    public static String getRootDirectory() {
        return argumentProcessor.getDirectoryPath();
    }
}
