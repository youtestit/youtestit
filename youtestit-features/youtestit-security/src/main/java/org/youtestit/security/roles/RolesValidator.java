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
package org.youtestit.security.roles;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.youtestit.commons.utils.Constants;
import org.youtestit.commons.utils.ConstantsProperties;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.datamodel.dao.UserDAO;
import org.youtestit.datamodel.entity.User;


/**
 * RolesValidator
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 27, 2011
 */
@Singleton
@Named
public class RolesValidator implements Serializable {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long         serialVersionUID = -2901557595265914689L;


    private final ConstantsProperties constants        = Constants.getInstance().getConstantsProperties();

    @Inject
    private UserDAO                   userDAO;

    // =========================================================================
    // METHODS
    // =========================================================================

    /**
     * Allow to checks if a user is an admin.
     * 
     * @param login user login
     * @return true, if is adminstrator
     * @throws ClientException the client exception
     */
    public boolean isAdmin(String login) throws ClientException {
        boolean result = false;

        if (login == null) {
            return result;
        }

        if (constants.getUserAdmin().equals(login)) {
            result = true;
        }

        final User user = userDAO.getUserByLogin(login);
        if (user != null) {
            if (!result) {
                result = hasAdminProfile(user);
            }
            if (!result) {
                result = hasAdminGroup(user);
            }
        }
        return result;
    }

    /**
     * Checks for admin profile.
     * 
     * @param user the user
     * @return true, if successful
     */
    protected boolean hasAdminProfile(User user) {
        boolean result = false;
        if (user.getProfile() != null && constants.getProfileAdministrator() != null) {
            result = constants.getProfileAdministrator().equals(user.getProfile().getName());
        }
        return result;
    }

    /**
     * Checks for admin group.
     * 
     * @param user the user
     * @return true, if successful
     */
    protected boolean hasAdminGroup(User user) {
        boolean result = false;
        if (user.getGroups() != null && constants.getGroupAdministrator() != null) {
            result = user.getGroups().contains(constants.getGroupAdministrator());
        }
        return result;
    }

}
