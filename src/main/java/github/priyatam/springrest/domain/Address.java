package github.priyatam.springrest.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="addresses")
@NamedQuery(name = "Address.findByAddrLine1CityStateZip", query = "select o from Address o where o.addrLine1 = :addrLine1" +
        " and o.city = :city and o.state = :state and o.zip = :zip")
public class Address extends BaseDomain implements Comparable<Address>, Serializable {

    private static final long serialVersionUID = 2474081809067118710L;

    public enum AddressType {
        RENTAL, HOMEOWNER, BUSINESS
    }

    private final String addrLine1;
    private final String addrLine2;
    private final String city;
    private final String state;
    private final String zip;

    @Enumerated(EnumType.STRING)
    private final AddressType type;

    // Default constructor used by Hibernate
    private Address() {
       this.addrLine1 = null;
       this.addrLine2 = null;
       this.city = null;
       this.state = null;
       this.zip = null;
       this.type = null;
    }
    
    @JsonCreator
    public Address(@JsonProperty("addrLine1") String addrLine1, @JsonProperty("addrLine2") String addrLine2,
                   @JsonProperty("city") String city, @JsonProperty("state") String state,
                   @JsonProperty("zip") String zip, @JsonProperty("type") AddressType type) {
        this.addrLine1 = addrLine1;
        this.addrLine2 = addrLine2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.type = type;
    }

    public String getAddrLine1() {
        return addrLine1;
    }

    public String getAddrLine2() {
        return addrLine2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public AddressType getType() {
        return type;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("addrLine1", addrLine1).add("city", city).add("state", state)
                .add("zip", zip).toString();
    }

    @Override
    public int compareTo(Address that) {
        return ComparisonChain.start().compare(this.addrLine1, that.addrLine1).compare(this.city, that.city)
                .compare(this.city, that.city).compare(this.zip, that.zip, Ordering.natural().nullsLast()).result();

    }

    @Override
    public boolean equals(Object obj) {
        Address that = (Address) obj;
        return Objects.equal(this.addrLine1, that.addrLine1) && Objects.equal(this.city, that.city)
                && Objects.equal(this.state, that.state) && Objects.equal(this.zip, that.zip);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(addrLine1, city, state, zip);
    }

}