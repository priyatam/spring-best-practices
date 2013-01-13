package github.priyatam.springrest.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.priyatam.springrest.EnvironmentModeJUnitRunner;
import github.priyatam.springrest.WebContextLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(EnvironmentModeJUnitRunner.class)
@ContextConfiguration(loader = WebContextLoader.class, locations = {"classpath:applicationContext-core.xml", "classpath*:applicationContext-persistence.xml"})
public class DriverResourceTest {

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void getDriver() throws Exception {
        mockMvc.perform(
                get("/driver/lic-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.licenseNumber").value("lic-1"));
    }

}
