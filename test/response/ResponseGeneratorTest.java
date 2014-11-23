package response;

import com.java_server.request.Request;
import com.java_server.response.Response;
import com.java_server.response.ResponseGenerator;
import com.java_server.routing.Route;
import com.java_server.routing.RoutesDispatcher;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * Created by Alex Codreanu on 11/21/14.
 */
public class ResponseGeneratorTest {
    @Test
    public void generateReponse_generatesAnInvalidResponseWhenUrlIsInvalid()
            throws IOException {
        String url = "/random/route/here";
        String body = "requestBody=body";
        Request request = newRequest(url, body, "POST");
        Response response = ResponseGenerator.generate(request);

        assertEquals("HTTP/1.1 404 Not Found", response.getResponseLine());
    }

    @Test
    public void generateReponse_generatesAnInvalidResponseWhenMethodIsNotAvailableForTheUrl()
            throws IOException {
        String url = "/method_options";
        String body = "requestBody=body";
        Request request = newRequest(url, body, "DELETE");
        Response response = ResponseGenerator.generate(request);

        assertEquals("HTTP/1.1 405 Method Not Allowed", response.getResponseLine());
    }

    @Test
    public void generateReponse_generatesAValidResponseWhenTheRequestIsValid()
            throws IOException {
        String url = "/method_options";
        Route route = new Route(url, new String[] {"GET", "HEAD", "POST", "OPTIONS", "PUT"}, new byte[0]);
        RoutesDispatcher.addRoute(route);
        Request request = newRequest(url, "", "OPTIONS");
        Response response = ResponseGenerator.generate(request);
        String expectedResponse = "HTTP/1.1 200 OK\r\n" +
                "Allow: GET,HEAD,POST,OPTIONS,PUT" +
                "\r\n\r\n";

        assert(Arrays.equals(expectedResponse.getBytes(), response.render()));
    }


    private Request newRequest(String url, String body, String method) {
        Hashtable headers = new Hashtable();
        return new Request(method, url, body, headers);
    }
}
