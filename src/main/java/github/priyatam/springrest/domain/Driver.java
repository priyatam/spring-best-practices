package github.priyatam.springrest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import github.priyatam.springrest.utils.Link;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@JsonDeserialize(builder = Driver.Builder.class)
@Entity
@NamedQueries({
        @NamedQuery(
                name = "Driver.FIND_BY_LICENSENUM", query = "select o from Driver o where o.licenseNum = :licenseNum"),
        @NamedQuery(
                name = "Driver.FIND_BY_POLICYNUM", query = "select o from Driver o where o.policy.policyNum = :policyNum")
})
public final class Driver extends BaseDomain implements Comparable<Driver>, Serializable {

    private static final long serialVersionUID = 7427627725590393187L;

    public enum Gender {
        MALE, FEMALE
    }

    // Key
    @Column(unique = true)
    @Index(name = "licenseNumIndex")
    private final String licenseNum;

    private final String licenseState;
       
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")    
    private final LocalDate licenseExpiryDate;
    private final String firstName;
    private final String lastName;
    
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")    
    private final LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private final Gender gender;
    private final String email;
    private final String phone;
    private final String occupation;
    private final Integer firstLicenseAtAge;
    private final Boolean isMarried;
    private final String priorCarrier;

    @OneToOne(cascade = {CascadeType.ALL}, fetch=FetchType.EAGER)
    @JoinColumn(name = "addressId")
    private final Address address;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "policyId")
    @JsonIgnore
    private Policy policy;

    // History
    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private final List<DrivingHistory> drivingHistory;

    private Driver(Builder builder) {
        this.licenseNum = builder.licenseNum;
        this.licenseState = builder.licenseState;
        this.licenseExpiryDate = builder.licenseExpiryDate;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.birthDate = builder.birthDate;
        this.gender = builder.gender;
        this.isMarried = builder.isMarried;
        this.email = builder.email;
        this.phone = builder.phone;
        this.occupation = builder.occupation;
        this.firstLicenseAtAge = builder.firstLicenseAtAge;
        this.address = builder.address;
        this.drivingHistory = builder.drivingHistory;
        this.priorCarrier = builder.priorCarrier;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Builder {

        private String licenseNum;
        private String licenseState;
        private LocalDate licenseExpiryDate;
        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private Gender gender;
        private boolean isMarried;
        private String email;
        private String phone;
        private String occupation;
        private Integer firstLicenseAtAge;
        private Address address;
        private List<DrivingHistory> drivingHistory;
        private String priorCarrier;

        public Builder() {

        }

        public Builder withLicenseNum(String licenseNum) {
            this.licenseNum = licenseNum;
            return this;
        }

        public Builder withLicenseState(String licenseState) {
            this.licenseState = licenseState;
            return this;
        }

        public Builder withLicenseExpiryDate(LocalDate licenseExpiryDate) {
            this.licenseExpiryDate = licenseExpiryDate;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder withGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder withIsMarried(boolean isMarried) {
            this.isMarried = isMarried;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder withOccupation(String occupation) {
            this.occupation = occupation;
            return this;
        }

        public Builder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder withFirstLicenseAtAge(Integer firstLicenseAtAge) {
            this.firstLicenseAtAge = firstLicenseAtAge;
            return this;
        }

        public Builder withDrivingHistory(List<DrivingHistory> drivingHistory) {
            this.drivingHistory = drivingHistory;
            return this;
        }

        public Builder withPriorCarrier(String priorCarrier) {
            this.priorCarrier = priorCarrier;
            return this;
        }

        public Driver build() {
            validate();
            return new Driver(this);
        }

        private void validate() {
            checkNotNull(licenseNum, "licenseNum may not be null");
            checkNotNull(lastName, "lastName may not be blank");
            checkNotNull(birthDate, "birthDate may not be null");
            checkNotNull(address, "address may not be null");
        }
    }

    public String getLicenseNum() {
        return licenseNum;
    }

    public String getLicenseState() {
        return licenseState;
    }

    public LocalDate getLicenseExpiryDate() {
        return licenseExpiryDate;
    }

    public List<DrivingHistory> getDrivingHistory() {
        return drivingHistory;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public boolean getIsMarried() {
        return isMarried;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getOccupation() {
        return occupation;
    }

    public Address getAddress() {
        return address;
    }

    public Integer getFirstLicenseAtAge() {
        return firstLicenseAtAge;
    }

    public String getPriorCarrier() {
        return priorCarrier;
    }

    public Policy getPolicy() {
        return policy;
    }

    // Deep copy
    public Driver deepCopyWithDrivingHistory(List<DrivingHistory> _drivingHistory) {
        Driver d = new Driver.Builder().withLicenseNum(licenseNum).withBirthDate(birthDate).withFirstName(firstName)
                .withLastName(lastName).withLicenseExpiryDate(licenseExpiryDate).withGender(gender)
                .withEmail(email).withPhone(phone).withOccupation(occupation)
                .withFirstLicenseAtAge(firstLicenseAtAge).withIsMarried(isMarried).withAddress(address)
                .withDrivingHistory(_drivingHistory) //~
                .build();
        copyLinks(d);
        return d;
    }

    private void copyLinks(Driver d) {
        for (Link link : getLinks()) {
            d.addLink(link);
        }
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("lastName", lastName).add("birthDate", birthDate)
                .add("occupation", occupation).add("licenseNum", licenseNum).toString();
    }

    @Override
    public int compareTo(Driver that) {
        return ComparisonChain.start().compare(this.licenseNum, that.licenseNum, Ordering.natural().nullsLast()).result();
    }

    @Override
    public boolean equals(Object obj) {
        Driver that = (Driver) obj;
        return Objects.equal(this.licenseNum, that.licenseNum);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(licenseNum);
    }

}