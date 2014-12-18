package request.methods;

import static org.junit.Assert.*;

import com.java_server.request.Request;
import com.java_server.response.Response;
import com.java_server.routing.Route;
import com.java_server.routing.RoutesDispatcher;
import com.java_server.request.methods.POST;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * Created by Alex Codreanu on 11/20/14.
 */
public class POSTTest {
    @Test
    public void processRequestSendsASucessfullResponseOnRouteForms() {
        String url = "/form";
        RoutesDispatcher.addRoute(new Route(url, new String[] {"POST"}, new byte[0]));
        String body = "requestBody=body";
        Request request = newRequest(url, body);
        POST postRouter = new POST(request);

        try {
            Response response = postRouter.getResponse();
            assertEquals("HTTP/1.1 200 OK", response.getResponseLine());
        }
        catch (IOException e) {
            fail(e.toString());
        }
    }

    @Test
    public void processRequestResetsRouteData() {
        String url = "/form";
        RoutesDispatcher.addRoute(new Route(url, new String[] {"POST"}, new byte[0]));
        String body = "requestBody=body";
        Request request = newRequest(url, body);
        POST postRouter = new POST(request);

        try {
            postRouter.getResponse();
            assert(Arrays.equals(body.getBytes(), RoutesDispatcher.getRoute(url).getData()));
        }
        catch (IOException e) {
            fail(e.toString());
        }
    }

    private Request newRequest(String url, String body) {
        String method = "POST";
        Hashtable headers = new Hashtable();
        return new Request(method, url, body, headers);
    }
}
