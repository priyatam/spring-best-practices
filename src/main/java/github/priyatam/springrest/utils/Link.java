package github.priyatam.springrest.utils;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Link implements Serializable {

	private static final long serialVersionUID = 2688912817762587701L;

	public static final String REL_SELF = "self";
	public static final String REL_PARENT = "parent";	
	public static final String REL_FIRST = "first";
	public static final String REL_PREVIOUS = "previous";
	public static final String REL_NEXT = "next";
	public static final String REL_LAST = "last";

	private String name;
	private String rel;
	private String href;

	/**
	 * Creates a new link to the given URI with the self rel.
	 * 
	 * @see #REL_SELF
	 * @param href must not be {@literal null} or empty.
	 */
	public Link(String href) {
		this("", href, REL_SELF);
	}

	/**
	 * Creates a new {@link com.github.priyatam.springrest.utils.Link} to the given URI with the given rel.
	 * 
	 * @param href must not be {@literal null} or empty.
	 * @param rel must not be {@literal null} or empty.
	 */
	public Link(String name, String href, String rel) {
		this.name = name;
		this.href = href;
		this.rel = rel;
	}

	/**
	 * Empty constructor required by the marshalling framework.
	 */
	protected Link() {

	}
	
	public String getName() {
		return name;
	}

	public String getHref() {
		return href;
	}

	public String getRel() {
		return rel;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Link)) {
			return false;
		}

		Link that = (Link) obj;

		return this.href.equals(that.href) && this.rel.equals(that.rel);
	}

	@Override
	public int hashCode() {
		int result = 17;
		result += 31 * href.hashCode();
		result += 31 * rel.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return String.format("{ rel : %s, href : %s }", rel, href);
	}
}