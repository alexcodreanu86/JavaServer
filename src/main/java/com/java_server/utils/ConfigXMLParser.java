package com.java_server.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;

/**
 * Created by Alex Codreanu on 12/16/14.
 */
public class ConfigXMLParser {
    String directorySelector = "dirPath";
    String portSelector = "port";
    Document configDocument;

    public ConfigXMLParser(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        configDocument = db.parse(file);
        configDocument.getDocumentElement().normalize();
    }

    public String getDefaultDirPath() {
        return getDefaultEl(directorySelector);
    }

    public String getDefaultPort() {
        return getDefaultEl(portSelector);
    }

    public XMLRouteWrapper[] getRoutes() {
        Element el = getCategoryEl("routes");
        NodeList routeNodes = el.getElementsByTagName("route");
        XMLRouteWrapper[] routes = new XMLRouteWrapper[routeNodes.getLength()];
        for (int i = 0; i < routeNodes.getLength(); i++) {
            routes[i] = newWrapper(routeNodes.item(i));
        }

        return routes;
    }

    private XMLRouteWrapper newWrapper(Node routeNode) {
        return new XMLRouteWrapperProd((Element) routeNode);
    }

    private String getDefaultEl(String elName) {
        Element el = getCategoryEl("defaults");
        NodeList elements = el.getElementsByTagName(elName);
        return elements.item(0).getTextContent();
    }

    private Element getCategoryEl(String categoryName) {
        NodeList nl = configDocument.getElementsByTagName(categoryName);
        return (Element) nl.item(0);
    }
}
