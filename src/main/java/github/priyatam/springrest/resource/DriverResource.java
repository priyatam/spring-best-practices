package github.priyatam.springrest.resource;

import com.google.common.collect.Lists;
import github.priyatam.springrest.domain.Driver;
import github.priyatam.springrest.helper.PersistenceHelper;
import github.priyatam.springrest.helper.ResponseBuilderHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DriverResource {

    final static Logger logger = LoggerFactory.getLogger(DriverResource.class);

    @Autowired
    protected ResponseBuilderHelper responseBuilder;

    @Autowired
    protected PersistenceHelper persistenceHelper;


    @RequestMapping(method = RequestMethod.GET, value = "/driver/{licenseNo}")
    @ResponseBody
    public ResponseEntity<Driver> getDriver(@PathVariable String licenseNo) {
        logger.debug(String.format("Retrieving Driver %s :", licenseNo));

        Driver driver = persistenceHelper.loadDriverByLicenseNum(licenseNo);
        if (driver == null) {
            logger.warn("No Driver found");
            return new ResponseEntity<Driver>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }

        // Convert to Restful Resource, update ETag and HATEOAS references
        responseBuilder.toDriver(Lists.newArrayList(driver));

        return new ResponseEntity<Driver>(driver, HttpStatus.OK);
    }

}
