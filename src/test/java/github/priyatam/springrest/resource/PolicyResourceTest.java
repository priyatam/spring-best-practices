package github.priyatam.springrest.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.priyatam.springrest.EnvironmentModeJUnitRunner;
import github.priyatam.springrest.MockDataHelper;
import github.priyatam.springrest.WebContextLoader;
import github.priyatam.springrest.domain.Policy;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Matchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(EnvironmentModeJUnitRunner.class)
@ContextConfiguration(loader = WebContextLoader.class, locations = {"classpath:applicationContext-core.xml", "classpath*:applicationContext-persistence.xml"})
public class PolicyResourceTest {

	@Autowired
	WebApplicationContext wac;
	
	MockMvc mockMvc;
	
	ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac)
            .alwaysExpect(content().contentType("application/json;charset=UTF-8"))    
            .build();
	}

	@Test
    @Ignore
    public void getPolicy() throws Exception {
		mockMvc.perform(
					get("/policy/pol-1"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.company").value("PlymouthRock"))
					.andExpect(jsonPath("$.state").value("MA"))
					.andExpect(jsonPath("$.quote").value(1250));	
	}

	@Test
    @Ignore //FIXME
	public void savePolicy() throws Exception {
		Policy policy = MockDataHelper.createPolicyFull("lic-test1", "vin-test1", "acc1");
		mockMvc.perform(
					post("/policy")
						.header("Content-Type", "application/json")
						.content(mapper.writeValueAsBytes(policy)))
					.andExpect(status().isAccepted())
					.andExpect(header().string("Location", contains("/future")));
	}
}
