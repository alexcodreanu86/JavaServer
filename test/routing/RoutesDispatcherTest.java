package routing;

import com.java_server.routing.Route;
import com.java_server.routing.RoutesDispatcher;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

/**
 * Created by Alex Codreanu on 11/20/14.
 */
public class RoutesDispatcherTest {
    @Test
    public void testGetRoute() {
        String[] methods = new String[]{"GET", "HEAD", "POST", "OPTIONS", "PUT"};
        Route route = new Route("/method_options", methods, new byte[0]);
        RoutesDispatcher.addRoute(route);
        assertEquals(route, RoutesDispatcher.getRoute("/method_options"));
    }

    @Test
    public void testAddRoute_addsNewRoute() {
        String[] methods = new String[]{"GET", "HEAD"};
        Route route = new Route("/home", methods, new byte[0]);
        RoutesDispatcher.addRoute(route);

        assertEquals(route, RoutesDispatcher.getRoute("/home"));
    }
}
