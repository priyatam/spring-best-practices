package github.priyatam.springrest.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * LinkBuilder for constructing links in BaseDomain
 * 
 */
public class LinkBuilder {

	public static Link build(String requestMapping, String name, String rel) {
		ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentServletMapping();
		String root = builder.build().toString();
		String href = root + requestMapping;
		return new Link(name, href, rel);
	}

	public static Link build(String requestMapping, String name) {
		ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentServletMapping();
		String root = builder.build().toString();
		String href = root + requestMapping;
		return new Link(name, href, Link.REL_SELF);
	}
}
