package github.priyatam.springrest.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import github.priyatam.springrest.MockDataHelper;
import org.joda.time.LocalDate;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DomainTest {

    static String jsonRoot = System.getProperty("user.dir") + "/src/test/resources/";

    static ObjectMapper mapper = new ObjectMapper();

    @BeforeClass
    public static void initModules() {
        mapper.registerModule(new JodaModule());
        mapper.registerModule(new GuavaModule());
    }

    @Test
    @Ignore
    public void testJodaDate() throws IOException {
        LocalDate date = new LocalDate(2001, 5, 25);
        assertEquals("[2001,05,25]", mapper.writeValueAsString(date));
    }

    @Test
    public void testAddress() throws IOException {
        mapper.writeValue(file("address.json"), MockDataHelper.createAddress());
        Address result = mapper.readValue(file("address.json"), Address.class);
        assertNotNull(result);
    }

    @Test
    public void testVehicle() throws IOException {
        mapper.writeValue(file("vehicle.json"), MockDataHelper.createVehicle("vin-1"));
        Vehicle result = mapper.readValue(file("vehicle.json"), Vehicle.class);
        assertNotNull(result);
    }

    @Test
    public void testDrivingHistory() throws IOException {
        DrivingHistory h = MockDataHelper.createDrivingHistory();
        mapper.writeValue(file("drivingHistory.json"), h);
        DrivingHistory result = mapper.readValue(file("drivingHistory.json"), DrivingHistory.class);
        assertNotNull(result);
    }

    @Test
    public void testDriver() throws IOException {
        Driver d = MockDataHelper.createDriver1();
        mapper.writeValue(file("driver.json"), d);

        Driver result = mapper.readValue(file("driver.json"), Driver.class);
        assertNotNull(result);
    }

    @Test
    public void testAccident() throws IOException {
        mapper.writeValue(file("accident.json"), MockDataHelper.createAccident1());
        Accident accident = mapper.readValue(file("accident.json"), Accident.class);
        assertNotNull(accident);
    }

    @Test
    public void testPolicy() throws IOException {
        Policy p = MockDataHelper.createPolicyFull("S123");
        mapper.writeValue(file("policy.json"), p);
        Policy result = mapper.readValue(file("policy.json"), Policy.class);
        assertNotNull(result);
    }

    @Test
    public void testPolicyWithEmpty() throws IOException {
        Policy p = MockDataHelper.createPolicyEmpty("S123");
        mapper.writeValue(file("policy-nulls.json"), p);
        Policy result = mapper.readValue(file("policy-nulls.json"), Policy.class);
        assertNotNull(result);
    }

    public static File file(String name) {
        return new File(jsonRoot + name);
    }

    public static void assertPolicy(Policy p) {
        assertNotNull(p);
    }

}