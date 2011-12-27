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
package org.youtestit.actions.admin.controllers.dashboard;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.datamodel.dao.UserDAO;
import org.youtestit.datamodel.entity.User;


/**
 * AdminDashboard
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 27, 2011
 */
@ViewScoped
@Named
public class DashboardAdmin implements Serializable {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 631638260318322100L;

    @Inject
    private Logger            log;
    
    @Inject
    private UserDAO       userDAO;
    
    @PersistenceContext
    private EntityManager     entityManager;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================


    // =========================================================================
    // METHODS
    // =========================================================================

    public List<User> getUserAccountToEnable() throws ClientException {
        log.debug("userAccountToEnable");
        return userDAO.getUsersAccountWaittingEnable();
    }
    
    public void activeUser(User user) throws ClientException {
        if(user!=null){
            user.setEnable(true);
            entityManager.merge(user);
        }
    }


    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================
}
