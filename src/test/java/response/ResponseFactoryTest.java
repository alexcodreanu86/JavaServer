package response;

import com.java_server.response.ResponseFactory;
import com.java_server.response.Response;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Alex Codreanu on 11/23/14.
 */
public class ResponseFactoryTest {
    @Test
    public void testMethodNotAllowed_returnsA405Response() {
        Response response = ResponseFactory.MethodNotAllowed();
        assertEquals(response.getResponseLine(), "HTTP/1.1 405 Method Not Allowed");
    }

    @Test
    public void testNotFound() {
        Response response = ResponseFactory.NotFound();
        assertEquals(response.getResponseLine(), "HTTP/1.1 404 Not Found");
    }

    @Test
    public void testUnauthorized() {
        Response response = ResponseFactory.Unauthorized();
        assertEquals(response.getResponseLine(), "HTTP/1.1 401 Unauthorized");
    }
    @Test
    public void testOK() {
        Response response = ResponseFactory.OK();
        assertEquals(response.getResponseLine(), "HTTP/1.1 200 OK");
    }

    @Test
    public void testNoContent() {
        Response response = ResponseFactory.NoContent();
        assertEquals(response.getResponseLine(), "HTTP/1.1 204 No Content");
    }

    @Test
    public void partialContent() {
        Response response = ResponseFactory.PartialContent();
        assertEquals(response.getResponseLine(), "HTTP/1.1 206 Partial Content");
    }
    @Test
    public void testMovedPermanently() {
        Response response = ResponseFactory.MovedPermanently();
        assertEquals(response.getResponseLine(), "HTTP/1.1 301 Moved Permanently");
    }

    @Test
    public void found() {
        Response response = ResponseFactory.Found();
        assertEquals(response.getResponseLine(), "HTTP/1.1 302 Found");
    }
}
