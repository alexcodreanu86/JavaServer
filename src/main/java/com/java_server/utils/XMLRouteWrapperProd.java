package com.java_server.utils;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

/**
 * Created by Alex Codreanu on 12/16/14.
 */
public class XMLRouteWrapperProd implements XMLRouteWrapper {
    Element route;
    public XMLRouteWrapperProd(Element route) {
        this.route = route;
    }

    public String getPath() {
        NodeList paths = getElements("path");
        return paths.item(0).getTextContent();
    }

    public Boolean requiresAuth() {
        NodeList auth = getElements("auth");
        String authValue = "";
        if (auth.getLength() > 0) {
            authValue  = auth.item(0).getTextContent();
        }
        return authValue.equals("true");
    }

    public String[] getMethods() {
        NodeList methodsNodes = getElements("method");
        String[] methods =  new String[methodsNodes.getLength()];

        for (int i = 0; i < methodsNodes.getLength(); i++) {
            Node method = methodsNodes.item(i);
            methods[i] = method.getTextContent();
        }

        return methods;
    }

    private NodeList getElements (String tagName) {
        return route.getElementsByTagName(tagName);
    }
}
