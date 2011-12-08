package org.youtestit.commons.utils.io.filters;


import java.io.File;
import java.io.FilenameFilter;
import java.util.Locale;


/**
 * The Class XhtmlFilter.
 */
public class XhtmlFilter implements FilenameFilter {

	/** The Constant XHTML_TYPE. */
	private static final String	XHTML_TYPE	= ".XHTML";



	/**
	 * {@inheritDoc}
	 */
	public boolean accept(File folder, String name) {
		boolean result = false;
		if (name != null) {
			final String cleanName = name.toUpperCase(Locale.US);
			result = cleanName.endsWith(XHTML_TYPE);
		}

		return result;
	}

}
