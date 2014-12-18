package com.java_server.utils;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by Alex Codreanu on 12/18/14.
 */
public class ServerXMLParser {
    Document configDocument;

    public ServerXMLParser(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        configDocument = db.parse(file);
        configDocument.getDocumentElement().normalize();
    }

    public NodeList getElements(String elSelector) {
        return configDocument.getElementsByTagName(elSelector);
    }

}
