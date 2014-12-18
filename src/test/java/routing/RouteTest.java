package routing;

import com.java_server.routing.Route;
import com.java_server.utils.XMLRouteWrapper;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
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
    public void testCanBeCreatedWithXMLWrapper() throws IOException {
        String[] methods = new String[] {"GET", "POST"};
        String url = "/test";
        XMLRouteWrapper wrapper = new MockXMLWrapper(url, true, methods);
        Route route = new Route(wrapper);

        assert(Arrays.equals(new byte[0], route.getData()));
        assert(Arrays.equals(methods, route.getMethods()));
        assertEquals(url, route.getUrl());
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
    public void testGetUrl_returnTheUrlTheRouteIsInitializedWith() {
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
    public void testRequiresAuthentication_returnsTrueWhenRequiresAuthentication() {
        String[] methods = new String[] {"GET", "POST"};
        Route route = newRouteWithAuth("/home", methods, new byte[0], true);

        assert(route.requiresAuthentication());
    }

    @Test
    public void testDataType_returnsTheDataTypeTheRouteContains() {
        String[] methods = new String[] {"GET", "POST"};
        Route route = newRouteWithAuth("/home.jpeg", methods, new byte[0], true);

        assertEquals("jpeg", route.getDataType());
    }

    @Test
    public void testDataType_returnsEmptyStringWhenThereIsNoDataType() {
        String[] methods = new String[] {"GET", "POST"};
        Route route = newRouteWithAuth("/home", methods, new byte[0], true);

        assertEquals("", route.getDataType());
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

    class MockXMLWrapper implements XMLRouteWrapper {
        String path, methods[];
        Boolean auth;

        public MockXMLWrapper(String inPath, Boolean inRequiresAuth, String[] inMethods) {
            path = inPath;
            auth = inRequiresAuth;
            methods = inMethods;
        }

        public String getPath() {
            return path;
        }
        public Boolean requiresAuth() {
            return auth;
        }
        public String[] getMethods() {
            return methods;
        }
    }
}
