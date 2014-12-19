package parser;

import com.java_server.parser.ConfigParser;
import com.java_server.parser.ConfigParserFactory;
import org.junit.*;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Alex Codreanu on 12/18/14.
 */
public class ConfigParserFactoryTest {
    File defaultsFile, routesFile, resourcesDirectory;
    PrintWriter defaultsWriter;
    PrintWriter routesWriter;
    String path = "/cob_spec/public/";
    String port = "6000";

    @Before
    public void setupXMLFile() throws Exception {
        resourcesDirectory = createDirectory("testResources");
        defaultsFile = createFile(resourcesDirectory, "defaults.xml");
        routesFile = createFile(resourcesDirectory, "routes.xml");
        defaultsWriter = new PrintWriter(defaultsFile, "UTF-8");
        routesWriter = new PrintWriter(routesFile, "UTF-8");
        writeFiles();
    }

    @After
    public void deleteXMLFiles() {
        defaultsFile.delete();
        routesFile.delete();
        resourcesDirectory.delete();
    }

    @Test
    public void testCreatesNewConfigParser() throws Exception {
        TestFactory factory = new TestFactory(resourcesDirectory);

        ConfigParser parser = factory.generate();

        assertEquals(parser.getDefaultDirPath(), path);
        assertEquals(parser.getDefaultPort(), port);
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

    private File createDirectory(String dirPath) throws IOException {
        File dir = new File(dirPath);
        dir.mkdir();
        return dir;
    }
    private File createFile(File dir, String filePath) throws IOException {
        File file = new File(dir ,filePath);
        file.createNewFile();
        return file;
    }

    class TestFactory extends ConfigParserFactory {
        File resourcesDirectory;
        public TestFactory(File inResourcesDirectory) {
            resourcesDirectory = inResourcesDirectory;
        }

        @Override
        protected File getResourcesDirectory() {
            return resourcesDirectory;
        }
    }
}

