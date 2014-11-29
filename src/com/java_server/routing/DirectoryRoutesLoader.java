package com.java_server.routing;

import com.java_server.templates.DirectoryContentTemplate;

import java.io.*;

/**
 * Created by Alex Codreanu on 11/22/14.
 */
public class DirectoryRoutesLoader {
    public static void loadDirectoryContents(File directory) {
        createRootPath(directory);
        createDirectoryContentsPaths(directory);
    }

    private static void createDirectoryContentsPaths( File directory) {
        File[] directoryContents = directory.listFiles();
        if (directoryContents != null) {
            for (File file : directoryContents) {
                String fileName = file.toString().substring(directory.toString().length());
                setupGetFileRoute(fileName, readFileContents(file));
            }
        }
    }

    public static void setupFileRoute(String fileName, String[] methods, byte[] fileData) {
        Route route = new Route(fileName, methods, fileData);
        RoutesDispatcher.addRoute(route);
    }

    private static void createRootPath(File directory) {
        String template =  new DirectoryContentTemplate(directory).render();
        setupGetFileRoute("/", template.getBytes());
    }

    private static void setupGetFileRoute(String fileName, byte[] fileData) {
        setupFileRoute(fileName, new String[] {"GET", "PATCH"}, fileData);
    }

    private static byte[] readFileContents(File file) {
        ByteArrayOutputStream fileContents = new ByteArrayOutputStream();
        try {
            FileInputStream fileInput = new FileInputStream(file);
            int content;
            while ((content = fileInput.read()) != -1) {
                fileContents.write(content);
            }
        }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        return fileContents.toByteArray();
    }
}
