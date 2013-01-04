package github.priyatam.springrest.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.priyatam.springrest.EnvironmentModeJUnitRunner;
import github.priyatam.springrest.MockDataHelper;
import github.priyatam.springrest.WebContextLoader;
import github.priyatam.springrest.domain.Policy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.server.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.server.setup.MockMvcBuilders.webApplicationContextSetup;

@RunWith(EnvironmentModeJUnitRunner.class)
@ContextConfiguration(loader = WebContextLoader.class, locations = { "classpath:application-servlet.xml", "classpath*:application-persistence.xml"})
public class PolicyResourceTest {

	@Autowired
	WebApplicationContext wac;
	
	MockMvc mockMvc;
	
	ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setup() {
		this.mockMvc = webApplicationContextSetup(this.wac).build();
	}

	@Test
	public void getPolicy() throws Exception {
		mockMvc.perform(
					get("/policy/pol-1"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.company").value("commerce"))
					.andExpect(jsonPath("$.state").value("MA"))
					.andExpect(jsonPath("$.quote").value(1033));	
	}

	@Test
	public void savePolicy() throws Exception {
		Policy policy = MockDataHelper.createPolicyFull("lic-test1", "vin-test1", "acc1");
		mockMvc.perform(
					post("/policy")
						.header("Content-Type", "application/json")
						.body(mapper.writeValueAsBytes(policy)))
					.andExpect(status().isAccepted())
					.andExpect(header().string("Location", containsString("/future")));
	}
}
