package response;

import com.java_server.response.Response;
import com.java_server.response.ResponseGenerator;
import org.junit.Test;

/**
 * Created by Alex Codreanu on 11/21/14.
 */
public class ResponseGeneratorTest {
    @Test
    public void generateReponse_generatesTheAllowedHeadersForAGivenUrl() {
        Response response = ResponseGenerator.generateResponse("/methon_options");
    }
}
