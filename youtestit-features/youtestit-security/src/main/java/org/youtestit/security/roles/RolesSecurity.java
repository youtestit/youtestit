package org.youtestit.security.roles;

import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.Secures;
import org.youtestit.datamodel.dao.UserDAO;
import org.youtestit.datamodel.entity.Group;
import org.youtestit.datamodel.entity.User;

@Named
public class RolesSecurity {
    
    @Inject
    private User currentUser;
    
    public @Secures @Owner boolean ownerChecker(Identity identity) {
        return !(identity == null || identity.getUser() == null);
    }   
    
    
    
    public @Secures @NotLoggedIn boolean notLoggedInChecker(Identity identity) {
        return identity == null || identity.getUser() == null;
    }
    
    
    
    public @Secures @Admin boolean adminChecker(Identity identity) {
        boolean result = false;
        if(identity!=null){
            if("admin".equals(identity.getAuthenticatorName())){
                result = true;
            }else{
                
                for(Group group :currentUser.getGroups()){
                    if("Administrators".equals(group.getName())){
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }   
    
    
       
}
