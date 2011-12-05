package org.apache.youtestit.core.actions.home;


import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SessionScoped
@Named
public class Home implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= -4746673041363469036L;

	private final  static Logger LOGGER = LoggerFactory.getLogger(Home.class);



	public void select(final String test) {
		LOGGER.info(">>>> test :{}", test);
	}



	public String getTest() {
		LOGGER.info("<<<<< get TEST <<<<<<<");
		return "Hello";
	}



	public void setTest() {
		LOGGER.info("<<<<< set TEST <<<<<<<");
	}

}

