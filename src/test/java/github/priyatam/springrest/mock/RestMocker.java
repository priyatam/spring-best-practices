package github.priyatam.springrest.mock;

import com.jayway.restassured.RestAssured;
import com.xebialabs.restito.server.StubServer;

public class RestMocker {
    private static StubServer server;

    public RestMocker() {
        // default port = 6666
        server = new StubServer().run();
        RestAssured.port = server.getPort();
    }

    public static void main(String args[]) {
        new RestMocker();
    }

    public void stop() {
        server.stop();
    }

}
