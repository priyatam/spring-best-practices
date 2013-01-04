package github.priyatam.springrest;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Base JUnit Runner that adds env mode to all unit tests
 */
public class EnvironmentModeJUnitRunner extends SpringJUnit4ClassRunner {

	public EnvironmentModeJUnitRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
		initJvmParams();
	}

	public static void initJvmParams() {
		System.getProperties().put("properties.root", "classpath:");
	    System.getProperties().put("environment.mode", "local");
	}
}
