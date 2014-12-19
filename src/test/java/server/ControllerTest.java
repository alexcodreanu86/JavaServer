package server;

import com.java_server.args.GlobalArguments;
import com.java_server.server.Controller;
import com.java_server.parser.ConfigParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import mocks.MockConfigParser;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Alex Codreanu on 11/30/14.
 */

public class ControllerTest {
    ServerSocket serverSocket;
    ConfigParser parser;

    @Before
    public void setupApp() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        parser = new MockConfigParser("mockPath", "9090");

        GlobalArguments.setArgs(new String[0], parser);
        int port = 6000;
        serverSocket = new ServerSocket(port);
    }

    @After
    public void tearDown() throws IOException{
        serverSocket.close();
    }

    @Test
    public void testListen_startsListeningToServerConnections() throws IOException {
        Controller controller = new Controller(serverSocket, parser);
        ControllerStarter starter = new ControllerStarter(controller);
        starter.start();
        URL url = new URL("http://localhost:6000");
        URLConnection connection = url.openConnection();
        String contentType = connection.getContentType();

        assertEquals("text/html", contentType);
    }

    @Test
    public void testListen_stopsListeningWhenTheServerSocketIsClosed() throws IOException {
        Controller controller = new Controller(serverSocket, parser);
        ControllerStarter starter = new ControllerStarter(controller);
        starter.start();
        serverSocket.close();
        URL url = new URL("http://localhost:6000");
        URLConnection connection = url.openConnection();
        String contentType = connection.getContentType();

        try {
            url.openStream();
            fail("Server not disconnected!");
        } catch(ConnectException e) {}

        assertNull(contentType);
    }

    class ControllerStarter extends Thread {
        public Controller controller;
        public ControllerStarter(Controller inController) {
            this.controller = inController;
        }

        public void run() {
            try {
                controller.listen();
            }
            catch (IOException e) {}
        }
    }
}

