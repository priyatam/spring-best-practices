package github.priyatam.springrest.resource;

import github.priyatam.springrest.domain.User;
import github.priyatam.springrest.helper.PersistenceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Secured("ROLE_USER")
public class UserResource {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserResource.class);
	
	@Autowired
	private PersistenceHelper persistenceHelper;
	
	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String index(UserDetails userDetails, Model model) {
		LOG.info(userDetails.toString());
		return "user/index";
	}
	
	@RequestMapping(value = "user.json", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public User jsonGetUser(UserDetails userDetails) {
		return persistenceHelper.findByUsername(userDetails.getUsername());
	}
}
