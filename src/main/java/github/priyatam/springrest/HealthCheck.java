package github.priyatam.springrest;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * HealthCheck to verify if Resources are up
 */
@Controller
public class HealthCheck {

	private static final Logger logger = LoggerFactory.getLogger(HealthCheck.class);

	@RequestMapping(value = { "", "/", "/home" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> home(Locale locale) {
		logger.info("Welcome home. The client locale is " + locale.toString());
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(new Date());
		return new ResponseEntity<String>("Welcome, SpringMVC Rest Demo is running. The time on the server is: "
				+ formattedDate, HttpStatus.OK);
	}

}
