package server;

import com.java_server.Request.Request;
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
    }
}
