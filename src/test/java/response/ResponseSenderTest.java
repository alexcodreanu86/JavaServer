package response;

import static org.junit.Assert.*;
import org.junit.Test;

import com.java_server.response.Response;
import com.java_server.response.ResponseSender;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Alex Codreanu on 11/20/14.
 */
public class ResponseSenderTest {
    @Test
    public void testSendSendsTheDataRenderedByTheGivenResponse() throws IOException {
         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
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
        ResponseSender sender = new ResponseSender(response, outputStream);

        sender.send();
        assertEquals(expectedResponse, outputStream.toString());
    }
}
