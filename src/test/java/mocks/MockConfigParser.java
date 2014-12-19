package mocks;

import com.java_server.parser.ConfigParser;
import com.java_server.parser.XMLRouteWrapper;

public class MockConfigParser implements ConfigParser {
    String dirPath, port;
    XMLRouteWrapper[] routes;
    public MockConfigParser (String inDirPath, String inPort) {
        this(inDirPath, inPort, new XMLRouteWrapper[0]);
    }

    public MockConfigParser(String inDirPath, String inPort, XMLRouteWrapper[] inRoutes) {
        dirPath = inDirPath;
        port = inPort;
        routes = inRoutes;
    }

    public String getDefaultDirPath() {
        return dirPath;
    }

    public String getDefaultPort(){
        return port;
    }

    public XMLRouteWrapper[] getRoutes() {
        return routes;
    }
}
