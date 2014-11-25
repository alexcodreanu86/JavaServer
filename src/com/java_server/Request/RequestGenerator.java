package com.java_server.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by Alex Codreanu on 11/19/14.
 */
public class RequestGenerator {
    private static final String whiteSpaceMatcher = "\\s";
    private static final String headersColonMatcher = ": ";
    private static final String headersDelimiter = "\r\n\r\n";

    public static Request generate(BufferedReader reader) throws IOException{
        String body = "";
        String requestLine = readLine(reader);
        String method = getMethod(requestLine);
        String url = getUrl(requestLine);
        String[] rest = getRestOfContent(reader);
        Hashtable<String, String> headers = getHeaders(rest[0]);
        if (rest.length > 1) {
            body = rest[1];
        }

        return new Request(method, url, body, headers);
    }

    private static String[] getRestOfContent(BufferedReader reader) throws IOException {
        StringBuilder content = new StringBuilder();
        while(reader.ready()) content.append((char) reader.read());
        return content.toString().split(headersDelimiter);
    }

    private static String readLine(BufferedReader reader) throws IOException {
        return reader.readLine();
    }

    private static String getMethod(String requestLine) {
        return requestLine.split(whiteSpaceMatcher)[0];
    }

    private static String getUrl(String requestLine) {
        return requestLine.split(whiteSpaceMatcher)[1];
    }

    private static Hashtable<String, String> getHeaders(String request) {
        String[] headersStr = request.split("\r\n");
        Hashtable<String, String> headers = new Hashtable<String, String>();
        for (String headerLine: headersStr) {
            setHeader(headerLine, headers);
        }
        return headers;
    }

    private static void setHeader(String headerLine, Hashtable<String, String> headers) {
        String[] elements = headerLine.split(headersColonMatcher);
        if (elements.length > 1)
            headers.put(elements[0], elements[1]);
    }
}
