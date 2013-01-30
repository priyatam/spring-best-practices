package github.priyatam.springrest.helper;

import github.priyatam.springrest.domain.Policy;
import github.priyatam.springrest.utils.exception.PolicyInvalidException.ErrorCode;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// This can be hooked to a Drools Service
@Component
public class RuleHelper {

	final static Logger logger = LoggerFactory.getLogger(RuleHelper.class);

	public ErrorCode validatePolicy(Policy policy) {

		if (!Strings.isNullOrEmpty(policy.getPolicyNum().toString())) {
			logger.info("Pre-condition failed: Policy Number should be null");
			return ErrorCode.NON_NULL_POLICY_NUM;
		}

		if (policy.getQuote() != null) {
			logger.info("Pre-condition failed: Quote should be null");
			return ErrorCode.NON_NULL_POLICY_NUM;
		}

		if (policy.getVehicles() != null && policy.getVehicles().size() > 10) {
			logger.info("Pre-condition failed: Policy can't have more than 10 vehicles");
			return ErrorCode.TOO_MANY_VEHICLES;
		}

		if (policy.getDrivers() != null && policy.getDrivers().size() > 10) {
			logger.info("Pre-condition failed: Policy can't have more than 10 drivers");
			return ErrorCode.TOO_MANY_DRIVERS;
		}

		return null; // pass
	}
}
