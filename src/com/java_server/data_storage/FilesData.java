package com.java_server.data_storage;

import java.util.Hashtable;

/**
 * Created by Alex Codreanu on 11/22/14.
 */
public class FilesData {
    static Hashtable<String, byte[]> storage = new Hashtable<String, byte[]>();

    public static void addFileWithData(String fileName, byte[] data) {
        storage.put(fileName, data);
    }

    public static void addFileWithData(String fileName, String data) {
        storage.put(fileName, data.getBytes());
    }

    public static byte[] getFileData(String fileName) {
        return storage.get(fileName);
    }
}
