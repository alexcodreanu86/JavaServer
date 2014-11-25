package routing;

import com.java_server.args.GlobalArguments;
import com.java_server.routing.RoutesDispatcher;
import com.java_server.routing.RoutesGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Alex Codreanu on 11/23/14.
 */
public class RoutesGeneratorTest {
    String directoryPath = "mockDirectory";
    String filePath1 = "/testFile1";
    File file;

    @Before
    public void setup() throws IOException{
        file = createFileWithText(directoryPath + filePath1, "");
        GlobalArguments.setArgs(new String[] {"-d", directoryPath});
    }

    @After
    public void tearDown() {
        deleteDirectory(file.getParentFile());
    }

    @Test
    public void testGenerate_generatesTheDirectoryContentsRoutes() throws IOException{
        RoutesGenerator.generate();
        String[] expectedMethods = new String[] {"GET"};
        String[] routeMethods = RoutesDispatcher.getRoute(filePath1).getMethods();

        assert(Arrays.equals(expectedMethods, routeMethods));

    }

    @Test
    public void testGenerate_generatesTheMethodOptionsRoute() throws IOException{
        RoutesGenerator.generate();
        String[] expectedMethods = new String[] {"GET", "HEAD", "POST", "OPTIONS", "PUT"};
        String[] routeMethods = RoutesDispatcher.getRoute("/method_options").getMethods();

        assert(Arrays.equals(expectedMethods, routeMethods));
    }

    @Test
    public void testGenerate_generatesTheFormRoute() throws IOException{
        RoutesGenerator.generate();
        String[] expectedMethods = new String[] {"GET", "POST", "PUT", "DELETE"};
        String[] routeMethods = RoutesDispatcher.getRoute("/form").getMethods();

        assert(Arrays.equals(expectedMethods, routeMethods));
    }

    @Test
    public void testGenerate_generatesTheRedirectRoute() throws IOException{
        RoutesGenerator.generate();
        String[] expectedMethods = new String[] {"GET"};
        String[] routeMethods = RoutesDispatcher.getRoute("/redirect").getMethods();

        assert(Arrays.equals(expectedMethods, routeMethods));
    }

    @Test
    public void testGenerate_generatesParamsRoute() throws IOException{
        RoutesGenerator.generate();
        String[] expectedMethods = new String[] {"GET"};
        String[] routeMethods = RoutesDispatcher.getRoute("/parameters").getMethods();

        assert(Arrays.equals(expectedMethods, routeMethods));
    }
    private File createFileWithText(String filePath, String text) throws IOException {
        File file = createFile(filePath);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
        writer.write(text);
        writer.close();
        return file;
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
