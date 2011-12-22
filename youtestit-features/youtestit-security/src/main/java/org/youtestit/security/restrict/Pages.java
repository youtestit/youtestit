package org.youtestit.security.restrict;

import org.jboss.seam.faces.event.PhaseIdType;
import org.jboss.seam.faces.security.LoginView;
import org.jboss.seam.faces.security.RestrictAtPhase;
import org.jboss.seam.faces.view.config.ViewConfig;
import org.jboss.seam.faces.view.config.ViewPattern;
import org.youtestit.security.roles.Admin;
import org.youtestit.security.roles.Owner;



@ViewConfig
public interface Pages {

    static enum Pages2 {
        
        @ViewPattern("/subscrib")
        SUBSCRIB,
        
        @ViewPattern("/login")
        LOGIN,
        
        @ViewPattern("/home.xhtml")
        @RestrictAtPhase(PhaseIdType.RENDER_RESPONSE)
        @LoginView("/login")
        @Owner
        HOME,
        
        
        @ViewPattern("/app/admin/*")
        @RestrictAtPhase(PhaseIdType.RENDER_RESPONSE)
        @LoginView("/login")
        @Admin
        ADMIN,
        
        @ViewPattern("/app/*")
        @RestrictAtPhase(PhaseIdType.RENDER_RESPONSE)
        @LoginView("/login")
        @Owner
        ALL;

    }

}