package org.youtestit.security.identification;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.jboss.seam.security.events.PostLoggedOutEvent;
import org.youtestit.datamodel.entity.User;

@Named
@SessionScoped
public class CurrentUserManager implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long  serialVersionUID = 3282524862213807171L;

    private User               currentUser;

    @Inject
    private HttpServletRequest httpRequest;


    @Produces
    @Authenticated
    @Named("currentUser")
    public User getCurrentAccount() {
        return currentUser;
    }

    public void onLogin(@Observes
    @Authenticated
    User user, HttpServletRequest request) {
        currentUser = user;
        request.getSession().setMaxInactiveInterval(3600);
    }


    public void onLogout(@Observes
    final PostLoggedOutEvent event) {
        httpRequest.getSession().invalidate();
    }

}
