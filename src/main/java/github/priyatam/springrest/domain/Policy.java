package github.priyatam.springrest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import github.priyatam.springrest.utils.Link;
import org.hibernate.annotations.Index;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = Policy.Builder.class)
@Entity
@NamedQuery(name = "Policy.FIND_BY_POLICYNUM", query = "select o from Policy o where o.policyNum = :policyNum")
public final class Policy extends BaseDomain implements Comparable<Policy>, Serializable {

	private static final long serialVersionUID = 2144310940122088721L;

    // key
    @Column(unique=true)
    @Index(name = "policyNum_ind")
    private final String policyNum;

	private final String company;
	private final LocalDate effectiveDate;
	private final String state;
	private final Integer quote;
	private final LocalDate expiryDate;
	private final Integer term;
	private final String declineReason;
	private final String agency;

    @OneToMany(mappedBy = "policy", cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
	private final List<Driver> drivers;

    @OneToMany(mappedBy = "policy", cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    private final List<Vehicle> vehicles;

	private Policy(Builder builder) {
		this.policyNum = builder.policyNum;
		this.company = builder.company;
		this.effectiveDate = builder.effectiveDate;
		this.state = builder.state;
		this.quote = builder.quote;
		this.expiryDate = builder.expiryDate;
		this.term = builder.term;
		this.declineReason = builder.declineReason;
		this.agency = builder.agency;
		this.drivers = builder.drivers;
		this.vehicles = builder.vehicles;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Builder {

		private String policyNum;
		private String company;
		private LocalDate effectiveDate;
		private String state;
		private Integer quote;
		private LocalDate expiryDate;
		private Integer term;
		private String declineReason;
		private String agency;
		private List<Driver> drivers;
		private List<Vehicle> vehicles;

		public Builder() {

		}

		public Builder withPolicyNum(String policyNum) {
			this.policyNum = policyNum;
			return this;
		}

		public Builder withCompany(String company) {
			this.company = company;
			return this;
		}

		public Builder withEffectiveDate(LocalDate effectiveDate) {
			this.effectiveDate = effectiveDate;
			return this;
		}

		public Builder withState(String state) {
			this.state = state;
			return this;
		}

		public Builder withQuote(Integer quote) {
			this.quote = quote;
			return this;
		}

		public Builder withExpiryDate(LocalDate expiryDate) {
			this.expiryDate = expiryDate;
			return this;
		}

		public Builder withTerm(Integer term) {
			this.term = term;
			return this;
		}

		public Builder withDeclineReason(String declineReason) {
			this.declineReason = declineReason;
			return this;
		}

		public Builder withAgency(String agency) {
			this.agency = agency;
			return this;
		}

		public Builder withDrivers(List<Driver> drivers) {
			this.drivers = drivers;
			return this;
		}

		public Builder withVehicles(List<Vehicle> vehicles) {
			this.vehicles = vehicles;
			return this;
		}

		public Policy build() {
			validate();
			return new Policy(this);
		}

	    private void validate() {
			checkNotNull(effectiveDate, "effectiveDate may not be null");
			checkNotNull(expiryDate, "expiryDate may not be null");
			checkNotNull(vehicles, "vehicles may not be null");

			// Business Rules
			checkArgument(vehicles.size() > 0, "vehicles can't be empty");
			checkArgument(effectiveDate.isAfter(new LocalDate()), "effectiveDate can't be in the past");
		}
	}

	public String getPolicyNum() {
		return policyNum;
	}

	public String getCompany() {
		return company;
	}

	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

	public String getState() {
		return state;
	}

	public Integer getQuote() {
		return quote;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public Integer getTerm() {
		return term;
	}

	public String getDeclineReason() {
		return declineReason;
	}

	public String getAgency() {
		return agency;
	}

	public List<Driver> getDrivers() {
		return drivers;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}


    // Deep copy
    private void copyLinks(Policy p) {
        for (Link link : getLinks()) {
            p.addLink(link);
        }
    }

    public Policy deepCopyWithDrivers(List<Driver> _drivers) {
        Policy p = new Policy.Builder()
                .withPolicyNum(policyNum)
                .withEffectiveDate(effectiveDate)
                .withExpiryDate(expiryDate)
                .withTerm(term)
                .withCompany(company)
                .withState(state)
                .withQuote(quote)
                .withDeclineReason(declineReason)
                .withAgency(agency)
                .withDrivers(_drivers)   //~
                .withVehicles(vehicles)
                .build();
        copyLinks(p);
        return p;
    }

    public Policy deepCopyWithVehicles(List<Vehicle> _vehicles) {
        Policy p = new Policy.Builder()
                .withPolicyNum(policyNum)
                .withEffectiveDate(effectiveDate)
                .withExpiryDate(expiryDate)
                .withTerm(term)
                .withCompany(company)
                .withState(state)
                .withQuote(quote)
                .withDeclineReason(declineReason)
                .withAgency(agency)
                .withDrivers(drivers)
                .withVehicles(_vehicles)  //~
                .build();
        copyLinks(p);
        return p;
    }

    public Policy deepCopyWithQuote(Integer _quote) {
        Policy p = new Policy.Builder()
                .withPolicyNum(policyNum)
                .withEffectiveDate(effectiveDate)
                .withExpiryDate(expiryDate)
                .withTerm(term)
                .withCompany(company)
                .withState(state)
                .withQuote(_quote)  //~
                .withDeclineReason(declineReason)
                .withAgency(agency)
                .withDrivers(drivers)
                .withVehicles(vehicles)
                .build();
        copyLinks(p);
        return p;
    }

    public Policy deepCopyWithPolicyNum(String _policyNum) {
        Policy p = new Policy.Builder()
                .withPolicyNum(_policyNum)   //~
                .withEffectiveDate(effectiveDate)
                .withExpiryDate(expiryDate)
                .withTerm(term)
                .withCompany(company)
                .withState(state)
                .withQuote(quote)
                .withDeclineReason(declineReason)
                .withAgency(agency)
                .withDrivers(drivers)
                .withVehicles(vehicles)
                .build();
        copyLinks(p);
        return p;
    }

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("policyNum", policyNum).add("effectiveDate", effectiveDate)
				.add("expiryDate", expiryDate).add("agency", agency).toString();
	}

	@Override
	public int compareTo(Policy that) {
		return ComparisonChain.start().compare(this.policyNum, that.policyNum, Ordering.natural().nullsLast()).result();
	}

	@Override
	public boolean equals(Object obj) {
		Policy that = (Policy) obj;
		return Objects.equal(this.policyNum, that.policyNum);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(policyNum);
	}

}