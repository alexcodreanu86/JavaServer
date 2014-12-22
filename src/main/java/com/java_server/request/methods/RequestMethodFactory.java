package com.java_server.request.methods;

import com.java_server.request.Request;
import com.java_server.request.RequestValidator;

/**
 * Created by Alex Codreanu on 11/20/14.
 */
public class RequestMethodFactory {
    public static RequestMethod buildRouteMethod(Request request, RequestValidator validator) {
        if (validator.isValidMethod()) {
            return generateValidMethod(request);
        } else {
            return generateInvalidMethod(request);
        }

    }

    private static RequestMethod generateValidMethod(Request request) {
        String methodName = request.getMethod();
        if (methodName.equals("GET")) {
            return new GET(request);
        }else if (methodName.equals("OPTIONS")) {
            return new OPTIONS(request);
        } else if (methodName.equals("DELETE")) {
            return new DELETE(request);
        } else if (methodName.equals("PATCH")){
            return new PATCH(request);
        } else {
            return new POST(request);
        }
    }

    public static RequestMethod generateInvalidMethod(Request request) {
        return new NotAllowed(request);
    }
}
