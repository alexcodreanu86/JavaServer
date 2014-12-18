package utils;

import com.java_server.utils.ConfigParser;
import com.java_server.utils.XMLRouteWrapper;

public class MockConfigParser implements ConfigParser {
    String dirPath, port;
    public MockConfigParser (String inDirPath, String inPort) {
        dirPath = inDirPath;
        port = inPort;
    }

    public String getDefaultDirPath() {
        return dirPath;
    }

    public String getDefaultPort(){
        return port;
    }

    public XMLRouteWrapper[] getRoutes() {
        return new XMLRouteWrapper[0];
    };
}
