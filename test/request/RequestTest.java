package request;

import com.java_server.request.Request;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Hashtable;

/**
 * Created by Alex Codreanu on 11/19/14.
 */
public class RequestTest {
    @Test
    public void readerMethodsReturnProperValues() {
        String method = "POST";
        String url = "/some/url/here";
        String body = "requestBody=body";
        Hashtable headers = new Hashtable();
        Request request = new Request(method, url, body, headers);
        assertSame(method, request.getMethod());
        assertSame(url, request.getUrl());
        assertSame(headers, request.getHeaders());
        assertEquals("POST /some/url/here HTTP/1.1", request.getRequestLine());
    }

    @Test
    public void readsTheParametersFromARequestUrl() {
        String method = "GET";
        String url = "/some/url/here";
        String params = "?param1=test1&param2=test2";
        Hashtable headers = new Hashtable();
        Request request = new Request(method, url + params, "", headers);
        assertEquals(url, request.getUrl());
        assertEquals(request.getParam("param1"), "test1");
        assertEquals(request.getParam("param2"), "test2");
    }

    @Test
    public void decodesEncodedParams() {
        String method = "GET";
        String url = "/some/url/here";
        String params = "?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";
        Hashtable headers = new Hashtable();
        Request request = new Request(method, url + params, "", headers);
        assertEquals(url, request.getUrl());
        assertEquals(request.getParam("variable_1"), "Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?");
        assertEquals(request.getParam("variable_2"), "stuff");
    }
}
