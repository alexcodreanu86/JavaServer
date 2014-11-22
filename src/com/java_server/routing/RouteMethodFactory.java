package com.java_server.routing;

import com.java_server.Request.Request;

import java.io.DataOutputStream;

/**
 * Created by Alex Codreanu on 11/20/14.
 */
public class RouteMethodFactory {
    public RouteMethod buildRouteMethod(Request request, DataOutputStream outputStream) {
        RequestValidator validator = new RequestValidator(request);
        if (validator.isValidRequest()) {
            return generateValidMethod(request, outputStream);
        } else {
            return new NOTFOUND(request, outputStream);
        }
    }

    private RouteMethod generateValidMethod(Request request, DataOutputStream outputStream) {
        String methodName = request.getMethod();
        if (methodName == "OPTIONS") {
            return new OPTIONS(request, outputStream);
        } else {
            return new GET(request, outputStream);
        }
    }
}
