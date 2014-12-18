package utils;

import com.java_server.utils.ConfigXMLParser;
import com.java_server.utils.XMLRouteWrapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Created by Alex Codreanu on 12/16/14.
 */
public class XMLRouteWrapperProdTest {
    File file;
    PrintWriter writer;
    String path = "/cob_spec/public/";
    String port = "6000";
    XMLRouteWrapper[] wrappers;

    @Before
    public void setupXMLFile() throws Exception {
        file = createFile("test.xml");
        writer = new PrintWriter(file, "UTF-8");
        writeFile(writer);

        ConfigXMLParser parser = new ConfigXMLParser(file);
        wrappers = parser.getRoutes();
    }

    @After
    public void deleteXMLFile() {
        file.delete();
    }

    @Test
    public void getRoutePath_testItReturnsThePath() throws Exception {
        XMLRouteWrapper wrapper = wrappers[0];

        assertEquals("/logs", wrapper.getPath());
    }


    @Test
    public void requiresAuth_returnsTrueWhenRouteHasAuthTagTrue() throws Exception {
        XMLRouteWrapper logsWrapper = wrappers[0];

        assert(logsWrapper.requiresAuth());
    }


    @Test
    public void requiresAuth_returnsFalseWhenRouteHasNoAuthTag() throws Exception {
        XMLRouteWrapper wrapper = wrappers[1];

        assertFalse(wrapper.requiresAuth());
    }

    @Test
    public void requiresAuth_returnsFalseWhenRouteAuthTagValueIsNotTrue() throws Exception {
        XMLRouteWrapper wrapper = wrappers[2];

        assertFalse(wrapper.requiresAuth());
    }

    @Test
    public void getMethods_returnsTheMethodsOfTheGivenRoute() throws Exception {
        XMLRouteWrapper wrapper = wrappers[1];
        String[] expectedMethods = new String[] { "GET", "POST" };

        assert(Arrays.equals(expectedMethods, wrapper.getMethods()));
    }

    private void writeFile(PrintWriter writer) {
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        writer.write("<config>\n<defaults>\n");
        writer.write("<dirPath>" + path + "</dirPath>\n");
        writer.write("<port>" + port + "</port>\n");
        writer.write("</defaults>\n");
        writer.write("<routes>\n");
        writer.write("<route><path>/logs</path><method>GET</method><auth>true</auth></route>\n");
        writer.write("<route><path>/test</path><method>GET</method><method>POST</method></route>\n");
        writer.write("<route><path>/invalid</path><method>GET</method><method>POST</method><auth>false</auth></route>\n");
        writer.write("</routes>");
        writer.write("</config>");
        writer.close();
    }

    private File createFile(String filePath) throws IOException {
        File file = new File(filePath);
        file.createNewFile();
        return file;
    }
}
