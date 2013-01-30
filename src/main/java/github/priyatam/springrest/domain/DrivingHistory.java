package github.priyatam.springrest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Objects;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.io.Serializable;

@JsonDeserialize(builder = DrivingHistory.Builder.class)
@Entity
@NamedQuery(name = "DrivingHistory.FIND_BY_DRIVER", query = "select o from DrivingHistory o " +
        "where o.driver.licenseNum = :licenseNum")
public final class DrivingHistory extends BaseDomain implements Serializable {

    private static final long serialVersionUID = 6466627416634621081L;

    public enum AccidentType {
        MINOR, COLLISION, TOTALED
    }

    // Key
    @Transient
    private final String driverLicense;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "driverId")
    @JsonIgnore
    private Driver driver;

    private final LocalDate purchaseOrLeasedDate;
    private final Integer annualMileage;
    private final Boolean isGarageParked;
    private final Boolean isPrimaryOperator;
    private final Boolean isAccident;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private final LocalDateTime accidentTime;

    private final Boolean isThirdPartyOffence;

    // Default constructor used by Hibernate
    private DrivingHistory() {
        this.driverLicense = null;
        this.purchaseOrLeasedDate = null;
        this.annualMileage = null;
        this.isGarageParked = null;
        this.isPrimaryOperator = null;
        this.isAccident = null;
        this.accidentTime = null;
        this.isThirdPartyOffence = null;  
    }
    
    private DrivingHistory(Builder builder) {
        this.driverLicense = builder.driverLicense;
        this.purchaseOrLeasedDate = builder.purchaseOrLeasedDate;
        this.annualMileage = builder.annualMileage;
        this.isGarageParked = builder.isGarageParked;
        this.isPrimaryOperator = builder.isPrimaryOperator;
        this.isAccident = builder.isAccident;
        this.accidentTime = builder.accidentTime;
        this.isThirdPartyOffence = builder.isThirdPartyOffence;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Builder {

        private String driverLicense;
        private LocalDate purchaseOrLeasedDate;
        private Integer annualMileage;
        private Boolean isGarageParked;
        private Boolean isPrimaryOperator;
        private Boolean isAccident;
        private LocalDateTime accidentTime;
        private Boolean isThirdPartyOffence;

        public Builder() {

        }

        public Builder withDriverLicense(String driverLicense) {
            this.driverLicense = driverLicense;
            return this;
        }

        public Builder withPurchaseOrLeasedDate(LocalDate purchaseOrLeasedDate) {
            this.purchaseOrLeasedDate = purchaseOrLeasedDate;
            return this;
        }

        public Builder withAnnualMileage(Integer annualMileage) {
            this.annualMileage = annualMileage;
            return this;
        }

        public Builder withIsGarageParked(boolean isGarageParked) {
            this.isGarageParked = isGarageParked;
            return this;
        }

        public Builder withIsPrimaryOperator(boolean isPrimaryOperator) {
            this.isPrimaryOperator = isPrimaryOperator;
            return this;
        }

        public Builder withIsAccident(boolean isAccident) {
            this.isAccident = isAccident;
            return this;
        }

        public Builder withAccidentTime(LocalDateTime accidentTime) {
            this.accidentTime = accidentTime;
            return this;
        }

        public Builder withIsThirdParyOffence(boolean isThirdPartyOffence) {
            this.isThirdPartyOffence = isThirdPartyOffence;
            return this;
        }

        public DrivingHistory build() {
            validate();
            return new DrivingHistory(this);
        }

        private void validate() {

        }
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public LocalDate getPurchaseOrLeasedDate() {
        return purchaseOrLeasedDate;
    }

    public Integer getAnnualMileage() {
        return annualMileage;
    }

    public Boolean getIsGarageParked() {
        return isGarageParked;
    }

    public Boolean getIsPrimaryOperator() {
        return isPrimaryOperator;
    }

    public Driver getDriver() {
        return driver;
    }

    public Boolean getGarageParked() {
        return isGarageParked;
    }

    public Boolean getPrimaryOperator() {
        return isPrimaryOperator;
    }

    public Boolean getAccident() {
        return isAccident;
    }

    public LocalDateTime getAccidentTime() {
        return accidentTime;
    }

    public Boolean getThirdPartyOffence() {
        return isThirdPartyOffence;
    }


    // Accident Info


    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("driverLicense", driverLicense)
                .add("purchaseOrLeasedDate", purchaseOrLeasedDate).add("annualMileage", annualMileage)
                .add("isAccident", isAccident).toString();
    }
}
