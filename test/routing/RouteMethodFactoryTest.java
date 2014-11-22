package routing;

import com.java_server.Request.Request;
import com.java_server.routing.*;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
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
        RouteMethod method = new RouteMethodFactory().buildRouteMethod(request, newOutStream());
        assert(method instanceof POST);
    }

    @Test
    public void testBuildRouteMethodReturnsOPTIONSForOPTIONSMethod() {
        String url = "/method_options";
        String body = "";
        Request request = newRequest(url, body, "OPTIONS");
        RouteMethod method = new RouteMethodFactory().buildRouteMethod(request, newOutStream());
        assert(method instanceof OPTIONS);

    }

    @Test
    public void testBuildRouteMethodReturnsGETForGETMethod() {
        String url = "/method_options";
        String body = "";
        Request request = newRequest(url, body, "GET");
        RouteMethod method = new RouteMethodFactory().buildRouteMethod(request, newOutStream());
        assert(method instanceof GET);
    }

    @Test
    public void testBuildRouteMethodReturnsInvalidRequestMethodWhenRequestIsInvalid() {
        String url = "/invalid/url/here";
        String body = "";
        Request request = newRequest(url, body, "POST");
        RouteMethod method = new RouteMethodFactory().buildRouteMethod(request, newOutStream());

        assert(method instanceof NOTFOUND);
    }

    private Request newRequest(String url, String body, String method) {
        Hashtable headers = new Hashtable();
        return new Request(method, url, body, headers);
    }

    private DataOutputStream newOutStream() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        return new DataOutputStream(outputStream);
    }
}
