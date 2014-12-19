package parser;

import com.java_server.parser.ConfigXMLParser;
import com.java_server.parser.XMLRouteWrapper;
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
    File defaultsFile;
    File routesFile;
    PrintWriter defaultsWriter;
    PrintWriter routesWriter;
    String path = "/cob_spec/public/";
    String port = "6000";

    @Before
    public void setupXMLFile() throws Exception {
        defaultsFile = createFile("defaults.xml");
        routesFile = createFile("routes.xml");
        defaultsWriter = new PrintWriter(defaultsFile, "UTF-8");
        routesWriter = new PrintWriter(routesFile, "UTF-8");
        writeFiles();
    }

    @After
    public void deleteXMLFiles() {
        defaultsFile.delete();
        routesFile.delete();
    }

    @Test
    public void getDirPath_testItReturnsTheDirPathInTheXMLFile() throws Exception {
        ConfigXMLParser parser = new ConfigXMLParser(defaultsFile, routesFile);
        assertEquals(path, parser.getDefaultDirPath());
    }

    @Test
    public void getPort_testItReturnsThePortInTheXMLFile() throws Exception {
        ConfigXMLParser parser = new ConfigXMLParser(defaultsFile, routesFile);
        assertEquals(port, parser.getDefaultPort());
    }

    @Test
    public void getRoutes_returnsAnArrayOfRouteWrappers() throws Exception {

        ConfigXMLParser parser = new ConfigXMLParser(defaultsFile, routesFile);
        XMLRouteWrapper[] routes = parser.getRoutes();

        assertEquals("/logs", routes[0].getPath());
        assertEquals("/test", routes[1].getPath());
    }

    private void writeFiles() {
        writeDefaults(defaultsWriter);
        writeRoutes(routesWriter);
    }

    private void writeDefaults(PrintWriter writer) {
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        writer.write("\n<defaults>\n");
        writer.write("<dirPath>" + path + "</dirPath>\n");
        writer.write("<port>" + port + "</port>\n");
        writer.write("</defaults>\n");
        writer.close();
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
