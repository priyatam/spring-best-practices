package github.priyatam.springrest.mock;


import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.xebialabs.restito.server.StubServer;
import org.glassfish.grizzly.http.Method;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.expect;
import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.builder.verify.VerifyHttp.verifyHttp;
import static com.xebialabs.restito.semantics.Action.resourceContent;
import static com.xebialabs.restito.semantics.Action.status;
import static com.xebialabs.restito.semantics.Action.success;
import static com.xebialabs.restito.semantics.Condition.*;
import static org.hamcrest.CoreMatchers.containsString;

public class RestMockerTest {

    private static StubServer server;

    @Before
    public void setup() {
        server = new StubServer().run();
        RestAssured.port = server.getPort();
    }

    @Test
    public void shouldTestStatus() {
        // Restito
        whenHttp(server).
                match(get("/policy/pol-1")).
                then(status(HttpStatus.OK_200));

        // Rest-assured
        expect().statusCode(200).when().get("/policy/pol-1");

        // Restito
        verifyHttp(server).once(
                method(Method.GET),
                uri("/policy/pol-1")
        );
    }

    @Test
    public void shouldDoContentNegotiation() {
        whenHttp(server).
                match(get("/policy/pol-1")).
                then(resourceContent("policy.json"));

        expect().contentType(ContentType.JSON).when().get("/policy/pol-1");
    }

    @Test
    public void shouldFindJsonFileByUrl() {
        whenHttp(server).
                match(get("/policy/pol-1")).
                then(success());

        expect().content(containsString("policyNum\":\"S123\"")).when().get("/policy/pol-1");
    }

    @After
    public void stop() {
        server.stop();
    }

}
