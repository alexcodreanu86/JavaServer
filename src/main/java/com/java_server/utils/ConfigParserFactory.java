package com.java_server.utils;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by Alex Codreanu on 12/18/14.
 */
public class ConfigParserFactory {
    private final String relativeResourcesPath = "/../java_server/resources/";

    public ConfigParser generate() throws ParserConfigurationException, IOException, SAXException {
        return new ConfigXMLParser(getDefaultsFile(), getRoutesFile());
    }

    private File getDefaultsFile() {
        return getResourceFile("defaults.xml");
    }

    private File getRoutesFile() {
        return getResourceFile("routes.xml");
    }

    private File getResourceFile(String fileName) {
        return new File(getResourcesDirectory(), fileName);
    }

   protected File getResourcesDirectory() {
       String currentLocation = System.getProperty("user.dir");
       return new File(currentLocation + relativeResourcesPath);
   }
}
