package github.priyatam.springrest.view;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class PolicyForm {

	private Date effectiveDate;

	@NotEmpty
	private String state;

	private List<Driver> drivers;
	private List<Vehicle> vehicles;

	public static class Driver {
		private String firstName;

		@NotEmpty
		private String lastName;

		@DateTimeFormat(iso = ISO.DATE)
		@Past
		private Date birthDate;

		private String gender;

		@NotEmpty
		private String email;
		private String phone;
		private String occupation;

		@NotEmpty
		private String firstLicenseAtAge;

		@NotEmpty
		private String priorCarrier;

		private Boolean married;

		private String addrLine1;

		private String addrLine2;
		private String city;
		private String state;
		private String zip;
		private String type;

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public Date getBirthDate() {
			return birthDate;
		}

		public void setBirthDate(Date birthDate) {
			this.birthDate = birthDate;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getOccupation() {
			return occupation;
		}

		public void setOccupation(String occupation) {
			this.occupation = occupation;
		}

		public String getFirstLicenseAtAge() {
			return firstLicenseAtAge;
		}

		public void setFirstLicenseAtAge(String firstLicenseAtAge) {
			this.firstLicenseAtAge = firstLicenseAtAge;
		}

		public String getPriorCarrier() {
			return priorCarrier;
		}

		public void setPriorCarrier(String priorCarrier) {
			this.priorCarrier = priorCarrier;
		}

		public Boolean getMarried() {
			return married;
		}

		public void setMarried(Boolean married) {
			this.married = married;
		}

		public String getAddrLine1() {
			return addrLine1;
		}

		public void setAddrLine1(String addrLine1) {
			this.addrLine1 = addrLine1;
		}

		public String getAddrLine2() {
			return addrLine2;
		}

		public void setAddrLine2(String addrLine2) {
			this.addrLine2 = addrLine2;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getZip() {
			return zip;
		}

		public void setZip(String zip) {
			this.zip = zip;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

	}

	public static class Vehicle {
		private String make;
		private String model;
		private Integer year;
		private String plateNum;
		private Integer odomoterReading;

		private List<DrivingHistory> drivingHistory;

		public String getMake() {
			return make;
		}

		public void setMake(String make) {
			this.make = make;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public Integer getYear() {
			return year;
		}

		public void setYear(Integer year) {
			this.year = year;
		}

		public String getPlateNum() {
			return plateNum;
		}

		public void setPlateNum(String plateNum) {
			this.plateNum = plateNum;
		}

		public Integer getOdomoterReading() {
			return odomoterReading;
		}

		public void setOdomoterReading(Integer odomoterReading) {
			this.odomoterReading = odomoterReading;
		}

		public List<DrivingHistory> getDrivingHistory() {
			return drivingHistory;
		}

		public void setDrivingHistory(List<DrivingHistory> drivingHistory) {
			this.drivingHistory = drivingHistory;
		}

	}

	public static class DrivingHistory {
		private Integer annualMileage;
		private Date accidentTime;
		private String accidentType;
		private Boolean thirdPartyOffence;

		public Integer getAnnualMileage() {
			return annualMileage;
		}

		public void setAnnualMileage(Integer annualMileage) {
			this.annualMileage = annualMileage;
		}

		public Date getAccidentTime() {
			return accidentTime;
		}

		public void setAccidentTime(Date accidentTime) {
			this.accidentTime = accidentTime;
		}

		public String getAccidentType() {
			return accidentType;
		}

		public void setAccidentType(String accidentType) {
			this.accidentType = accidentType;
		}

		public Boolean getThirdPartyOffence() {
			return thirdPartyOffence;
		}

		public void setThirdPartyOffence(Boolean thirdPartyOffence) {
			this.thirdPartyOffence = thirdPartyOffence;
		}

	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

}
