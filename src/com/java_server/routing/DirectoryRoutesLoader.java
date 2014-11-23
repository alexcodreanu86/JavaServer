package com.java_server.routing;

import com.java_server.data_storage.FilesData;

import java.io.*;

/**
 * Created by Alex Codreanu on 11/22/14.
 */
public class DirectoryRoutesLoader {
    public static void loadDirectoryContents(File directory) {
        File[] directoryContents = directory.listFiles();
        if (directoryContents != null) {
            for(File file : directoryContents) {
                String fileName = file.toString().substring(directory.toString().length());
                RoutesDispatcher.addRouteWithMethods(fileName, new String[] {"GET"});

                FilesData.addFileWithData(fileName, readFileContents(file));
            }
        }
        printFileContents(FilesData.getFileData("/file2"));
    }

    private static void printFileContents(byte[] contents) {
        //TODO Files can be accessed from the FilesData
        for (byte character : contents) {
            System.out.print((char) character);
        }
    }

    public static byte[] readFileContents(File file) {
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
