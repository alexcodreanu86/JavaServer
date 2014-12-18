package args;

import com.java_server.args.GlobalArguments;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Alex Codreanu on 11/23/14.
 */
public class GlobalArgumentsTest {
    @Test
    public void testSetArgs_setsTheGlobalArgumentsToDefaultsWhenNoValidArgsAreProvided(){
        GlobalArguments.setArgs(new String[0]);
        assertEquals(5000, GlobalArguments.getPort());
        assertEquals("/Users/alextsveta2012/8thLight/server/cob_spec/public/", GlobalArguments.getRootDirectory());
    }

    @Test
    public void testSetArgs_setsThePortToTheGivenPort() {
        GlobalArguments.setArgs(new String[]{"-p", "3000"});
        assertEquals(3000, GlobalArguments.getPort());
    }

    @Test
    public void testSetArgs_setsTheRootDirectoryToTheGivenPath() {
        GlobalArguments.setArgs(new String[]{"-d", "/test/path/here"});
        assertEquals("/test/path/here", GlobalArguments.getRootDirectory());
    }
}
