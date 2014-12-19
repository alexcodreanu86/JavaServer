package args;

import com.java_server.args.GlobalArguments;
import com.java_server.parser.ConfigParser;
import org.junit.Before;
import org.junit.Test;
import mocks.MockConfigParser;

import static org.junit.Assert.*;

/**
 * Created by Alex Codreanu on 11/23/14.
 */
public class GlobalArgumentsTest {
    String xmlPath, xmlPort;
    ConfigParser parser;

    @Before
    public void setupConfigParser() {
        xmlPath =  "/config";
        xmlPort = "9898";
        parser = new MockConfigParser(xmlPath, xmlPort);
    }

    @Test
    public void testSetArgs_setsTheGlobalArgumentsToDefaultsWhenNoValidArgsAreProvided(){
        GlobalArguments.setArgs(new String[0], parser);
        assertEquals(Integer.parseInt(xmlPort), GlobalArguments.getPort());
        assertEquals(System.getProperty("user.dir") + xmlPath, GlobalArguments.getRootDirectory());
    }

    @Test
    public void testSetArgs_setsThePortToTheGivenPort() {
        GlobalArguments.setArgs(new String[]{"-p", "3000"}, parser);
        assertEquals(3000, GlobalArguments.getPort());
    }

    @Test
    public void testSetArgs_setsTheRootDirectoryToTheGivenPath() {
        GlobalArguments.setArgs(new String[]{"-d", "/test/path/here"}, parser);
        assertEquals("/test/path/here", GlobalArguments.getRootDirectory());
    }
}
