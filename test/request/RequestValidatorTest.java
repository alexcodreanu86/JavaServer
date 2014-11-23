package request;

import com.java_server.request.Request;
import com.java_server.request.RequestValidator;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Hashtable;

/**
 * Created by Alex Codreanu on 11/21/14.
 */
public class RequestValidatorTest {
    @Test
    public void testIsValidRequestReturnsTrueWhenRequestIsValid() {
        String url = "/method_options";
        String body = "";
        Request request = newRequest(url, body, "GET");
        RequestValidator validator = new RequestValidator(request);
        assertTrue(validator.isValidRequest());
    }

    @Test
    public void testIsValidRequestReturnsFalseWhenRequestHasInvalidUrl() {
        String url = "/invalid_url";
        String body = "";
        Request request = newRequest(url, body, "GET");
        RequestValidator validator = new RequestValidator(request);
        assertFalse(validator.isValidRequest());
    }

    @Test
    public void testIsValidRequestReturnsFalseWhenRequestHasInvalidMethodForTheUrl() {
        String url = "/method_options";
        String body = "requestBody=body";
        Request request = newRequest(url, body, "DELETE");
        RequestValidator validator = new RequestValidator(request);
        assertFalse(validator.isValidMethod());
    }

    private Request newRequest(String url, String body,String method) {
        Hashtable headers = new Hashtable();
        return new Request(method, url, body, headers);
    }
}
