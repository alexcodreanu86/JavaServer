package server;

import com.java_server.args.GlobalArguments;
import com.java_server.routing.RoutesGenerator;
import com.java_server.server.ClientConnection;
import mocks.MockClientSocket;
import org.junit.Before;
import org.junit.Test;
import utils.MockConfigParser;

import java.io.IOException;


/**
 * Created by Alex Codreanu on 11/30/14.
 */
public class ClientConnectionTest {
    @Before
    public void setupApp(){
        GlobalArguments.setArgs(new String[0], new MockConfigParser("mockPath", "9090"));
        RoutesGenerator.generate();
    }

    @Test
    public void testProcessesNewRequest() throws IOException{
        MockClientSocket socket = new MockClientSocket("GET / HTTP/1.1\r\n\r\n".getBytes());
        ClientConnection clientConnection = new ClientConnection(socket);

        clientConnection.run();
        assert(socket.getOutputStream().toString().contains("HTTP/1.1 200 OK"));
    }

    //TODO test Threads
    public void testExtendsThread() throws IOException {
        MockClientSocket socket = new MockClientSocket("GET / HTTP/1.1\r\n\r\n".getBytes());
        ClientConnection clientConnection = new ClientConnection(socket);

        clientConnection.start();
        assert(socket.getOutputStream().toString().contains("200"));
    }
}
