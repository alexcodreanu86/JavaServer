package server;

import com.java_server.args.GlobalArguments;
import com.java_server.routing.Route;
import com.java_server.routing.RoutesDispatcher;
import com.java_server.routing.RoutesGenerator;
import com.java_server.server.Logger;
import com.java_server.parser.ConfigParser;
import com.java_server.parser.XMLRouteWrapper;
import mocks.MockXMLRouteWrapper;
import org.junit.Test;
import mocks.MockConfigParser;

import static org.junit.Assert.*;

/**
 * Created by Alex Codreanu on 11/29/14.
 */
public class LoggerTest {
    @Test
    public void testLog_addsTheGivenTextToTheLog() {
        XMLRouteWrapper wrapper = new MockXMLRouteWrapper("/logs", true, new String[] {"GET"});
        XMLRouteWrapper[] routes = new XMLRouteWrapper[] { wrapper };
        ConfigParser parser = new MockConfigParser("somePath", "5000", routes);
        GlobalArguments.setArgs(new String[0], parser);
        RoutesGenerator.generate(parser);
        Logger.log("text here");
        Logger.log("other text here");
        Route route = RoutesDispatcher.getRoute("/logs");
        assertEquals("text here\r\nother text here\r\n", new String(route.getData()));
    }
}
