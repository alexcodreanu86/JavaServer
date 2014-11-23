package data_storage;

import com.java_server.data_storage.FilesData;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Alex Codreanu on 11/23/14.
 */
public class FilesDataTest {
    @Test
    public void testAddFileWithData_addsTheGivenFileNameWithDataAsAString() {
        String data = "test_data";
        String fileName = "/testFile";
        FilesData.addFileWithData(fileName, data);
        assert(Arrays.equals(FilesData.getFileData(fileName), data.getBytes()));
    }

    @Test
    public void testAddFileWithData_addsTheGivenFileNameWithDataAsABytes() {
        byte[] data = "test_data".getBytes();
        String fileName = "/testFile";
        FilesData.addFileWithData(fileName, data);
        assert(Arrays.equals(FilesData.getFileData(fileName), data));
    }
}
