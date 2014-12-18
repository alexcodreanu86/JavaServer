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

/**
 * Created by Alex Codreanu on 12/16/14.
 */
public class ConfigXMLParserTest {
    File file;
    PrintWriter writer;
    String path = "/cob_spec/public/";
    String port = "6000";

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
    public void getDirPath_testItReturnsTheDirPathInTheXMLFile() throws Exception {
        writeFileWithDefaults(writer);
        writeFileClose(writer);
        ConfigXMLParser parser = new ConfigXMLParser(file);
        assertEquals(path, parser.getDefaultDirPath());
    }

    @Test
    public void getPort_testItReturnsThePortInTheXMLFile() throws Exception {
        writeFileWithDefaults(writer);
        writeFileClose(writer);
        ConfigXMLParser parser = new ConfigXMLParser(file);
        assertEquals(port, parser.getDefaultPort());
    }

    @Test
    public void getRoutes_returnsAnArrayOfRouteWrappers() throws Exception {
        writeFileWithDefaults(writer);
        writeRoutes(writer);
        writeFileClose(writer);

        ConfigXMLParser parser = new ConfigXMLParser(file);
        XMLRouteWrapper[] routes = parser.getRoutes();

        assertEquals("/logs", routes[0].getPath());
        assertEquals("/test", routes[1].getPath());
    }



    private void writeFileClose(PrintWriter writer) {
        writer.write("</config>");
        writer.close();
    }
    private void writeFileWithDefaults(PrintWriter writer) {
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        writer.write("<config>\n<defaults>\n");
        writer.write("<dirPath>" + path + "</dirPath>\n");
        writer.write("<port>" + port + "</port>\n");
        writer.write("</defaults>\n");
    }

    private void writeRoutes(PrintWriter writer) {
        writer.write("<routes>\n");
        writer.write("<route><path>/logs</path><method>GET</method><auth>true</auth></route>\n");
        writer.write("<route><path>/test</path><method>GET</method><method>POST</method></route>\n");
        writer.write("</routes>");
    }

    private File createFile(String filePath) throws IOException {
        File file = new File(filePath);
        file.createNewFile();
        return file;
    }
}
