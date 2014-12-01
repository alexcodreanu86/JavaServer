package response.handlers;

import com.java_server.request.Request;
import com.java_server.response.Response;
import com.java_server.response.ResponseFactory;
import com.java_server.response.handlers.OKHandler;
import com.java_server.routing.Route;
import com.java_server.routing.RoutesDispatcher;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * Created by Alex Codreanu on 11/27/14.
 */
public class OKHandlerTest {
    @Test
    public void testGetResponse_addsTheBodyForAValidRequestRoute() throws IOException {
        String routePath = "/hello";
        String routeData = "hello world";
        Route route = new Route(routePath, new String[] {"GET"}, routeData.getBytes());
        RoutesDispatcher.addRoute(route);

        String expectedResponse = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html" +
                "\r\n\r\n" +
                "hello world\r\n";
        Response response = ResponseFactory.OK();
        Request request = newRequest(routePath, new Hashtable());

        new OKHandler(request, response, route).populateResponse();

        assert(Arrays.equals(expectedResponse.getBytes(), response.render()));
    }

    @Test
    public void testGetResponse_addsContentTypeForJPEG() throws IOException {
        String routePath = "/hello.jpeg";
        String routeData = "hello world";
        Route route = new Route(routePath, new String[] {"GET"}, routeData.getBytes());
        RoutesDispatcher.addRoute(route);

        String expectedResponse = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: image/jpeg" +
                "\r\n\r\n" +
                "hello world\r\n";
        Response response = ResponseFactory.OK();
        Request request = newRequest(routePath, new Hashtable());

        new OKHandler(request, response, route).populateResponse();

        assertEquals(expectedResponse, new String(response.render()));
    }

    @Test
    public void testGetResponse_addsContentTypeForTXT() throws IOException {
        String routePath = "/hello.txt";
        String routeData = "hello world";
        Route route = new Route(routePath, new String[] {"GET"}, routeData.getBytes());
        RoutesDispatcher.addRoute(route);

        String expectedResponse = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html" +
                "\r\n\r\n" +
                "hello world\r\n";
        Response response = ResponseFactory.OK();
        Request request = newRequest(routePath, new Hashtable());

        new OKHandler(request, response, route).populateResponse();

        assertEquals(expectedResponse, new String(response.render()));
    }
    private Request newRequest(String url, Hashtable headers) {
        String method = "GET";
        return new Request(method, url, "", headers);
    }
}
