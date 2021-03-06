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

import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.commons.lang.StringUtils;
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
    public List<SelectItem> suggestUsers(String query){
        log.debug("findUsers");
        List<SelectItem> result = new ArrayList<SelectItem>(0);
        if(query==null ||  StringUtils.isEmpty(query.trim())){
            return result;
        }
        
        try {
            List<User> users = userDAO.getUsersByNameOrLogin(query);
            for(User item:users){
                result.add(new SelectItem(item,String.format("%s %s", item.getFirstname(),item.getLastname())));
            }
        } catch (ClientException e) {
            log.error(e);
        }
        
        return result;
    }

    
    

}
