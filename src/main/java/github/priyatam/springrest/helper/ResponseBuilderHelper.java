package github.priyatam.springrest.helper;

import com.google.common.collect.Lists;
import github.priyatam.springrest.domain.Driver;
import github.priyatam.springrest.domain.DrivingHistory;
import github.priyatam.springrest.domain.Policy;
import github.priyatam.springrest.domain.Vehicle;
import github.priyatam.springrest.utils.Link;
import github.priyatam.springrest.utils.LinkBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ResponseBuilderHelper {

    final static Logger logger = LoggerFactory.getLogger(ResponseBuilderHelper.class);

    @Inject
    protected ETagHelper eTagHelper;

    @Inject
    private PersistenceHelper persistenceHelper;

    @Value("${virtualhost}")
    private String vhost;

    public enum URLS {

        POLICY("/policy/{policyNum}"),
        POLICY_VEHICLE("/policy/{policyNum}/vehicle/{vin}"),
        POLICY_DRIVER("/policy/{policyNum}/driver/{licenseNo}"),
        DRIVING_HISTORY("/policy/{policyNum}/driver/{licenseNo}/drivinghistory"),
        VEHICLE("/vehicle/{vin}"),
        DRIVER("/driver/{licenseNo}");

        final String url;

        URLS(String url) {
            this.url = url;
        }

        public String expand(String policyNum, String param) {
            switch (this) {
                case POLICY_DRIVER:
                    return url.replaceAll("\\{policyNum\\}", policyNum).replaceAll("\\{licenseNo\\}", param);
                case POLICY_VEHICLE:
                    return url.replaceAll("\\{policyNum\\}", policyNum).replaceAll("\\{vin\\}", param);
                case DRIVING_HISTORY:
                    return url.replaceAll("\\{policyNum\\}", policyNum).replaceAll("\\{licenseNo\\}", param);
                default:
                    return "";
            }
        }

        public String expand(String param) {
            switch (this) {
                case POLICY:
                    return url.replaceAll("\\{policyNum\\}", param);
                case DRIVER:
                    return url.replaceAll("\\{licenseNo\\}", param);
                case VEHICLE:
                    return url.replaceAll("\\{vin\\}", param);
                default:
                    return "";
            }
        }
    }

    public void toPolicy(Policy policy) {
        // Build policy link
        if (policy.getLinks().size() != 1) {
            policy.addLink(LinkBuilder.build(URLS.POLICY.expand(policy.getPolicyNum()), policy.toString()));
        }

        // Build driver links
        List<Driver> drivers = persistenceHelper.loadDriverByPolicyNum(policy.getPolicyNum());

        toDriver(policy, policy.getPolicyNum(), drivers);

        // Build vehicle links
        List<Vehicle> vehicles = persistenceHelper.loadVehicleByPolicyNum(policy.getPolicyNum());

        toVehicle(policy, policy.getPolicyNum(), vehicles);

        // Update Etag
        eTagHelper.generate(vhost + URLS.POLICY.expand(policy.getPolicyNum()), policy);
    }

    public void toPolicyFull(Policy policy) {
        // Build policy link
        if (policy.getLinks().size() != 1) {
            policy.addLink(LinkBuilder.build(URLS.POLICY.expand(policy.getPolicyNum()), policy.toString()));
        }

        // Build driver links
        List<Driver> drivers = persistenceHelper.loadDriverByPolicyNum(policy.getPolicyNum());

        toDriver(policy, policy.getPolicyNum(), drivers);
        policy = policy.deepCopyWithDrivers(drivers);

        // Build vehicle links
        List<Vehicle> vehicles = persistenceHelper.loadVehicleByPolicyNum(policy.getPolicyNum());
        policy = policy.deepCopyWithVehicles(vehicles);

        toVehicle(policy, policy.getPolicyNum(), vehicles);

        // Update Etag
        eTagHelper.generate(vhost + URLS.POLICY.expand(policy.getPolicyNum()), policy);
    }

    public void toDriver(Policy policy, String policyNum, List<Driver> drivers) {
        for (Driver _d : drivers) {
            Driver driver = new Driver.Builder().withLicenseNum(_d.getLicenseNum()).build();
            driver.addLink(LinkBuilder.build(URLS.POLICY.expand(policyNum), "Policy", Link.REL_PARENT));
            driver.addLink(LinkBuilder.build(URLS.POLICY_DRIVER.expand(policyNum, _d.getLicenseNum()), _d.toString()));

            List<DrivingHistory> dh = persistenceHelper.loadDrivingHistory(policyNum, _d.getLicenseNum());
            toDrivingHistory(driver, policyNum, _d.getLicenseNum(), dh);

            if (policy != null) {
                policy = policy.deepCopyWithDrivers(Lists.newArrayList(driver));
            }

            // Update Etag
            eTagHelper.generate(vhost + URLS.POLICY_DRIVER.expand(policyNum, _d.getLicenseNum()), _d);
        }
    }

    public void toDriver(List<Driver> drivers) {
        for (Driver driver : drivers) {
            if (driver.getLinks().size() != 1) {
                driver.addLink(LinkBuilder.build(URLS.DRIVER.expand(driver.getLicenseNum()), driver.toString()));
            }

            // Update Etag
            eTagHelper.generate(vhost + URLS.DRIVER.expand(driver.getLicenseNum()), driver);
        }
    }

    public void toDriver(String policyNum, List<Driver> drivers) {
        for (Driver driver : drivers) {
            if (driver.getLinks().size() != 2) {
                driver.addLink(LinkBuilder.build(URLS.POLICY.expand(policyNum), "Policy", Link.REL_PARENT));
                driver.addLink(LinkBuilder.build(URLS.POLICY_DRIVER.expand(policyNum, driver.getLicenseNum()),
                        driver.toString()));
            }

            List<DrivingHistory> dh = persistenceHelper.loadDrivingHistory(policyNum, driver.getLicenseNum());
            toDrivingHistory(policyNum, driver.getLicenseNum(), dh);
            for (DrivingHistory history : dh) {
                driver = driver.deepCopyWithDrivingHistory(Lists.newArrayList(history));
            }

            // Update Etag
            eTagHelper.generate(vhost + URLS.POLICY_DRIVER.expand(policyNum, driver.getLicenseNum()), driver);
        }
    }

    public void toDrivingHistory(String policyNum, String licenseNo, List<DrivingHistory> historyList) {
        for (DrivingHistory history : historyList) {
            if (history.getLinks().size() != 2) {
                history.addLink(LinkBuilder.build(URLS.DRIVER.expand(licenseNo), "Driver", Link.REL_PARENT));
                history.addLink(LinkBuilder.build(URLS.DRIVING_HISTORY.expand(policyNum, licenseNo), history.toString()));
            }

            // Update Etag
            eTagHelper.generate(vhost + URLS.DRIVING_HISTORY.expand(policyNum, licenseNo), history);
        }
    }

    public void toDrivingHistory(Driver driver, String policyNum, String licenseNo, List<DrivingHistory> historyList) {
        for (DrivingHistory _h : historyList) {
            DrivingHistory history = new DrivingHistory.Builder().build();
            history.addLink(LinkBuilder.build(URLS.DRIVER.expand(licenseNo), driver.toString(), Link.REL_PARENT));
            history.addLink(LinkBuilder.build(URLS.DRIVING_HISTORY.expand(policyNum, licenseNo), _h.toString()));

            if (driver != null) {
                driver = driver.deepCopyWithDrivingHistory(Lists.newArrayList(history));
            }

            // Update Etag
            eTagHelper.generate(vhost + URLS.DRIVING_HISTORY.expand(policyNum, licenseNo), _h);
        }
    }

    public void toVehicle(List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getLinks().size() != 1) {
                vehicle.addLink(LinkBuilder.build(URLS.VEHICLE.expand(vehicle.getVin()), vehicle.toString()));

                // Update Etag
                eTagHelper.generate(vhost + URLS.VEHICLE.expand(vehicle.getVin()), vehicle);
            }
        }
    }

    public void toVehicle(Policy policy, String policyNum, List<Vehicle> vehicles) {
        for (Vehicle _v : vehicles) {
            if (_v.getLinks().size() != 2) {
                Vehicle vehicle = new Vehicle.Builder().withVin(_v.getVin()).build();
                vehicle.addLink(LinkBuilder.build(URLS.POLICY.expand(policyNum), "Policy", Link.REL_PARENT));
                vehicle.addLink(LinkBuilder.build(URLS.POLICY_VEHICLE.expand(policyNum, _v.getVin()), _v.toString()));

                if (policy != null) {
                    policy = policy.deepCopyWithVehicles(Lists.newArrayList(vehicle));
                }
            }
            // Update Etag
            eTagHelper.generate(vhost + URLS.POLICY_VEHICLE.expand(policyNum, _v.getVin()), _v);
        }
    }

    public void toVehicle(String policyNum, List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getLinks().size() != 2) {
                vehicle.addLink(LinkBuilder.build(URLS.POLICY.expand(policyNum), "Policy", Link.REL_PARENT));
                vehicle.addLink(LinkBuilder.build(URLS.POLICY_VEHICLE.expand(policyNum, vehicle.getVin()),
                        vehicle.toString()));
            }

            // Update Etag
            eTagHelper.generate(vhost + URLS.POLICY_VEHICLE.expand(policyNum, vehicle.getVin()), vehicle);
        }
    }

}
