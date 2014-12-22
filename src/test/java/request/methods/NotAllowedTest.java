package request.methods;

import com.java_server.response.Response;
import com.java_server.request.methods.NotAllowed;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

/**
 * Created by Alex Codreanu on 12/1/14.
 */
public class NotAllowedTest {
    @Test
    public void testGetResponse_addsTheBodyForAValidRequestRoute() throws IOException {
        Response response = new NotAllowed().getResponse();
        String expectedResponse = "HTTP/1.1 405 Method Not Allowed\r\n\r\n";

        assertEquals(expectedResponse, new String(response.render()));
    }
}
