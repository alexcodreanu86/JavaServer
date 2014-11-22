package routing;

import static org.junit.Assert.*;

import com.java_server.Request.Request;
import com.java_server.response.Response;
import com.java_server.routing.POST;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

/**
 * Created by Alex Codreanu on 11/20/14.
 */
public class POSTTest {
    @Test
    public void processRequestSendsASucessfullResponseOnRouteForms() {
        String url = "/form";
        String body = "requestBody=body";
        Request request = newRequest(url, body);
        POST postRouter = new POST(request);
        String expectedResponse = "HTTP/1.1 200 OK";
        try {
            Response response = postRouter.getResponse();
            assertEquals(expectedResponse, response.getResponseLine());
        }
        catch (IOException e) {
            fail(e.toString());
        }
    }

    public void processRequestSendsA404IfInvalidRoute() {
        String url = "/random/route/here";
        String body = "requestBody=body";
        Request request = newRequest(url, body);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        POST postRouter = new POST(request);
        String expectedResponse = "HTTP/1.1 404 Not Found\r\n\r\n";
        try {
            postRouter.getResponse();
            assertEquals(outputStream.toString(), expectedResponse);
        }
        catch (IOException e) {
            fail(e.toString());
        }
    }

    private Request newRequest(String url, String body) {
        String method = "POST";
        Hashtable headers = new Hashtable();
        return new Request(method, url, body, headers);
    }

    private DataOutputStream newOutStream(OutputStream outputStream) {
        return new DataOutputStream(outputStream);
    }
}
