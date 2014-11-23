package routing;

import com.java_server.routing.RoutesDispatcher;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Alex Codreanu on 11/20/14.
 */
public class RoutesDispatcherTest {
    @Test
    public void testGetRoutesMethod() {
        String[] expectedResponse = new String[]{"GET", "HEAD", "POST", "OPTIONS", "PUT"};
        assert(Arrays.equals(expectedResponse, RoutesDispatcher.getRouteMethods("/method_options")));
        assert(Arrays.equals(new String[]{"GET"}, RoutesDispatcher.getRouteMethods("/")));
    }
}
