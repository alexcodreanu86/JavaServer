package com.java_server.response.handlers;

import com.java_server.request.Request;
import com.java_server.response.Response;
import com.java_server.routing.Route;

/**
 * Created by Alex Codreanu on 11/27/14.
 */
public class OKHandler {
    Response response;
    Request request;
    Route route;
    public OKHandler(Request inRequest, Response inResponse, Route inRoute) {
        this.response = inResponse;
        this.request  = inRequest;
        this.route    = inRoute;
    }

    public void populateResponse() {
        response.addHeader("Content-Type", "text/html");
        byte[] data = route.getData();
        if (data.length > 0) {
            response.addToBody(data);
        }
        addRequestParamsToResponse(response);
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
