package request.methods;

import com.java_server.request.Request;
import com.java_server.response.Response;
import com.java_server.request.methods.NotAllowed;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by Alex Codreanu on 12/1/14.
 */
public class NotAllowedTest {
    @Test
    public void testGetResponse_addsTheBodyForAValidRequestRoute() throws IOException {
        Response response = new NotAllowed(newRequest("/")).getResponse();
        String expectedResponse = "HTTP/1.1 405 Method Not Allowed\r\n\r\n";

        assertEquals(expectedResponse, new String(response.render()));
    }

    private Request newRequest(String url) {
        return newRequest(url, new Hashtable());
    }

    private Request newRequest(String url, Hashtable headers) {
        String method = "GET";
        return new Request(method, url, "", headers);
    }


}
