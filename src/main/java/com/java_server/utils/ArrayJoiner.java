package com.java_server.utils;

/**
 * Created by Alex Codreanu on 11/23/14.
 */
public class ArrayJoiner {
    public static byte[] join(byte[] original, byte[] toBeAdded) {
        int originalLength = original.length;
        int toBeAddedLength = toBeAdded.length;
        byte[] cummulator = new byte[originalLength + toBeAddedLength];
        System.arraycopy(original, 0, cummulator, 0, originalLength);
        System.arraycopy(toBeAdded, 0, cummulator, originalLength, toBeAddedLength);
        return cummulator;
    }
}
