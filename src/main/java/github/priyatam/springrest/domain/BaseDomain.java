package github.priyatam.springrest.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import github.priyatam.springrest.utils.Link;

import javax.persistence.*;

/**
 * Adds id, version, and Heateoas Links to a domain
 * 
 */
@MappedSuperclass
public abstract class BaseDomain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    @JsonIgnore
    private Long id;

    @Version
    @JsonIgnore
    private Integer version;

    @Embedded
	private List<Link> links = new LinkedList<Link>();

	public void addLink(Link link) {
		this.links.add(link);
	}

	public List<Link> getLinks() {
        if (links == null) {
            links = new LinkedList<Link>();
        }
		return links;
	}

}
