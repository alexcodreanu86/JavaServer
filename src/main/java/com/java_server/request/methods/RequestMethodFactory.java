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
            return generateInvalidMethod();
        }
    }

    private static RequestMethod generateValidMethod(Request request) {
        String methodName = request.getMethod();
        if (methodName.equals("GET")) {
            return new ReadData(request);
        }else if (methodName.equals("OPTIONS")) {
            return new RouteOptions();
        } else if (methodName.equals("PATCH")){
            return new PatchData(request);
        } else {
            return new ChangeData(request);
        }
    }

    public static RequestMethod generateInvalidMethod() { return new NotAllowed(); }
}
