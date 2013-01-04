package github.priyatam.springrest.resource;

import com.google.common.collect.Lists;
import github.priyatam.springrest.domain.Vehicle;
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
public class VehicleResource {

    final static Logger logger = LoggerFactory.getLogger(VehicleResource.class);

    @Autowired
    protected PersistenceHelper persistenceHelper;

    @Autowired
    protected ResponseBuilderHelper responseBuilder;

    @RequestMapping(method = RequestMethod.GET, value = "/vehicle/{vin}")
    @ResponseBody
    public ResponseEntity<Vehicle> getVehicle(@PathVariable String vin) {
        logger.debug(String.format("Retrieving Vehicle %s :", vin));

        Vehicle vehicle = persistenceHelper.loadVehicleByVin(vin);
        if (vehicle == null) {
            logger.warn("No Vehicle found");
            return new ResponseEntity<Vehicle>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }

        // Convert to Restful Resource, update ETag and HATEOAS references
        responseBuilder.toVehicle(Lists.newArrayList(vehicle));

        return new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
    }

}
