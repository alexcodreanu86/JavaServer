package request.methods;

import com.java_server.request.Request;
import com.java_server.response.Response;
import com.java_server.routing.Route;
import com.java_server.routing.RoutesDispatcher;
import com.java_server.request.methods.DELETE;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * Created by Alex Codreanu on 11/23/14.
 */
public class DELETETest {
    @Test
    public void processRequestSendsASucessfullResponseOnRouteForms() {
        String url = "/form";
        RoutesDispatcher.addRoute(new Route(url, new String[]{"DELETE"}, new byte[0]));
        String body = "";
        Request request = newRequest(url, body);
        DELETE deleteRouter = new DELETE(request);

        try {
            Response response = deleteRouter.getResponse();
            assertEquals("HTTP/1.1 200 OK", response.getResponseLine());
        }
        catch (IOException e) {
            fail(e.toString());
        }
    }

    @Test
    public void processRequestResetsRouteData() {
        String url = "/form";
        RoutesDispatcher.addRoute(new Route(url, new String[] {"DELETE"}, "currentData".getBytes()));
        String body = "";
        Request request = newRequest(url, body);
        DELETE deleteRouter = new DELETE(request);

        try {
            deleteRouter.getResponse();
            assert(Arrays.equals(new byte[0], RoutesDispatcher.getRoute(url).getData()));
        }
        catch (IOException e) {
            fail(e.toString());
        }
    }

    private Request newRequest(String url, String body) {
        String method = "DELETE";
        Hashtable headers = new Hashtable();
        return new Request(method, url, body, headers);
    }
}
