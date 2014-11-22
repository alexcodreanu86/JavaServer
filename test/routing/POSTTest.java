package routing;

import static org.junit.Assert.*;

import com.java_server.request.Request;
import com.java_server.response.Response;
import com.java_server.routing.POST;
import org.junit.Test;

import java.io.IOException;
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
            assertEquals(body, response.getBody());
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
}
