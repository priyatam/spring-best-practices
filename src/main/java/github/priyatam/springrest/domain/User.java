package github.priyatam.springrest.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import org.hibernate.annotations.Index;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@Entity
@NamedQuery(name = "User.FIND_BY_USERNAME", query = "select o from User o where o.username = :username")
public class User extends BaseDomain implements Comparable<User>, Serializable {

    @Column(unique = true)
    @Index(name = "usernameIndex")
    private String username;

    @JsonIgnore
    private String password;

    private String role;

    @JsonCreator
    public User(@JsonProperty("username") String username, @JsonProperty("password") String password,
                @JsonProperty("role")  String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("username", username).toString();
    }

    @Override
    public int compareTo(User that) {
        return ComparisonChain.start().compare(this.username, that.username, Ordering.natural().nullsLast()).result();
    }

    @Override
    public boolean equals(Object obj) {
        User that = (User) obj;
        return Objects.equal(this.username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }
}
