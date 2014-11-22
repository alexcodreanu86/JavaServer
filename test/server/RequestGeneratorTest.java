package server;

import com.java_server.Request.Request;
import com.java_server.Request.RequestGenerator;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

/**
 * Created by Alex Codreanu on 11/19/14.
 */
public class RequestGeneratorTest {

    @Test
    public void generatesARequest() {
        String req = "GET / HTTP/1.1\r\nHost: localhost:5000\r\nAccept: */*\r\nUser-Agent: Typhoeus - https://github.com/typhoeus/typhoeus\r\n\r\n";
        ByteArrayInputStream in = new ByteArrayInputStream(req.getBytes());
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            Request request = RequestGenerator.generate(reader);
            assert(request != null);
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void parseSetsMethodGetWhenTheMethodIsGet() {
        String req = "GET / HTTP/1.1\r\nHost: localhost:5000\r\nAccept: */*\r\nUser-Agent: Typhoeus - https://github.com/typhoeus/typhoeus\r\n\r\n";
        ByteArrayInputStream in = new ByteArrayInputStream(req.getBytes());
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        try {
            Request request = RequestGenerator.generate(reader);
            assertEquals("GET", request.getMethod());
        }

        catch (IOException e) { fail(e.toString());}
    }

    @Test
    public void parseSetsMethodPostWhenTheMethodIsPost() {
        String req = "POST / HTTP/1.1\r\nHost: localhost:5000\r\nAccept: */*\r\nUser-Agent: Typhoeus - https://github.com/typhoeus/typhoeus\r\n\r\n";
        ByteArrayInputStream in = new ByteArrayInputStream(req.getBytes());
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            Request request = RequestGenerator.generate(reader);
            assertEquals("POST", request.getMethod());
        }
        catch (IOException e) { fail(e.toString());}
    }

    @Test
    public void parseSetsUrlToTheGivenUrl() {
        String req = "POST /path/to/file HTTP/1.1\r\nHost: localhost:5000\r\nAccept: */*\r\nUser-Agent: Typhoeus - https://github.com/typhoeus/typhoeus\r\n\r\n";
        ByteArrayInputStream in = new ByteArrayInputStream(req.getBytes());
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            Request request = RequestGenerator.generate(reader);
            assertEquals("/path/to/file", request.getUrl());
        }
        catch (IOException e) { fail(e.toString());}
    }

    @Test
    public void parseSetsTheHeadersOfTheRequest() {
        String req = "POST / HTTP/1.1\r\nHost: localhost:5000\r\nAccept: */*\r\nUser-Agent: Typhoeus - https://github.com/typhoeus/typhoeus\r\n\r\n";
        ByteArrayInputStream in = new ByteArrayInputStream(req.getBytes());
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            Request request = RequestGenerator.generate(reader);
            Hashtable headers = request.getHeaders();
            assertEquals("localhost:5000", headers.get("Host"));
            assertEquals("*/*", headers.get("Accept"));
            assertEquals("Typhoeus - https://github.com/typhoeus/typhoeus", headers.get("User-Agent"));
        }
        catch (IOException e) { fail(e.toString());}
    }
}
