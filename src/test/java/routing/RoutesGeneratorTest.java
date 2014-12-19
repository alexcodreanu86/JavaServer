package routing;

import com.java_server.args.GlobalArguments;
import com.java_server.routing.Route;
import com.java_server.routing.RoutesDispatcher;
import com.java_server.routing.RoutesGenerator;
import com.java_server.parser.ConfigParser;
import com.java_server.parser.XMLRouteWrapper;
import mocks.MockXMLRouteWrapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import mocks.MockConfigParser;

import static org.junit.Assert.*;

import java.io.*;
import java.util.Arrays;

/**
 * Created by Alex Codreanu on 11/23/14.
 */
public class RoutesGeneratorTest {
    String directoryPath = "mockDirectory";
    String filePath = "/testFile";
    File file;
    ConfigParser parser;

    @Before
    public void setup() throws IOException{
        file = createFile(directoryPath + filePath);
        parser = new MockConfigParser("mockPath", "9090");
        GlobalArguments.setArgs(new String[] {"-d", directoryPath}, parser);
    }

    @After
    public void tearDown() {
        deleteDirectory(file.getParentFile());
    }

    @Test
    public void testGenerate_generatesTheDirectoryContentsRoutes() throws IOException{
        RoutesGenerator.generate(parser);
        String[] expectedMethods = new String[] {"GET", "PATCH"};
        String[] routeMethods = RoutesDispatcher.getRoute(filePath).getMethods();

        assert(Arrays.equals(expectedMethods, routeMethods));
    }

    @Test
    public void testAddsRoutesGivenByTheXMLParser() throws Exception {
        String[] route1Methods = new String[] { "GET", "POST" };
        String[] route2Methods = new String[] { "GET", "DELETE" };
        XMLRouteWrapper wrapper1 = new MockXMLRouteWrapper("/route1", true, route1Methods);
        XMLRouteWrapper wrapper2 = new MockXMLRouteWrapper("/route2", false, route2Methods);

        ConfigParser parser = new MockConfigParser("", "6000", new XMLRouteWrapper[] { wrapper1, wrapper2 });
        RoutesGenerator.generate(parser);

        Route route1 = RoutesDispatcher.getRoute("/route1");
        Route route2 = RoutesDispatcher.getRoute("/route2");

        assert(Arrays.equals(route1Methods, route1.getMethods()));
        assert(route1.requiresAuthentication());

        assert(Arrays.equals(route2Methods, route2.getMethods()));
        assertFalse(route2.requiresAuthentication());
    }


    private File createFile(String filePath) throws IOException {
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (!parent.exists()) {
            file.getParentFile().mkdir();
        }
        file.createNewFile();
        return file;
    }

    public static boolean deleteDirectory(File directory) {
        if(directory.exists()){
            File[] files = directory.listFiles();
            if(null!=files){
                for(int i=0; i<files.length; i++) {
                    if(files[i].isDirectory()) {
                        deleteDirectory(files[i]);
                    }
                    else {
                        files[i].delete();
                    }
                }
            }
        }
        return(directory.delete());
    }
}
