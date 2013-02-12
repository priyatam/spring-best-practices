package github.priyatam.springrest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import github.priyatam.springrest.domain.Policy;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static github.priyatam.springrest.MockDataHelper.createPolicyFull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class SpringClientRestTest {

    static RestTemplate restTemplate = new RestTemplate();
    static MockRestServiceServer mockServer;
    static ObjectMapper mapper = new ObjectMapper();

    @BeforeClass
    public static void setup() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        mapper.registerModule(new JodaModule());
        mapper.registerModule(new GuavaModule());
    }

    @Test
    @Ignore // FIXME: Jackson/Spring Bug: doesn't deserialize joda time when called through spring
    public void stubRestCalls() throws IOException {
        mockServer.expect(requestTo("/policy/pol-1"))
            .andRespond(withSuccess(mockPolicy(), MediaType.APPLICATION_JSON));

        // Use RestTemplate ...
        Policy policy = restTemplate.getForObject("/policy/{policyNum}", Policy.class, "pol-1");
        Assert.assertNotNull(policy);
        
        mockServer.verify();
    }

    String mockPolicy() throws IOException {
        Policy p = createPolicyFull("pol-1000", "vin-1000", "lic-1000");
        String str = mapper.writeValueAsString(p);
        return str;
    }

}
