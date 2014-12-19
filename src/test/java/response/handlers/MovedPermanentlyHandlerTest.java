package response.handlers;

import com.java_server.response.Response;
import com.java_server.response.handlers.MovedPermanentlyHandler;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Alex Codreanu on 11/27/14.
 */
public class MovedPermanentlyHandlerTest {
    @Test
    public void testGetResponse_returnsRedirectResponseWhenRequestRouteIsredirect() throws IOException {
        String redirectPath = "/testRedirect";

        Response response = new Response("301", "Moved Permanently");
        new MovedPermanentlyHandler(response, redirectPath).populateResponse();
        String expectedResponse = "HTTP/1.1 301 Moved Permanently\r\n" +
                "Location: http://localhost:5000" + redirectPath +
                "\r\n\r\n";

        assert(Arrays.equals(expectedResponse.getBytes(), response.render()));
    }
}
