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
package org.youtestit.core.references.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.jboss.logging.Logger;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.datamodel.dao.UserDAO;
import org.youtestit.datamodel.entity.User;

/**
 * UsersRefDatas
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since 15 févr. 2012
 */
@Singleton
@Named
public class UsersRefDatas implements Serializable {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7242495471888743370L;

    /** The log. */
    @Inject
    private Logger log;
    
    @Inject
    private UserDAO userDAO;
    
    // =========================================================================
    // METHODS
    // =========================================================================
    
    
    /**
     * Find user on name or login.
     *
     * @param value the name or login
     * @return list of user finded 
     * @throws ClientException if an error is occur
     */
    public List<User> findUsers(String value) throws ClientException{
        log.debug("findUsers");
        if(value==null){
            return new ArrayList<User>(0);
        }
        
        return userDAO.getUsersByNameOrLogin(value);
    }

}
