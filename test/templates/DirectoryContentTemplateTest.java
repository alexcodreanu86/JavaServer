package templates;

import com.java_server.templates.DirectoryContentTemplate;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by Alex Codreanu on 11/23/14.
 */
public class DirectoryContentTemplateTest {
    String directoryPath = "mockDirectory";
    String filePath1 = "/testFile1";
    String filePath2 = "/testFile2";

    @Test
    public void testRender_rendersLinksForEachFileName() throws IOException {
        File file = createFile(directoryPath + filePath1);
        File directory = file.getParentFile();
        createFile(directoryPath + filePath2);

        String expectedResponse = "<ul>" +
                                  "<li><a href='" + filePath1 + "'>" + filePath1 + "</a></li>" +
                                  "<li><a href='" + filePath2 + "'>" + filePath2 + "</a></li>" +
                                  "</ul>";
        String renderedDirectory = new DirectoryContentTemplate(directory).render();

        assertEquals(expectedResponse, renderedDirectory);

        deleteDirectory(directory);
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
