package github.priyatam.springrest;

import github.priyatam.springrest.resource.PolicyResource;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.setup.MockMvcBuilders.webApplicationContextSetup;
import org.springframework.test.web.server.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.server.setup.MockMvcBuilders.webApplicationContextSetup;

/**
 * Checklist of Tests to ensure the sanity of a Spring ws web application.
 *
 * @author pmudivarti
 */
@RunWith(EnvironmentModeJUnitRunner.class)
@ContextConfiguration(loader = WebContextLoader.class, locations = {"classpath:application-servlet.xml", "classpath*:application-persistence.xml"})
@TransactionConfiguration
public class ChecklistTest {

    @Autowired
    PolicyResource resource;

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webApplicationContextSetup(this.wac).build();
    }

    // Check if Spring appServlet context is configured properly
    @Test
    public void testServletContext() throws Exception {
        assertNotNull(mockMvc);
    }

    // Check if Transactions are used with cglib proxy
    @Test
    public void transactionCGLIB() {
        String resourceClassName = resource.getClass().getName();
        Assert.assertTrue(resourceClassName.contains("EnhancerByCGLIB"));
    }

    @Test
    public void testHealthCheck() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome")));

    }

}
