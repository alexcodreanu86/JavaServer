package utils;

import com.java_server.utils.ArrayJoiner;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Alex Codreanu on 11/23/14.
 */
public class ArrayJoinerTest {
    @Test
    public void testJoin_joinsTwoByteArrays() {
        String text1 = "test text 1";
        String text2 = "test text 2";
        byte[] textByte1 = text1.getBytes();
        byte[] textByte2 = text2.getBytes();
        byte[] expectedResult = (text1 + text2).getBytes();

        assert(Arrays.equals(expectedResult, ArrayJoiner.join(textByte1, textByte2)));
    }
}
