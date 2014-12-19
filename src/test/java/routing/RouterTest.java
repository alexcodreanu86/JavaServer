package routing;

import com.java_server.args.GlobalArguments;
import com.java_server.routing.Route;
import com.java_server.routing.Router;
import com.java_server.routing.RoutesDispatcher;
import com.java_server.routing.RoutesGenerator;
import com.java_server.parser.ConfigParser;
import com.java_server.parser.XMLRouteWrapper;
import mocks.MockClientSocket;
import mocks.MockReader;
import mocks.MockXMLRouteWrapper;
import org.junit.Before;
import org.junit.Test;
import mocks.MockConfigParser;

import static org.junit.Assert.*;

import java.io.*;


/**
 * Created by Alex Codreanu on 11/29/14.
 */
public class RouterTest {
    @Before
    public void setupApp() {
        XMLRouteWrapper wrapper1 = new MockXMLRouteWrapper("/logs", true, new String[] {"GET"});
        XMLRouteWrapper wrapper2 = new MockXMLRouteWrapper("/", false, new String[] {"GET"});
        XMLRouteWrapper[] routes = new XMLRouteWrapper[] { wrapper1, wrapper2 };
        ConfigParser parser = new MockConfigParser("somePath", "5000", routes);

        GlobalArguments.setArgs(new String[0], parser);
        RoutesGenerator.generate(parser);
    }

    @Test
    public void testClosesTheConnection() {
        String request = "GET / HTTP/1.1\r\n\r\n";
        MockClientSocket socket = new MockClientSocket();
        BufferedReader reader = new BufferedReader(new MockReader(request));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();

        Router router = new Router(reader, writer, socket);
        router.processRequest();

        assert(socket.isClosed());
    }

    @Test
    public void testLogsTheRequest() {
        String request = "GET /test_route HTTP/1.1\r\n\r\n";
        BufferedReader reader = new BufferedReader(new MockReader(request));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();

        Router router = new Router(reader, writer, new MockClientSocket());
        router.processRequest();

        Route route = RoutesDispatcher.getRoute("/logs");
        assertEquals("GET /test_route HTTP/1.1\r\n", new String(route.getData()));
    }

    @Test
    public void testRespondsToTheRequest() {
        String request = "GET / HTTP/1.1\r\n\r\n";
        BufferedReader reader = new BufferedReader(new MockReader(request));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();

        Router router = new Router(reader, writer, new MockClientSocket());
        router.processRequest();

        assert(writer.toString().contains("HTTP/1.1 200 OK\r\n"));
    }
}

