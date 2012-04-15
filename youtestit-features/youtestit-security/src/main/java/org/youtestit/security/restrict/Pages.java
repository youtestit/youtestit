package org.youtestit.security.restrict;

import org.jboss.seam.faces.event.PhaseIdType;
import org.jboss.seam.faces.rewrite.FacesRedirect;
import org.jboss.seam.faces.security.AccessDeniedView;
import org.jboss.seam.faces.security.LoginView;
import org.jboss.seam.faces.security.RestrictAtPhase;
import org.jboss.seam.faces.view.config.ViewConfig;
import org.jboss.seam.faces.view.config.ViewPattern;
import org.youtestit.security.roles.Admin;
import org.youtestit.security.roles.Owner;



@ViewConfig
public interface Pages {
    String LOGIN_PAGE="/login";

    static enum Pages2 {
        @ViewPattern("/subscrib")
        SUBSCRIB,
        
        @ViewPattern("/login*")
        LOGIN,
        
        @FacesRedirect
        @ViewPattern("/home*")
        @RestrictAtPhase(PhaseIdType.RENDER_RESPONSE)
        @LoginView(LOGIN_PAGE)
        @Owner
        HOME,
        
        @ViewPattern("/admin*")
        @RestrictAtPhase(PhaseIdType.RENDER_RESPONSE)
        @LoginView("/login")
        @AccessDeniedView("/denied")
        @Admin
        ADMIN,
        
        @ViewPattern("/app-project*")
        @RestrictAtPhase(PhaseIdType.RENDER_RESPONSE)
        @LoginView(LOGIN_PAGE)
        @Owner
        ALL;

    }

}