package auth;

import com.java_server.auth.Authenticator;
import com.java_server.request.Request;
import com.java_server.routing.Route;
import com.java_server.routing.RoutesDispatcher;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.util.Hashtable;

import static org.junit.Assert.*;

/**
 * Created by Alex Codreanu on 11/28/14.
 */
public class AuthenticatorTest {
    @Test
    public void testIsAuthenticated_returnsFalseWhenUserIsNotAuthenticatedProperly() {
        String method = "GET";
        String url = "/authenticate";
        Hashtable headers = new Hashtable();
        Request request = new Request(method, url, "", headers);
        String[] methods = new String[]{"GET"};
        Route route = new Route("/authenticate", methods, new byte[0], true);
        RoutesDispatcher.addRoute(route);

        assertFalse(new Authenticator(request).isAuthorized());
    }

    @Test
    public void testIsAuthenticated_returnsTrueWhenRequestIsAuthenticatedProperly() {
        String method = "GET";
        String url = "/authenticate";
        Hashtable headers = new Hashtable();
        String encodedToken = DatatypeConverter.printBase64Binary("admin:hunter2".getBytes());

        headers.put("Authorization", "Basic " + encodedToken);
        Request request = new Request(method, url, "", headers);
        String[] methods = new String[]{"GET"};
        Route route = new Route("/authenticate", methods, new byte[0], true);
        RoutesDispatcher.addRoute(route);

        assertTrue(new Authenticator(request).isAuthorized());
    }

    @Test
    public void testIsAuthenticated_returnsTrueWhenRouteDoesNotRequireAuthentication() {
        String method = "GET";
        String url = "/authenticate";
        Hashtable headers = new Hashtable();
        Request request = new Request(method, url, "", headers);
        String[] methods = new String[]{"GET"};
        Route route = new Route("/authenticate", methods, new byte[0], false);
        RoutesDispatcher.addRoute(route);

        assertTrue(new Authenticator(request).isAuthorized());
    }
}
