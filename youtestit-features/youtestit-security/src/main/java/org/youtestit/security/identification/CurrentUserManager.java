/*
 *   YouTestit source code:
 *   ======================
 *   Licensed to the Apache Software Foundation (ASF) under one or more
 *   contributor license agreements.  See the NOTICE file distributed with
 *   this work for additional information regarding copyright ownership.
 *   The ASF licenses this file to You under the Apache License, Version 2.0
 *   (the "License"); you may not use this file except in compliance with
 *   the License.  You may obtain a copy of the License at
 *  
        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *   Links:
 *   ======
 *   Homepage : http://www.youtestit.org
 *   Git      : https://github.com/youtestit
 */
package org.youtestit.security.identification;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.jboss.seam.security.events.PostLoggedOutEvent;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.datamodel.entity.User;
import org.youtestit.security.roles.RolesValidator;

/**
 * The Class CurrentUserManager.
 */
@Named
@SessionScoped
public class CurrentUserManager implements Serializable {

    

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long  serialVersionUID = 3282524862213807171L;

    /** The log. */
    @Inject
    private Logger      log;
    
    /** The current user. */
    private User               currentUser;

    /** The http request. */
    @Inject
    private HttpServletRequest httpRequest;
    

    /** The roles validator. */
    @Inject
    private RolesValidator     rolesValidator;

    /** The is admin. */
    private Boolean            admin          = null;

    /** The Constant SESSION_TIMEOUT. */
    //TODO: add in properties...
    private static final int SESSION_TIMEOUT = 3600;
    
    // =========================================================================
    // METHODS
    // =========================================================================

    /**
     * Gets the current account.
     *
     * @return the current account
     */
    @Produces
    @Authenticated
    @Named("currentUser")
    public User getCurrentAccount() {
        return currentUser;
    }

    /**
     * Checks if is admin.
     *
     * @return true, if is admin
     * @throws ClientException the client exception
     */
    @Produces
    @Named("administrator")
    public boolean isAdmin() throws ClientException {
        log.debug("isAdmin");
        if (admin == null) {
            admin = rolesValidator.isAdmin(currentUser.getLogin());
        }
        return admin;
    }

    
    
    /**
     * On login.
     *
     * @param user the user
     * @param request the request
     * @throws ClientException 
     */
    public void onLogin(@Observes
    @Authenticated
    User user, HttpServletRequest request) throws ClientException {
        log.debug("onLogin");
        currentUser = user;
        request.getSession().setMaxInactiveInterval(SESSION_TIMEOUT);
    }


  
    /**
     * On logout.
     *
     * @param event the event
     */
    public void onLogout(@Observes
    final PostLoggedOutEvent event) {
        log.debug("onLogout");
        httpRequest.getSession().invalidate();
    }

}
