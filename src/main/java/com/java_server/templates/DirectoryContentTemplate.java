package com.java_server.templates;

import java.io.File;

/**
 * Created by Alex Codreanu on 11/23/14.
 */
public class DirectoryContentTemplate {
    private File directory;
    public DirectoryContentTemplate(File directory) {
        this.directory = directory;
    }

    public String render() {
        File[] directoryContents = directory.listFiles();
        String template = "<ul>";
        if (directoryContents != null) {
            for(File file : directoryContents) {
                String fileName = file.toString().substring(directory.toString().length());
                template += ("<li><a href='" + fileName + "'>" + fileName.substring(1) + "</a></li>");

            }
        }

        return template + "</ul>";
    }
}
