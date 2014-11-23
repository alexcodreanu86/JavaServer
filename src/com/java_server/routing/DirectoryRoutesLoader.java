package com.java_server.routing;

import com.java_server.data_storage.FilesData;
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
                setupFileRoute(fileName, readFileContents(file));
            }
        }
    }

    private static void setupFileRoute(String fileName, byte[] fileData) {
        RoutesDispatcher.addRouteWithMethods(fileName, new String[] {"GET"});

        FilesData.addFileWithData(fileName, fileData);
    }

    private static void createRootPath(File directory) {
        String template =  new DirectoryContentTemplate(directory).render();
        setupFileRoute("/", template.getBytes());
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
