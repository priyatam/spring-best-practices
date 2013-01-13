package github.priyatam.springrest.resource;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import github.priyatam.springrest.domain.Driver;
import github.priyatam.springrest.domain.DrivingHistory;
import github.priyatam.springrest.domain.Policy;
import github.priyatam.springrest.domain.Vehicle;
import github.priyatam.springrest.exception.InvalidTagException;
import github.priyatam.springrest.exception.PolicyInvalidException;
import github.priyatam.springrest.exception.PolicyInvalidException.ErrorCode;
import github.priyatam.springrest.helper.*;
import github.priyatam.springrest.helper.ResponseBuilderHelper.URLS;
import github.priyatam.springrest.helper.PolicyAsyncHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PolicyResource {

    final static Logger logger = LoggerFactory.getLogger(PolicyResource.class);

    @Inject
    private PolicyAsyncHelper policyWorker;

    @Inject
    protected ResourceHelper resourceHelper;

    @Inject
    protected ETagHelper eTagHelper;

    @Inject
    protected ResponseBuilderHelper responseBuilder;

    @Inject
    MailHelper mailer;

    @Inject
    RuleHelper ruleHelper;

    @Inject
    protected PersistenceHelper persistenceHelper;

    @Value("${wara.ws.virtualhost}")
    private String vhost;

    @RequestMapping(method = RequestMethod.OPTIONS, value = "/policy")
    public ResponseEntity<Policy> options(HttpServletRequest arg0) {
        // TODO Return all the Links for the resource
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/policy")
    @ResponseBody
    public ResponseEntity<List<Policy>> getAll() {
        List<Policy> policies = new ArrayList<Policy>(); // TODO: Implement mapper.getAll
        if (policies.size() == 0) {
            logger.debug("No Policies found");
            return new ResponseEntity<List<Policy>>(policies, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Policy>>(policies, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/policy")
    @ResponseBody
    public ResponseEntity<Policy> save(final @RequestBody Policy policy) {

        // Input Sanitization / Validations
        ErrorCode code = ruleHelper.validatePolicy(policy);
        if (code != null) {
            throw new PolicyInvalidException(code);
        }

        logger.debug("Generating a Future Url ...");
        String futureKey = resourceHelper.generateFutureKey();
        String futureLocation = resourceHelper.createFutureLocation("/policy", futureKey);

        // build header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", futureLocation);

        // async
        policyWorker.createPolicy(futureKey, policy);

        return new ResponseEntity<Policy>(null, headers, HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.HEAD, value = "/policy/{policyNum}")
    @ResponseBody
    public ResponseEntity<Policy> head(HttpServletRequest request, @PathVariable String policyNum) {
        logger.debug("Requested head for: " + policyNum);
        // / String url = ServletUriComponentsBuilder.fromRequest(request).build().toString();
        try {
            String tag = eTagHelper.get(vhost + URLS.POLICY.expand(policyNum));
            HttpHeaders headers = new HttpHeaders();
            headers.add("Etag", tag);
            return new ResponseEntity<Policy>(null, headers, HttpStatus.NO_CONTENT);
        } catch (InvalidTagException e) {
            return new ResponseEntity<Policy>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/policy/{policyNum}")
    @ResponseBody
    public ResponseEntity<Policy> get(@PathVariable String policyNum, WebRequest request) {
        logger.debug("Retrieving Policy :" + policyNum);

        if (request.checkNotModified(resourceHelper.lastModified(policyNum))) {
            // shortcut exit - no further processing necessary
            return null;
        }

        // Load Policy
        Policy policy = persistenceHelper.loadPolicy(policyNum);
        if (policy == null) {
            logger.warn("No Policy found");
            return new ResponseEntity<Policy>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }

        // Convert to Restful Resource, update ETag and HATEOAS references
        responseBuilder.toPolicy(policy);

        return new ResponseEntity<Policy>(policy, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/policy/{policyNum}/drivers")
    @ResponseBody
    public ResponseEntity<List<Driver>> getDrivers(@PathVariable String policyNum) {
        logger.debug("Retrieving Driver for Policy :" + policyNum);

        // Load drivers
        List<Driver> drivers = persistenceHelper.loadDriverByPolicyNum(policyNum);
        if (drivers == null) {
            logger.warn("No Drivers found");
            return new ResponseEntity<List<Driver>>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }

        // Convert to Restful Resource, update ETag and HATEOAS references
        responseBuilder.toDriver(policyNum, drivers);

        return new ResponseEntity<List<Driver>>(drivers, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/policy/{policyNum}/driver/{licenseNo}")
    @ResponseBody
    public ResponseEntity<Driver> getDriver(@PathVariable String policyNum, @NotNull @PathVariable String licenseNo) {
        logger.debug(String.format("Retrieving Driver %s for Policy %s :", licenseNo, policyNum));

        if (licenseNo.length() > 20) {
            logger.warn("licenseNo too large");
            return new ResponseEntity<Driver>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        Driver driver = persistenceHelper.loadDriverByLicenseNum(licenseNo);
        if (driver == null) {
            logger.warn("No Driver found");
            return new ResponseEntity<Driver>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }

        // Convert to Restful Resource, update ETag and HATEOAS references
        responseBuilder.toDriver(policyNum, Lists.newArrayList(driver));

        return new ResponseEntity<Driver>(driver, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/policy/{policyNum}/driver/{licenseNo}/drivinghistory")
    @ResponseBody
    public ResponseEntity<List<DrivingHistory>> getDrivingHistory(@PathVariable String policyNum,
                                                                  @PathVariable String licenseNo) {
        logger.debug(String.format("Retrieving Driver %s for Policy %s :", licenseNo, policyNum));

        List<DrivingHistory> history = persistenceHelper.loadDrivingHistory(policyNum, licenseNo);
        if (history == null) {
            logger.warn("No Driving history found");
            return new ResponseEntity<List<DrivingHistory>>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }

        // Convert to Restful Resource, update ETag and HATEOAS references
        responseBuilder.toDrivingHistory(policyNum, licenseNo, Lists.newArrayList(history));

        return new ResponseEntity<List<DrivingHistory>>(history, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/policy/{policyNum}/vehicle/{vin}")
    @ResponseBody
    public ResponseEntity<Vehicle> getVehicle(@PathVariable String policyNum, @PathVariable String vin) {
        logger.debug(String.format("Retrieving Vehicle %s for Policy %s :", vin, policyNum));

        Vehicle vehicle = persistenceHelper.loadVehicleByVin(vin);
        if (vehicle == null) {
            logger.warn("No Vehicle found");
            return new ResponseEntity<Vehicle>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }

        // Convert to Restful Resource, update ETag and HATEOAS references
        responseBuilder.toVehicle(policyNum, Lists.newArrayList(vehicle));

        return new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/policy/future/{futureKey}")
    @ResponseBody
    public ResponseEntity<Policy> poll(@PathVariable String futureKey) {
        logger.debug("Polling policy with future:" + futureKey);

        String location = resourceHelper.getPermLocation(futureKey);
        if (Strings.isNullOrEmpty(location)) {
            logger.warn("No Policy found for future. Is future expired?");
            return new ResponseEntity<Policy>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
        if (ResourceHelper.ProcessingStatus.PROCESSING.toString().equals(location)) {
            logger.debug("Policy is still processing.");
            return new ResponseEntity<Policy>(null, new HttpHeaders(), HttpStatus.ACCEPTED);
        }
        if (ResourceHelper.ProcessingStatus.ERROR.toString().equals(location)) {
            logger.debug("Policy was not created but resulted in an error.");
            throw new DataIntegrityViolationException("Unable to save Policy", new RuntimeException(
                    resourceHelper.getError(futureKey)));
        }

        logger.debug("Received notification that policy creation is completed");
        String policyNum = resourceHelper.parseKey(resourceHelper.getPermLocation(futureKey));

        // Lookup
        Policy policy = persistenceHelper.loadPolicy(policyNum);
        if (policy == null) {
            logger.error("Can't read policy with key: " + policyNum);
            return new ResponseEntity<Policy>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Convert to Restful Resource with sparse objects
        responseBuilder.toPolicy(policy);

        return new ResponseEntity<Policy>(policy, HttpStatus.CREATED);
    }

}