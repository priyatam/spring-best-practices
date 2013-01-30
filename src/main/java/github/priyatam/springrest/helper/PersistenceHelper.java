package github.priyatam.springrest.helper;

import github.priyatam.springrest.domain.*;
import github.priyatam.springrest.integration.QuoteGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Random;

@Repository
public class PersistenceHelper {

    final static Logger logger = LoggerFactory.getLogger(PersistenceHelper.class);

    @Inject
    ETagHelper eTagHelper;

    @Inject
    QuoteGenerator quoteGenerator;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    public User findByUsername(String username) {
        try {
            return entityManager.createNamedQuery("User.FIND_BY_USERNAME", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (PersistenceException e) {
            return null;
        }
    }

    @Cacheable(value = "policies")
    public Policy loadPolicy(String policyNum) {
        return entityManager.createNamedQuery("Policy.FIND_BY_POLICYNUM", Policy.class)
                .setParameter("policyNum", policyNum)
                .getSingleResult();
    }

    @Cacheable(value = "drivers")
    public Driver loadDriverByLicenseNum(String licenseNum) {
        return entityManager.createNamedQuery("Driver.FIND_BY_LICENSENUM", Driver.class)
                .setParameter("licenseNum", licenseNum)
                .getSingleResult();
    }

    @Cacheable(value = "policy-drivers", key = "#policyNum")
    public List<Driver> loadDriverByPolicyNum(String policyNum) {
        return entityManager.createNamedQuery("Driver.FIND_BY_POLICYNUM", Driver.class)
                .setParameter("policyNum", policyNum)
                .getResultList();
    }

    @Cacheable(value = "vehicles", key = "#vin")
    public Vehicle loadVehicleByVin(String vin) {
        return entityManager.createNamedQuery("Vehicle.FIND_BY_VIN", Vehicle.class)
                .setParameter("vin", vin)
                .getSingleResult();
    }

    @Cacheable(value = "policy-vehicles")
    public List<Vehicle> loadVehicleByPolicyNum(String policyNum) {
        return entityManager.createNamedQuery("Vehicle.FIND_BY_POLICYNUM", Vehicle.class)
                .setParameter("policyNum", policyNum)
                .getResultList();
    }

    @Cacheable(value = "driving-history")
    public List<DrivingHistory> loadDrivingHistory(String vin, String licenseNum) {
        return entityManager.createNamedQuery("DrivingHistory.FIND_BY_DRIVER_VEHICLE", DrivingHistory.class)
                .setParameter("licenseNum", licenseNum)
                .setParameter("vin", vin)
                .getResultList();
    }

    @Transactional
    public String create(final Policy policy) {
        logger.debug("Saving Policy");

        // Invoke Quote Service
        Policy policyWithQuote = policy.deepCopyWithQuote(quoteGenerator.generateQuote(policy));

        // Generate Policy Number
        Policy policyWithNum = policyWithQuote.deepCopyWithPolicyNum(policyWithQuote.getAgency() + "-"
                + new Random().nextInt(10000));

        // Save policy
        logger.debug("Finished Saving Policy: " + policy);
        entityManager.persist(policyWithNum);

        return policyWithNum.getPolicyNum();
    }

}