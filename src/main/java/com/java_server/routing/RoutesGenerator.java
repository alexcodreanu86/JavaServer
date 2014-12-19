package com.java_server.routing;

import com.java_server.args.GlobalArguments;
import com.java_server.parser.ConfigParser;
import com.java_server.parser.XMLRouteWrapper;

import java.io.File;

/**
 * Created by Alex Codreanu on 11/23/14.
 */
public class RoutesGenerator {
    public static void generate(ConfigParser parser) {
        generateDirectoryContents();
        generateRoutesFromConfig(parser);
    }

    private static void generateRoutesFromConfig(ConfigParser parser) {
        XMLRouteWrapper[] wrappers = parser.getRoutes();
        for (XMLRouteWrapper wrapper : wrappers) {
            addRouteFromXML(wrapper);
        }
    }

    private static void addRouteFromXML(XMLRouteWrapper wrapper) {
        Route route = new Route(wrapper);
        RoutesDispatcher.addRoute(route);
    }

    private static void generateDirectoryContents() {
        File directory = new File(GlobalArguments.getRootDirectory());
        DirectoryRoutesLoader.loadDirectoryContents(directory);
    }
}
