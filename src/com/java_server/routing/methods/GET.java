package com.java_server.routing.methods;

import com.java_server.args.GlobalArguments;
import com.java_server.request.Request;
import com.java_server.response.Response;
import com.java_server.response.ResponseFactory;
import com.java_server.routing.Route;
import com.java_server.routing.RoutesDispatcher;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Alex Codreanu on 11/20/14.
 */
public class GET extends RouteMethod {
    private Request request;

    public GET(Request inRequest) {
        this.request = inRequest;
    }

    public Response getResponse() throws IOException {
        Route route = RoutesDispatcher.getRoute(request.getUrl());
        if (route.getUrl().equals("/redirect")) {
            return createRedirectResponse();
        } else if (request.getHeaders().get("Range") != null){
            return createPartialResponse(request.getHeaders().get("Range"), route);
        } else {
            return createSuccessResponse(route);
        }
    }

    private Response createPartialResponse(String range, Route route) {
        Response response = ResponseFactory.partialContent();
        String rangeStr = range.split("bytes=")[1];
        String[] rangeParts = rangeStr.split("-");

        response.addToBody(getPartialData(rangeParts, route.getData()));
        return response;
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

    private int getRangeEnd(String[] rangeParts, byte[] data) {
        if (rangeParts[1].length() > 0) {
            return Integer.parseInt(rangeParts[1]) + 1;
        } else {
            return data.length - 1;
        }
    }

    private int getRangeStart(String[] rangeParts, byte[] data) {
        if (rangeParts[0].length() > 0) {
            return Integer.parseInt(rangeParts[0]);
        } else {
            return data.length - Integer.parseInt(rangeParts[1]);
        }
    }

    private Response createRedirectResponse() {
        Response response = ResponseFactory.movedPermanently();
        response.addHeader("Location", "http://localhost:" + GlobalArguments.getPort() + "/");
        return response;
    }

    private Response createSuccessResponse(Route route) throws IOException {
        Response response = ResponseFactory.OK();
        response.addHeader("Content-Type", "text/html");
        byte[] data = route.getData();
        if (data.length > 0) {
            response.addToBody(data);
        } else {
            addRequestParamsToResponse(response);
        }
        return response;
    }

    private void addRequestParamsToResponse(Response response) {
        String paramsString = "";
        for(Object paramName : request.getParams().keySet()){
            if(paramsString.length() != 0) {
                paramsString += "\r\n";
            }
            paramsString += getParamsLine((String) paramName);
        }
        response.addToBody(paramsString.getBytes());
    }

    private String getParamsLine(String paramName) {
        return paramName + " = " + request.getParam(paramName);
    }
}
