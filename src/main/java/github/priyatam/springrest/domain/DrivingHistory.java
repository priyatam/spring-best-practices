package github.priyatam.springrest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Objects;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@JsonDeserialize(builder = DrivingHistory.Builder.class)
@Entity
@NamedQuery(name = "DrivingHistory.FIND_BY_DRIVER", query = "select o from DrivingHistory o " +
        "where o.driver.licenseNum = :licenseNum")
public final class DrivingHistory extends BaseDomain implements Serializable {

    private static final long serialVersionUID = 6466627416634621081L;

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

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn
    private final List<Accident> accidents;

    private DrivingHistory(Builder builder) {
        this.driverLicense = builder.driverLicense;
        this.purchaseOrLeasedDate = builder.purchaseOrLeasedDate;
        this.annualMileage = builder.annualMileage;
        this.isGarageParked = builder.isGarageParked;
        this.isPrimaryOperator = builder.isPrimaryOperator;
        this.accidents = builder.accidents;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Builder {

        private String driverLicense;
        private LocalDate purchaseOrLeasedDate;
        private Integer annualMileage;
        private Boolean isGarageParked;
        private Boolean isPrimaryOperator;
        private List<Accident> accidents;

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

        public Builder withAccidents(List<Accident> accidents) {
            this.accidents = accidents;
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

    public List<Accident> getAccidents() {
        return accidents;
    }

    // Accident Info


    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("driverLicense", driverLicense)
                .add("purchaseOrLeasedDate", purchaseOrLeasedDate).add("annualMileage", annualMileage)
                .add("accidents", accidents).toString();
    }
}
