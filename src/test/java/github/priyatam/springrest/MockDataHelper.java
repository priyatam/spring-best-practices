package github.priyatam.springrest;

import com.google.common.collect.Lists;
import github.priyatam.springrest.domain.*;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.io.File;

public class MockDataHelper {

    static String jsonRoot = System.getProperty("user.dir") + "/src/test/resources/";

    public static Address createAddress() {
        return new Address("100 cambridge st", "apt 1", "cambridge", "MA", "02139", Address.AddressType.HOMEOWNER);
    }

    public static Driver createDriver1() {
        Address a = createAddress();
        DrivingHistory dh = MockDataHelper.createDrivingHistory();

        return new Driver.Builder().withLicenseNum("Lic-123").withBirthDate(new LocalDate()).withFirstName("Sarah")
                .withLastName("Conor").withLicenseExpiryDate(new LocalDate()).withGender(Driver.Gender.FEMALE)
                .withEmail("cooldriver@junkmail123.com").withPhone("6178769876").withOccupation("hacker")
                .withFirstLicenseAtAge(23).withIsMarried(false).withAddress(a)
                .withDrivingHistory(Lists.newArrayList(dh)).build();
    }

    public static Driver createDriverFull(String licenseNo, String accident) {
        return new Driver.Builder().withLicenseNum(licenseNo).withBirthDate(new LocalDate()).withFirstName("Stephen")
                .withLastName("King").withLicenseExpiryDate(new LocalDate()).withGender(Driver.Gender.MALE)
                .withEmail("baddriver@junkmail123.com").withPhone("6178769876").withOccupation("writer")
                .withFirstLicenseAtAge(17).withIsMarried(false).withAddress(createAddress())
                .withDrivingHistory(Lists.newArrayList(createDrivingHistoryFull(accident))).build();
    }

    public static Vehicle createVehicle(String vin) {
        return new Vehicle.Builder().withVin(vin).withMake("bmw").withModel("335xi").withYear(2012)
                .withPlateNum("1234567890").withOdomoterReading(24000).withOwnerType(Vehicle.VehicleOwnerType.LEASED).build();
    }

    public static DrivingHistory createDrivingHistory() {
        return new DrivingHistory.Builder().withAnnualMileage(2000).withIsGarageParked(true).withIsPrimaryOperator(true)
                .withIsAccident(false).build();
    }

    public static DrivingHistory createDrivingHistoryFull(String accident) {
        return new DrivingHistory.Builder().withAnnualMileage(2000).withIsGarageParked(true).withIsPrimaryOperator(true)
                .withIsAccident(true).withAccidentTime(new LocalDateTime()).withIsThirdParyOffence(true).build();
    }


    public static Policy createPolicyFull(String licenseNo, String vin, String accident) {
        return new Policy.Builder().withEffectiveDate(new LocalDate()).withExpiryDate(new LocalDate()).withTerm(3)
                .withCompany("PlymouthRock").withState("MA").withDeclineReason(null)
                .withAgency("Commerce").withDrivers(Lists.newArrayList(createDriverFull(licenseNo, accident)))
                .withVehicles(Lists.newArrayList(createVehicle(vin))).build();
    }

    public static Policy createPolicyFull(String policyNum) {
        return new Policy.Builder().withPolicyNum(policyNum).withEffectiveDate(new LocalDate("2015-1-1")).withExpiryDate(new LocalDate())
                .withTerm(3).withCompany("PlymouthRock").withState("MA").withQuote(1250).withDeclineReason(null)
                .withAgency("Commerce").withDrivers(Lists.newArrayList(createDriver1()))
                .withVehicles(Lists.newArrayList(createVehicle("123456789"))).build();
    }

    public static File file(String name) {
        return new File(jsonRoot + name);
    }
}
