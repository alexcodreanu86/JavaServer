package com.java_server.response;

/**
 * Created by Alex Codreanu on 11/23/14.
 */
public class ResponseFactory {
    public static Response methodNotAllowed() {
        return new Response("405", ResponseCodes.getReasonPhrase("405"));
    }

    public static Response notFound() {
        return new Response("404", ResponseCodes.getReasonPhrase("404"));
    }

    public static Response OK() {
        return new Response("200", ResponseCodes.getReasonPhrase("200"));
    }

    public static Response partialContent() {
        return new Response("206", ResponseCodes.getReasonPhrase("206"));
    }

    public static Response movedPermanently() {
        return new Response("301", ResponseCodes.getReasonPhrase("301"));
    }

    public static Response found() {
        return new Response("302", ResponseCodes.getReasonPhrase("302"));
    }
}
