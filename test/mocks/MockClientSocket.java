package mocks;

import java.io.*;
import java.net.Socket;

/**
 * Created by Alex Codreanu on 11/30/14.
 */
public class MockClientSocket extends Socket {
    private boolean closed = false;
    private byte[] inStream;
    private InputStream inputStream;
    private OutputStream outputStream;

    public MockClientSocket() {
        this("".getBytes());
    }

    public MockClientSocket(byte[] inputStream) {
        this.inStream = inputStream;
        this.inputStream = new ByteArrayInputStream(inStream);
        this.outputStream = new ByteArrayOutputStream();
    }

    public void close() {
        this.closed = true;
    }

    public boolean isClosed() {
        return this.closed;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }
}
