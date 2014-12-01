package request.methods;

import com.java_server.args.GlobalArguments;
import com.java_server.request.Request;
import com.java_server.response.Response;
import com.java_server.routing.Route;
import com.java_server.routing.RoutesDispatcher;
import com.java_server.request.methods.GET;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * Created by Alex Codreanu on 11/20/14.
 */
public class GETTest {
    @Test
    public void testGetResponse_addsTheBodyForAValidRequestRoute() throws IOException {
        String routePath = "/hello";
        String routeData = "hello world";
        Route route = new Route(routePath, new String[] {"GET"}, routeData.getBytes());
        RoutesDispatcher.addRoute(route);

        Response response = new GET(newRequest(routePath)).getResponse();
        String expectedResponse = "HTTP/1.1 200 OK\r\n" +
                                  "Content-Type: text/html" +
                                  "\r\n\r\n" +
                                  "hello world\r\n";

        assert(Arrays.equals(expectedResponse.getBytes(), response.render()));
    }

    @Test
    public void testGetResponse_returnsRedirectResponseWhenRequestRouteIsredirect() throws IOException {
        String routePath = "/redirect";
        Route route = new Route(routePath, new String[] {"GET"}, new byte[0]);
        RoutesDispatcher.addRoute(route);
        GlobalArguments.setArgs(new String[0]);

        Response response = new GET(newRequest(routePath)).getResponse();
        String expectedResponse = "HTTP/1.1 301 Moved Permanently\r\n" +
                "Location: http://localhost:5000/" +
                "\r\n\r\n";

        assert(Arrays.equals(expectedResponse.getBytes(), response.render()));
    }

    @Test
    public void testGetResponse_addsTheParamsToTheBodyWhenThereAreParams() throws IOException {
        String routePath = "/params";
        String params = "?param1=test1&param2=test2";
        Route route = new Route(routePath, new String[] {"GET"}, new byte[0]);
        RoutesDispatcher.addRoute(route);

        Response response = new GET(newRequest(routePath + params)).getResponse();
        String expectedResponse = "HTTP/1.1 200 OK\r\n" +
                                  "Content-Type: text/html" +
                                  "\r\n\r\n" +
                                  "param2 = test2\r\n" +
                                  "param1 = test1";

        assert(Arrays.equals(expectedResponse.getBytes(), response.render()));
    }

    @Test
    public void testGetResponse_returnsPartialContentWhenRangeHeaderExists() throws IOException {
        String routePath = "/route";
        Route route = new Route(routePath, new String[] {"GET"}, "some data in here".getBytes());
        RoutesDispatcher.addRoute(route);
        Hashtable<String, String> headers = new Hashtable<String, String>();
        headers.put("Range", "bytes=0-4");


        Response response = new GET(newRequest(routePath, headers)).getResponse();

        assert(Arrays.equals("some ".getBytes(), response.getBody()));
        assert(response.getResponseLine().equals("HTTP/1.1 206 Partial Content"));
    }

    private Request newRequest(String url) {
        return newRequest(url, new Hashtable());
    }

    private Request newRequest(String url, Hashtable headers) {
        String method = "GET";
        return new Request(method, url, "", headers);
    }
}
