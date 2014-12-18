package response.handlers;

import com.java_server.args.GlobalArguments;
import com.java_server.response.Response;
import com.java_server.response.ResponseFactory;
import com.java_server.response.handlers.MovedPermanentlyHandler;
import com.java_server.routing.Route;
import com.java_server.routing.RoutesDispatcher;
import org.junit.Test;
import utils.MockConfigParser;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Alex Codreanu on 11/27/14.
 */
public class MovedPermanentlyHandlerTest {
    @Test
    public void testGetResponse_returnsRedirectResponseWhenRequestRouteIsredirect() throws IOException {
        String routePath = "/redirect";
        Route route = new Route(routePath, new String[] {"GET"}, new byte[0]);
        RoutesDispatcher.addRoute(route);
        GlobalArguments.setArgs(new String[0], new MockConfigParser("mockPath", "5000"));

        Response response = ResponseFactory.MovedPermanently();
        new MovedPermanentlyHandler(response).populateResponse();
        String expectedResponse = "HTTP/1.1 301 Moved Permanently\r\n" +
                "Location: http://localhost:5000/" +
                "\r\n\r\n";

        assert(Arrays.equals(expectedResponse.getBytes(), response.render()));
    }
}
