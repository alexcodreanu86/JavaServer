package com.java_server.routing.methods;

import com.java_server.request.Request;
import com.java_server.request.RequestValidator;

/**
 * Created by Alex Codreanu on 11/20/14.
 */
public class RouteMethodFactory {
    public static RouteMethod buildRouteMethod(Request request, RequestValidator validator) {
        if (validator.isValidMethod()) {
            return generateValidMethod(request);
        } else {
            return generateInvalidMethod(request);
        }

    }

    private static RouteMethod generateValidMethod(Request request) {
        String methodName = request.getMethod();
        if (methodName.equals("POST")) {
            return new POST(request);
        }else if (methodName.equals("OPTIONS")) {
            return new OPTIONS(request);
        } else if (methodName.equals("PUT")) {
            return new PUT(request);
        } else if (methodName.equals("DELETE")) {
            return new DELETE(request);
        } else {
            return new GET(request);
        }
    }

    public static RouteMethod generateInvalidMethod(Request request) {
        return new NotAllowed(request);
    }
}
