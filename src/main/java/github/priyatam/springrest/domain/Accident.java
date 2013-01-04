package github.priyatam.springrest.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.annotations.Index;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import java.io.Serializable;

@Entity
public class Accident extends BaseDomain implements Serializable {

    public enum AccidentType {
        MINOR, COLLISSION, TOTALED
    }

    // key
    @Column(unique = true)
    @Index(name = "accidentNameIndex")
    private final String name;

    private final LocalDateTime incidentTime;
    private final boolean thirdParyOffence;

    @Enumerated(EnumType.STRING)
    private final AccidentType type;

    @JsonCreator
    public Accident(@JsonProperty("name") String name, @JsonProperty("incidentTime") LocalDateTime incidentTime,
                    @JsonProperty("thirdParyOffence") boolean thirdParyOffence,
                    @JsonProperty("type") AccidentType type) {
        this.name = name;
        this.incidentTime = null;
        this.thirdParyOffence = thirdParyOffence;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getIncidentTime() {
        return incidentTime;
    }

    public boolean isThirdParyOffence() {
        return thirdParyOffence;
    }

    public AccidentType getType() {
        return type;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("incidentTime", incidentTime).add("type", type).toString();
    }
}