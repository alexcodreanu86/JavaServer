package server;

import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by Alex Codreanu on 11/30/14.
 */
public class ControllerTest {
    @Test
    public void testServerSocket() throws IOException {
        ServerSocket serverSocket = new ServerSocket(6000);
        System.out.println(serverSocket.isClosed());
    }
}
