package response;

import static org.junit.Assert.*;

import com.java_server.response.ResponseCodes;
import org.junit.Test;
/**
 * Created by Alex Codreanu on 11/20/14.
 */
public class ResponseCodesTest {
    @Test
    public void testGetReasonPhraseReturnsThePhraseForTheGivenCode() {
        assertEquals("OK", ResponseCodes.getReasonPhrase("200"));
        assertEquals("Not Found", ResponseCodes.getReasonPhrase("404"));
        assertEquals("I'm a teapot", ResponseCodes.getReasonPhrase("418"));
    }
}
