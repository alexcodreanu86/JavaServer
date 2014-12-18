package response;

import static org.junit.Assert.*;

import com.java_server.response.Response;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Alex Codreanu on 11/20/14.
 */
public class ResponseTest {
    @Test
    public void testReturnsResponseLine() {
        Response response = new Response("200", "OK");
        assertEquals("HTTP/1.1 200 OK", response.getResponseLine());
    }

    @Test
    public void addBodyAddsTheBodyToTheResponse() {
        Response response = new Response("200", "OK");
        String testBody = "date=today";
        response.addToBody(testBody);
        assert(Arrays.equals(response.getBody(), testBody.getBytes()));
    }

    @Test
    public void addBodyAddsLineSeparatorIfCurrentBodyHasContent() {
        Response response = new Response("200", "OK");
        String testBody = "date=today";
        response.addToBody(testBody);
        response.addToBody(testBody);
        byte[] expectedBody = (testBody + "\r\n" + testBody).getBytes();
        assert(Arrays.equals(expectedBody, response.getBody()));
    }

    @Test
    public void addBodyAddsByteContentsToBody() {
        Response response = new Response("200", "OK");
        String testBody = "date=today";
        response.addToBody(testBody.getBytes());
        response.addToBody(testBody.getBytes());
        byte[] expectedBody = (testBody + "\r\n" + testBody).getBytes();
        assert(Arrays.equals(expectedBody, response.getBody()));
    }
    @Test
    public void renderIsRenderingTheFullResponse() {
        Response response = new Response("200", "OK");
        String testBody = "date=today";
        response.addHeader("Allow", "GET");
        response.addHeader("Test", "Testing");
        response.addToBody(testBody);
        String expectedResponse = "HTTP/1.1 200 OK\r\n" +
                                  "Allow: GET\r\n" + "" +
                                  "Test: Testing" +
                                  "\r\n\r\n" +
                                  "date=today";
        assert(Arrays.equals(expectedResponse.getBytes(), response.render()));
    }
}
