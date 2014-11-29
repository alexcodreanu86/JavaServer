package routing.methods;

import com.java_server.request.Request;
import com.java_server.response.Response;
import com.java_server.routing.Route;
import com.java_server.routing.RoutesDispatcher;
import com.java_server.routing.methods.PATCH;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * Created by Alex Codreanu on 11/26/14.
 */
public class PATCHTest {

    @Test
    public void processRequestSendsASucessfullResponseOnRouteForms() {
        String url = "/form";
        RoutesDispatcher.addRoute(new Route(url, new String[] {"PATCH"}, new byte[0]));
        String body = "requestBody=body";
        Request request = newRequest(url, body);
        PATCH postRouter = new PATCH(request);

        try {
            Response response = postRouter.getResponse();
            assertEquals("HTTP/1.1 204 No Content", response.getResponseLine());
        }
        catch (IOException e) {
            fail(e.toString());
        }
    }

    @Test
    public void processRequestResetsRouteData() {
        String url = "/form";
        RoutesDispatcher.addRoute(new Route(url, new String[] {"PATCH"}, new byte[0]));
        String body = "requestBody=body";
        Request request = newRequest(url, body);
        PATCH postRouter = new PATCH(request);

        try {
            postRouter.getResponse();
            assert(Arrays.equals(body.getBytes(), RoutesDispatcher.getRoute(url).getData()));
        }
        catch (IOException e) {
            fail(e.toString());
        }
    }

    private Request newRequest(String url, String body) {
        String method = "PATCH";
        Hashtable headers = new Hashtable();
        return new Request(method, url, body, headers);
    }
}
