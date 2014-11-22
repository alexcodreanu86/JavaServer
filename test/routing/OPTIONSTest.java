package routing;

import com.java_server.Request.Request;
import com.java_server.routing.OPTIONS;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;

import static org.junit.Assert.*;

/**
 * Created by Alex Codreanu on 11/21/14.
 */
public class OPTIONSTest {
    @Test
    public void testGetUrlOptions() {
        String url = "/method_options";
        String body = "requestBody=body";
        Request request = newRequest(url, body);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        OPTIONS optionsRouter = new OPTIONS(request, newOutStream(outputStream));
        String[] expectedResponse = new String[]{"GET", "HEAD", "POST", "OPTIONS", "PUT"};
        assert(Arrays.equals(expectedResponse, optionsRouter.getRouteOptions()));
    }

    @Test
    public void testProcessRequestReturnsInvalidRequestWhenRouteDoesntExist() {
        String url = "/invalid_url/here";
        String body = "requestBody=body";
        Request request = newRequest(url, body);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        OPTIONS optionsRouter = new OPTIONS(request, newOutStream(outputStream));
        try {
            optionsRouter.processRequest();
            assertEquals("HTTP/1.1 404 Not Found\r\n\r\n",outputStream.toString());
        }
        catch (IOException e) {
            fail(e.toString());
        }
    }

    @Test
    public void testProcessRequestRespondsWithOptionsForTheRequestedRoute() {
        String url = "/method_options";
        String body = "requestBody=body";
        Request request = newRequest(url, body);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        OPTIONS optionsRouter = new OPTIONS(request, newOutStream(outputStream));
        String expectedResponse = "HTTP/1.1 200 OK\r\n" +
                                  "Allow: GET, HEAD, POST, OPTIONS, PUT" +
                                  "\r\n\r\n";
        try {
            optionsRouter.processRequest();
            assertEquals(expectedResponse, outputStream.toString());
        }
        catch (IOException e) {
            fail(e.toString());
        }
    }

    private Request newRequest(String url, String body) {
        String method = "OPTIONS";
        Hashtable headers = new Hashtable();
        return new Request(method, url, body, headers);
    }

    private DataOutputStream newOutStream(ByteArrayOutputStream outputStream) {
        return new DataOutputStream(outputStream);
    }
}

