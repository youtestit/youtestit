package org.youtestit.actions.user.controllers.home;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;


@SessionScoped
@Named
public class Home implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4746673041363469036L;

    @Inject
    private Logger log;


    public void select(final String test) {
        log.info(">>>> test :");
    }

    
    public String getTest() {   
        log.info("<<<<< get TEST <<<<<<<");
       
        return "Hello";
    }

    public void setTest() {
        log.info("<<<<< set TEST <<<<<<<");
    }


    

}
