package routing.methods;

import com.java_server.data_storage.FilesData;
import com.java_server.request.Request;
import com.java_server.response.Response;
import com.java_server.routing.RoutesDispatcher;
import com.java_server.routing.methods.GET;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * Created by Alex Codreanu on 11/20/14.
 */
public class GETTest {
    @Test

    public void testGetResponse_addsTheBodyForAValidRequestRoute() throws IOException {
        String route = "/hello";
        String routeBody = "hello world";
        RoutesDispatcher.addRouteWithMethods(route, new String[] {"GET"});
        FilesData.addFileWithData(route, routeBody);

        Response response = new GET(newRequest(route)).getResponse();
        String expectedResponse = "HTTP/1.1 200 OK\r\n" +
                                  "Content-Type: text/html" +
                                  "\r\n\r\n" +
                                  "hello world";

        assert(Arrays.equals(expectedResponse.getBytes(), response.render()));
    }

    @Test
    public void testGetResponse_returnsANotAllowedResponseWhenMethodIsNotAllowedForRoute() throws IOException {
        String route = "/trigger_alarm";
        String routeBody = "ALARM!!!!";
        FilesData.addFileWithData(route, routeBody);
        RoutesDispatcher.addRouteWithMethods(route, new String[] {"POST"});

        Response response = new GET(newRequest(route)).getResponse();
        String expectedResponse = "HTTP/1.1 405 Method Not Allowed\r\n\r\n";

        assert(Arrays.equals(expectedResponse.getBytes(), response.render()));
    }

    private Request newRequest(String url) {
        String method = "GET";
        Hashtable headers = new Hashtable();
        return new Request(method, url, "", headers);
    }
}
