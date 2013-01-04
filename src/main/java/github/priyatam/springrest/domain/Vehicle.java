package github.priyatam.springrest.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import org.hibernate.annotations.Index;

import javax.persistence.*;
import java.io.Serializable;

@JsonDeserialize(builder = Vehicle.Builder.class)
@Entity
@NamedQueries({
        @NamedQuery(
                name = "Vehicle.FIND_BY_VIN", query = "select o from Vehicle o where o.vin = :vin"),
        @NamedQuery(
                name = "Vehicle.FIND_BY_POLICYNUM", query = "select o from Vehicle o where o.policy.policyNum = :policyNum")
})
public final class Vehicle extends BaseDomain implements Comparable<Vehicle>, Serializable {

    private static final long serialVersionUID = 2086756361985950564L;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "policyId")
    @JsonIgnore
    private Policy policy;

    public enum VehicleOwnerType {
        LEASED, OWNED, BUSINESS
    }

    // Key
    @Column(unique = true)
    @Index(name = "vinIndex")
    private final String vin;

    private final String make;
    private final String model;
    private final Integer year;
    private final String plateNum;
    private final Integer odomoterReading;

    @Enumerated(EnumType.STRING)
    private final VehicleOwnerType ownerType;

    private Vehicle(Builder builder) {
        this.vin = builder.vin;
        this.plateNum = builder.plateNum;
        this.make = builder.make;
        this.model = builder.model;
        this.year = builder.year;
        this.ownerType = builder.ownerType;
        this.odomoterReading = builder.odomoterReading;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Builder {

        private String vin;
        private String plateNum;
        private String make;
        private String model;
        private Integer year;
        private VehicleOwnerType ownerType;
        private Integer odomoterReading;

        public Builder() {

        }

        public Builder withVin(String vin) {
            this.vin = vin;
            return this;
        }

        public Builder withPlateNum(String plateNum) {
            this.plateNum = plateNum;
            return this;
        }

        public Builder withMake(String make) {
            this.make = make;
            return this;
        }

        public Builder withModel(String model) {
            this.model = model;
            return this;
        }

        public Builder withYear(Integer year) {
            this.year = year;
            return this;
        }

        public Builder withOwnerType(VehicleOwnerType ownerType) {
            this.ownerType = ownerType;
            return this;
        }

        public Builder withOdomoterReading(Integer odomoterReading) {
            this.odomoterReading = odomoterReading;
            return this;
        }

        public Vehicle build() {
            validate();
            return new Vehicle(this);
        }

        public Vehicle buildAsKey(String vin) {
            this.vin = vin;
            return new Vehicle(this);
        }

        private void validate() {
            Preconditions.checkNotNull(vin, "vin may not be null");
        }
    }

    public String getVin() {
        return vin;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public Integer getOdomoterReading() {
        return odomoterReading;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public VehicleOwnerType getOwnerType() {
        return ownerType;
    }

    public Policy getPolicy() {
        return policy;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("vin", vin).add("plateNum", plateNum).add("year", year)
                .add("odomoterReading", odomoterReading).toString();
    }

    @Override
    public int compareTo(Vehicle that) {
        return ComparisonChain.start().compare(this.vin, that.vin)
                .compare(this.plateNum, that.plateNum, Ordering.natural().nullsLast()).result();
    }

    @Override
    public boolean equals(Object obj) {
        Vehicle that = (Vehicle) obj;
        return Objects.equal(this.vin, that.vin);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(vin);
    }

}
