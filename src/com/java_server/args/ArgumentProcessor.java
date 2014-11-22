package com.java_server.args;

import java.util.Arrays;

/**
 * Created by Alex Codreanu on 11/18/14.
 */

public class ArgumentProcessor {
    private String[] args;
    private String defaultPort;
    private String defaultPath;

    final String portTag          = "-p";
    final String directoryPathTag = "-d";

    public ArgumentProcessor(String[] inArgs, String inDefaultPort, String inDefaultPath) {
        this.args = inArgs;
        this.defaultPort = inDefaultPort;
        this.defaultPath = inDefaultPath;
    }

    public int getPort() {
        return Integer.parseInt(getElementWithFallback(this.portTag, this.defaultPort));
    }

    public String getDirectoryPath() { return getElementWithFallback(this.directoryPathTag, this.defaultPath); }

    private String getElementWithFallback(String elementTag, String fallback){
        int tagIndex = indexOfArgument(elementTag);
        if (isValidIndex(tagIndex)) {
            return  this.args[tagIndex + 1];
        } else {
            return fallback;
        }

    }

    private int indexOfArgument(String identifier) {
        return Arrays.asList(this.args).indexOf(identifier);
    }

    private boolean isValidIndex(int index) {
        return index != -1;
    }
}
