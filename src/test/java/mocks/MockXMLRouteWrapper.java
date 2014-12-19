package mocks;

import com.java_server.parser.XMLRouteWrapper;

/**
 * Created by Alex Codreanu on 12/18/14.
 */
public class MockXMLRouteWrapper implements XMLRouteWrapper {
    String path, redirectPath, methods[];
    Boolean auth;

    public MockXMLRouteWrapper(String inPath, Boolean inAuth, String[] inMethods) {
        this(inPath, inAuth, inMethods, null);
    }

    public MockXMLRouteWrapper(String inPath, Boolean inAuth, String[] inMethods, String inRedirectPath) {
        path = inPath;
        auth = inAuth;
        methods = inMethods;
        redirectPath = inRedirectPath;
    }

    public String getPath() { return path; }

    public Boolean requiresAuth() { return auth; }

    public String[] getMethods() { return methods; }

    public String getRedirectPath() { return redirectPath; }
}
