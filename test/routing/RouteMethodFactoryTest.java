package routing;

import com.java_server.request.Request;
import com.java_server.request.RequestValidator;
import com.java_server.routing.methods.*;
import org.junit.Test;


import java.util.Hashtable;

/**
 * Created by Alex Codreanu on 11/21/14.
 */
public class RouteMethodFactoryTest {
    @Test
    public void testBuildRouteMethodReturnsPOSTForPOSTMethod() {
        String url = "/method_options";
        String body = "requestBody=body";
        Request request = newRequest(url, body, "POST");
        RequestValidator validator = new RequestValidator(request);
        RouteMethod method = RouteMethodFactory.buildRouteMethod(request, validator);
    }

    @Test
    public void testBuildRouteMethodReturnsOPTIONSForOPTIONSMethod() {
        String url = "/method_options";
        String body = "";
        Request request = newRequest(url, body, "OPTIONS");
        RequestValidator validator = new RequestValidator(request);
        RouteMethod method = RouteMethodFactory.buildRouteMethod(request, validator);
        assert(method instanceof OPTIONS);

    }

    @Test
    public void testBuildRouteMethodReturnsGETForGETMethod() {
        String url = "/method_options";
        String body = "";
        Request request = newRequest(url, body, "GET");
        RequestValidator validator = new RequestValidator(request);
        RouteMethod method = RouteMethodFactory.buildRouteMethod(request, validator);
        assert(method instanceof GET);
    }

    @Test
    public void testBuildRouteMethodReturnsInvalidRequestMethodWhenRequestIsInvalid() {
        String url = "/invalid/url/here";
        String body = "";
        Request request = newRequest(url, body, "POST");
        RequestValidator validator = new RequestValidator(request);
        RouteMethod method = RouteMethodFactory.buildRouteMethod(request, validator);

        assert(method instanceof NotAllowed);
    }

    private Request newRequest(String url, String body, String method) {
        Hashtable headers = new Hashtable();
        return new Request(method, url, body, headers);
    }
}
