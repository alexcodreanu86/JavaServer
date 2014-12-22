package com.java_server.response.handlers;

import com.java_server.response.Response;
import com.java_server.routing.Route;

import java.util.Arrays;

/**
 * Created by Alex Codreanu on 11/27/14.
 */
public class PartialContentHandler {
    private String range;
    private Response response;
    private Route route;

    public PartialContentHandler(String inRange, Response inResponse, Route inRoute) {
        this.range    = inRange;
        this.response = inResponse;
        this.route    = inRoute;
    }

    public void populateResponse() {
        String rangeStr = range.split("bytes=")[1];
        String[] rangeParts = rangeStr.split("-");

        response.addToBody(getPartialData(rangeParts, route.getData()));
    }

    private byte[] getPartialData(String[] rangeParts, byte[] data) {
        int[] rangeBoundaries = getRangeBoundaries(rangeParts, data);
        return Arrays.copyOfRange(data, rangeBoundaries[0], rangeBoundaries[1]);
    }

    private int[] getRangeBoundaries(String[]rangeParts, byte[] data) {
        int rangeStart = getRangeStart(rangeParts, data);
        int rangeEnd;
        if (rangeParts.length > 1 && !rangeParts[0].equals("")) {
            rangeEnd = getRangeEnd(rangeParts, data);
        } else {
            rangeEnd = data.length;
        }
        return new int[] {rangeStart, rangeEnd};
    }

    private int getRangeStart(String[] rangeParts, byte[] data) {
        if (rangeParts[0].length() > 0) {
            return Integer.parseInt(rangeParts[0]);
        } else {
            return data.length - Integer.parseInt(rangeParts[1]);
        }
    }

    private int getRangeEnd(String[] rangeParts, byte[] data) {
        if (rangeParts[1].length() > 0) {
            return Integer.parseInt(rangeParts[1]) + 1;
        } else {
            return data.length - 1;
        }
    }

}
