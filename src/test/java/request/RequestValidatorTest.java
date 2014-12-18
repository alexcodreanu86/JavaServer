package request;

import com.java_server.request.Request;
import com.java_server.request.RequestValidator;
import com.java_server.routing.Route;
import com.java_server.routing.RoutesDispatcher;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Hashtable;

/**
 * Created by Alex Codreanu on 11/21/14.
 */
public class RequestValidatorTest {
    @Test
    public void testIsValidRequestReturnsTrueWhenRequestIsValid() {
        String url = "/method_options";
        String body = "";
        Route route = new Route(url, new String[]{"GET"}, new byte[0]);
        RoutesDispatcher.addRoute(route);
        Request request = newRequest(url, body, "GET");
        RequestValidator validator = new RequestValidator(request);
        assertTrue(validator.isValidUrl());
    }

    @Test
    public void testIsValidRequestReturnsFalseWhenRequestHasInvalidUrl() {
        String url = "/invalid_url";
        String body = "";
        Route route = new Route("/home", new String[]{"GET"}, new byte[0]);
        RoutesDispatcher.addRoute(route);

        Request request = newRequest(url, body, "GET");
        RequestValidator validator = new RequestValidator(request);
        assertFalse(validator.isValidUrl());
    }

    @Test
    public void testIsValidRequestReturnsFalseWhenRequestHasInvalidMethodForTheUrl() {
        String url = "/method_options";
        String body = "requestBody=body";
        Route route = new Route(url, new String[]{"GET"}, new byte[0]);
        RoutesDispatcher.addRoute(route);
        Request request = newRequest(url, body, "DELETE");
        RequestValidator validator = new RequestValidator(request);
        assertFalse(validator.isValidMethod());
    }

    private Request newRequest(String url, String body,String method) {
        Hashtable headers = new Hashtable();
        return new Request(method, url, body, headers);
    }
}
