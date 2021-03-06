package request.methods;

import com.java_server.request.Request;
import com.java_server.request.methods.RouteOptions;
import com.java_server.response.Response;
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
public class RouteOptionsTest {

    @Test
    public void testProcessRequestRespondsWithOptionsForTheRequestedRoute() {
        String url = "/method_options";
        String body = "requestBody=body";
        Request request = newRequest(url, body);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        RouteOptions optionsRouter = new RouteOptions();
        String expectedResponse = "HTTP/1.1 200 OK\r\n\r\n";
        try {
            Response response = optionsRouter.getResponse();
            assert(Arrays.equals(expectedResponse.getBytes(), response.render()));
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

