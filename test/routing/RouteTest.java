package routing;

import com.java_server.routing.Route;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

/**
 * Created by Alex Codreanu on 11/23/14.
 */
public class RouteTest {
    @Test
    public void testGetData_returnsTheData() {
        byte[] data = "home data".getBytes();
        Route route = newRoute("/home", new String[] {"GET"}, data);

        assert(Arrays.equals(data, route.getData()));
    }

    @Test
    public void testSetData_setsDataToTheNewValue() {
        byte[] oldData = "Old Data".getBytes();
        byte[] newData = "Other Data".getBytes();
        Route route = newRoute("/home", new String[] {"GET"}, oldData);
        assert(Arrays.equals(oldData, route.getData()));

        route.setData(newData);
        assert(Arrays.equals(newData, route.getData()));
    }

    @Test
    public void testGetUrl_returnTheUrlTheRouteIsInitalizedWith() {
        String url = "/home_path";
        Route route = newRoute(url, new String[] {"GET"}, new byte[0]);

        assertEquals(url, route.getUrl());
    }

    @Test
    public void testGetMethods_returnsTheRouteMethods() {
        String[] methods = new String[] {"GET", "POST"};
        Route route = newRoute("/home", methods, new byte[0]);

        assert(Arrays.equals(methods, route.getMethods()));
    }

    @Test
    public void restRequiresAuthentication_returnsTrueWhenRequiresAuthentication() {
        String[] methods = new String[] {"GET", "POST"};
        Route route = newRouteWithAuth("/home", methods, new byte[0], true);

        assert(route.requiresAuthentication());
    }
    @Test
    public void testSetMethods_setsMethodsToTheNewValue() {
        String[] oldMethods = new String[] {"DELETE", "PUT"};
        String[] newMethods = new String[] {"GET", "POST"};

        Route route = newRoute("/home", oldMethods, new byte[0]);
        assert(Arrays.equals(oldMethods, route.getMethods()));

        route.setMethods(newMethods);
        assert(Arrays.equals(newMethods, route.getMethods()));
    }

    private Route newRoute(String url, String[] methods, byte[] data) {
        return newRouteWithAuth(url, methods, data, false);
    }

    private Route newRouteWithAuth(String url, String[] methods, byte[] data, boolean requiresAuth) {
        return new Route(url, methods, data, requiresAuth);
    }
}
