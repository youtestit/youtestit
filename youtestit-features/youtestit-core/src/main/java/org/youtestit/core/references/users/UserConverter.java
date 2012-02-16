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

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.datamodel.dao.UserDAO;
import org.youtestit.datamodel.entity.User;

/**
 * UserConverter
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since 16 févr. 2012
 */
@FacesConverter("userConverter")
public class UserConverter implements Converter {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    @Inject
    private UserDAO userDAO;
    
    @Inject
    private Logger log;
    

    // =========================================================================
    // OVERRIDES
    // =========================================================================
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component,
            String submittedValue) {
        User user = null;
        
        if (submittedValue == null
                || StringUtils.isEmpty(submittedValue.trim())) {
            return user;
        } else {
            
            try {
                user = userDAO.getUserByLogin(submittedValue.trim());
            } catch (ClientException e) {
                log.error(e);
            }
        }
        
        return user;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component,
            Object value) {
        boolean isUser = value instanceof User;
        if (value == null || !isUser) {
            return "";
        } else {
            User user = (User) value;
            return user.getLogin();
        }

    }

}
