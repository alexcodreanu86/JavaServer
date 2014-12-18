package args;

import com.java_server.args.ArgumentProcessor;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Alex Codreanu on 11/18/14.
 */
public class ArgumentProcessorTest {
    @Test
    public void getPortReturnsTheDefaultValueWhenArgumentNotFound() {
        ArgumentProcessor processor = new ArgumentProcessor(new String[0], "5000", "");
        assertEquals(5000, processor.getPort());
    }
    @Test
    public void getPortReturnsThePortWhenTheArgumentIsFirst() {
        ArgumentProcessor processor = new ArgumentProcessor(new String[]{"-p", "3000"}, "5000", "");
        assertEquals(3000, processor.getPort());
    }

    @Test
    public void getPortReturnsThePortWhenThePortArgumentIsLast() {
        ArgumentProcessor processor = new ArgumentProcessor(new String[]{"-d", "/some/directory", "-p", "4000"}, "5000", "");
        assertEquals(4000, processor.getPort());
    }

    @Test
    public void getDirectoryPathReturnsTheDefaultValueWhenArgumentNotFound() {
        String defaultPath = "/default/path";
        ArgumentProcessor processor = new ArgumentProcessor(new String[0], "5000", defaultPath);
        assertEquals(defaultPath, processor.getDirectoryPath());
    }

    @Test
    public void getDirectoryPathReturnsThePathWhenTheArgumentIsFirst() {
        String path = "/some/directory";
        ArgumentProcessor processor = new ArgumentProcessor(new String[]{"-d", path, "-p", "4000"}, "5000", "");
        assertEquals(path, processor.getDirectoryPath());
    }

    @Test
    public void getDirectoryPathReturnsThePathWhenTheArgumentIsLast() {
        String path = "/some/directory";
        ArgumentProcessor processor = new ArgumentProcessor(new String[]{"-p", "4000", "-d", path }, "5000", "");
        assertEquals(path, processor.getDirectoryPath());
    }
}
