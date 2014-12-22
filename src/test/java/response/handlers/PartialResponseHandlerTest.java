package response.handlers;

import com.java_server.response.Response;
import com.java_server.response.ResponseFactory;
import com.java_server.response.handlers.PartialContentHandler;
import com.java_server.routing.Route;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Alex Codreanu on 11/27/14.
 */
public class PartialResponseHandlerTest {

    @Test
    public void testGetResponse_returnsPartialContentWhenRangeHeaderExists() throws IOException {
        String routePath = "/route";
        Route route = new Route(routePath, new String[] {"GET"}, "some data in here".getBytes());

        Response response = ResponseFactory.PartialContent();
        new PartialContentHandler("bytes=0-4", response, route).populateResponse();

        assert(Arrays.equals("some ".getBytes(), response.getBody()));
    }

    @Test
    public void testGetResponse_returnsPartialContentWhenRangeHeaderHasOnlyEndOfRange() throws IOException {
        String routePath = "/route";
        Route route = new Route(routePath, new String[] {"GET"}, "some data in here".getBytes());

        Response response = ResponseFactory.PartialContent();
        new PartialContentHandler("bytes=-4", response, route).populateResponse();

        assert(Arrays.equals("here".getBytes(), response.getBody()));
    }

    @Test
    public void testGetResponse_returnsPartialContentWhenRangeHeaderHasOnlyStartRange() throws IOException {
        String routePath = "/route";
        Route route = new Route(routePath, new String[] {"GET"}, "some data in here".getBytes());

        Response response = ResponseFactory.PartialContent();
        new PartialContentHandler("bytes=5-", response, route).populateResponse();

        assert(Arrays.equals("data in here".getBytes(), response.getBody()));
    }
}
