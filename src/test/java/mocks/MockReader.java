package mocks;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by Alex Codreanu on 11/30/14.
 */
public class MockReader extends StringReader {
    private int charsRead = 0;
    private String inputString;
    public MockReader(String input) {
        super(input);
        this.inputString = input;
    }

    public int read() throws IOException {
        this.charsRead++;
        return super.read();
    }

    public boolean ready() {
        return charsRead == inputString.length();
    }
}
