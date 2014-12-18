package utils;

import com.java_server.utils.ServerXMLParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alex Codreanu on 12/18/14.
 */
public class ServerXMLParserTest {
    File file;
    PrintWriter writer;

    @Before
    public void setupXMLFile() throws Exception {
        file = createFile("test.xml");
        writer = new PrintWriter(file, "UTF-8");
    }

    @After
    public void deleteXMLFile() {
        file.delete();
    }

    @Test
    public void getElements_testItReturnsElementsInTheXMLFile() throws Exception {
        writeRoutes(writer);
        ServerXMLParser parser = new ServerXMLParser(file);
        NodeList routes = parser.getElements("route");
        Element route1 = (Element) routes.item(0);
        Element route2 = (Element) routes.item(1);

        assertEquals("/logs", getElementValue(route1, "path"));
        assertEquals("/test", getElementValue(route2, "path"));
    }

    private String getElementValue(Element el, String propertyName) {
        Node property = el.getElementsByTagName(propertyName).item(0);
        return property.getTextContent();
    }

    private void writeRoutes(PrintWriter writer) {
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        writer.write("<routes>\n");
        writer.write("<route><path>/logs</path><method>GET</method><auth>true</auth></route>\n");
        writer.write("<route><path>/test</path><method>GET</method><method>POST</method></route>\n");
        writer.write("</routes>");
        writer.close();
    }

    private File createFile(String filePath) throws IOException {
        File file = new File(filePath);
        file.createNewFile();
        return file;
    }
}

