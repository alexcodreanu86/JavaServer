package routing;

import com.java_server.routing.DirectoryRoutesLoader;
import com.java_server.routing.RoutesDispatcher;
import com.java_server.templates.DirectoryContentTemplate;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Alex Codreanu on 11/23/14.
 */
public class DirectoryRoutesLoaderTest {
    String directoryPath = "mockDirectory";
    String filePath1 = "/testFile1";
    String filePath2 = "/testFile2";

    @Test
    public void testLoadDirectoryContents_addsTheDirectoryContentsToFilesData() throws IOException {
        String text1 = "Testing loadDirectory1";
        String text2 = "Testing loadDirectory2";
        File file = createFileWithText(directoryPath + filePath1, text1);
        createFileWithText(directoryPath + filePath2, text2);

        DirectoryRoutesLoader.loadDirectoryContents(file.getParentFile());

        assert(Arrays.equals(RoutesDispatcher.getRoute(filePath1).getData(), text1.getBytes()));
        assert(Arrays.equals(RoutesDispatcher.getRoute(filePath2).getData(), text2.getBytes()));

        deleteDirectory(new File(directoryPath));
    }

    @Test
    public void testLoadDirectoryContents_addsTheDirectoryStructureToRootFileData() throws IOException {
        String text1 = "Testing loadDirectory1";
        String text2 = "Testing loadDirectory2";
        File file = createFileWithText(directoryPath + filePath1, text1);
        createFileWithText(directoryPath + filePath2, text2);

        DirectoryRoutesLoader.loadDirectoryContents(file.getParentFile());
        String directoryTemplate = new DirectoryContentTemplate(file.getParentFile()).render();

        assert(Arrays.equals(directoryTemplate.getBytes(), RoutesDispatcher.getRoute("/").getData()));
        assert(Arrays.equals(new String[] {"GET"}, RoutesDispatcher.getRoute("/").getMethods()));

        deleteDirectory(new File(directoryPath));
    }

    @Test
    public void testLoadDirectoryContents_addsTheDirectoryContentsToRoutesDispatcher() throws IOException {
        String text1 = "Testing loadDirectory1";
        String text2 = "Testing loadDirectory2";
        File file = createFileWithText(directoryPath + filePath1, text1);
        createFileWithText(directoryPath + filePath2, text2);

        DirectoryRoutesLoader.loadDirectoryContents(file.getParentFile());

        assert(Arrays.equals(new String[] {"GET"}, RoutesDispatcher.getRoute(filePath1).getMethods()));
        assert(Arrays.equals(new String[] {"GET"}, RoutesDispatcher.getRoute(filePath2).getMethods()));

        deleteDirectory(new File(directoryPath));
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
