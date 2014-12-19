package com.java_server.parser;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;

/**
 * Created by Alex Codreanu on 12/16/14.
 */
public class ConfigXMLParser implements ConfigParser {
    String directorySelector = "dirPath";
    String portSelector = "port";
    ServerXMLParser defaultsParser, routesParser;

    public ConfigXMLParser(File inDefaultsFile, File inRoutesFile)
            throws ParserConfigurationException, IOException, SAXException {
        defaultsParser = new ServerXMLParser(inDefaultsFile);
        routesParser = new ServerXMLParser(inRoutesFile);
    }

    @Override
    public String getDefaultDirPath() {
        return getDefaultElementValue(directorySelector);
    }

    @Override
    public String getDefaultPort() {
        return getDefaultElementValue(portSelector);
    }

    @Override
    public XMLRouteWrapper[] getRoutes() {
        NodeList routeNodes = routesParser.getElements("route");
        XMLRouteWrapper[] routes = new XMLRouteWrapper[routeNodes.getLength()];
        for (int i = 0; i < routeNodes.getLength(); i++) {
            routes[i] = newWrapper(routeNodes.item(i));
        }

        return routes;
    }

    private XMLRouteWrapper newWrapper(Node routeNode) {
        return new XMLRouteWrapperProd((Element) routeNode);
    }

    private String getDefaultElementValue(String elName) {
        NodeList elements = defaultsParser.getElements(elName);
        return elements.item(0).getTextContent();
    }
}
