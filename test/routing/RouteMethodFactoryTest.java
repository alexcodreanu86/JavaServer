package routing;

import com.java_server.request.Request;
import com.java_server.request.RequestValidator;
import com.java_server.routing.Route;
import com.java_server.routing.RoutesDispatcher;
import com.java_server.routing.methods.*;
import org.junit.Test;


import java.util.Hashtable;

/**
 * Created by Alex Codreanu on 11/21/14.
 */
public class RouteMethodFactoryTest {
    @Test
    public void testBuildRouteMethod_returnsPOSTMethod() {
        String url = "/method_options";
        String body = "requestBody=body";
        registerRoute(url, new String[]{"POST"});
        Request request = newRequest(url, body, "POST");
        RequestValidator validator = new RequestValidator(request);
        RouteMethod method = RouteMethodFactory.buildRouteMethod(request, validator);
        assert(method instanceof POST);
    }

    @Test
    public void testBuildRouteMethod_returnsOPTIONSMethod() {
        String url = "/method_options";
        String body = "";
        registerRoute(url, new String[]{"OPTIONS"});
        Request request = newRequest(url, body, "OPTIONS");
        RequestValidator validator = new RequestValidator(request);
        RouteMethod method = RouteMethodFactory.buildRouteMethod(request, validator);
        assert(method instanceof OPTIONS);

    }

    @Test
    public void testBuildRouteMethod_returnsGETMethod() {
        String url = "/method_options";
        String body = "";
        registerRoute(url, new String[]{"GET"});
        Request request = newRequest(url, body, "GET");
        RequestValidator validator = new RequestValidator(request);
        RouteMethod method = RouteMethodFactory.buildRouteMethod(request, validator);
        assert(method instanceof GET);
    }

    @Test
    public void testBuildRouteMethod_returnsPATCHMethod() {
        String url = "/method_options";
        String body = "requestBody=body";
        registerRoute(url, new String[]{"PATCH"});
        Request request = newRequest(url, body, "PATCH");
        RequestValidator validator = new RequestValidator(request);
        RouteMethod method = RouteMethodFactory.buildRouteMethod(request, validator);
        assert(method instanceof PATCH);
    }

    @Test
    public void testBuildRouteMethod_returnsInvalidRequestMethodWhenRequestIsInvalid() {
        String url = "/invalid/url/here";
        String body = "";
        Request request = newRequest(url, body, "POST");
        RequestValidator validator = new RequestValidator(request);
        RouteMethod method = RouteMethodFactory.buildRouteMethod(request, validator);

        assert(method instanceof NotAllowed);
    }

    private void registerRoute(String url, String[] methods) {
        Route route = new Route(url, methods, new byte[0] );
        RoutesDispatcher.addRoute(route);
    }

    private Request newRequest(String url, String body, String method) {
        Hashtable headers = new Hashtable();
        return new Request(method, url, body, headers);
    }
}
