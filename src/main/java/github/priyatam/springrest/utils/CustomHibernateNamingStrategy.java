package github.priyatam.springrest.utils;

import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * A Custom Naming Strategy with standard Database naming conventions Add this class in
 * <i>persistence.xml</i>, as hibernate.ejb.naming_strategy property
 * 
 */
public class CustomHibernateNamingStrategy
	extends ImprovedNamingStrategy {

	private static final long serialVersionUID = 4674532118559124654L;

	@Override
	public String foreignKeyColumnName(
		String propertyName,
		String propertyEntityName,
		String propertyTableName,
		String referencedColumnName ) {

		return columnName( propertyName ) + "_id";
	}

	@Override
	public String classToTableName(
		String className ) {

		return toLowerCase( pluralize( super.classToTableName( className ) ) );
	}

	// IMPORTANT! To ensure compatibility across windows and unix, convert all table
	// names to lowercase
	private String toLowerCase(
		String name ) {

		return name.toLowerCase();
	}

	private String pluralize(
		String name ) {

		StringBuilder p = new StringBuilder( name );
		if ( name.endsWith( "y" ) ) {
			p.deleteCharAt( p.length() - 1 );
			p.append( "ies" );
		} else {
			p.append( 's' );
		}
		return p.toString();
	}
}