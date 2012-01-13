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

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.Secures;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.security.identification.CurrentUserManager;

/**
 * The Class RolesSecurity.
 */
@Named
public class RolesSecurity implements Serializable {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5023681736363699508L;

    /** The roles validator. */
    @Inject
    private RolesValidator    rolesValidator;
    
    @Inject
    private CurrentUserManager currentUserManager;

    // =========================================================================
    // METHODS
    // =========================================================================

    /**
     * Owner checker.
     * 
     * @param identity the identity
     * @return true, if successful
     */
    public @Secures @Owner boolean ownerChecker(Identity identity) {
        return !(identity == null || identity.getUser() == null);
    }


    /**
     * Not logged in checker.
     * 
     * @param identity the identity
     * @return true, if successful
     */
    public @Secures  @NotLoggedIn boolean notLoggedInChecker(Identity identity) {
        return identity == null || identity.getUser() == null;
    }


    /**
     * Admin checker.
     * 
     * @param identity the identity
     * @return true, if successful
     * @throws ClientException the client exception
     */
    public @Secures @Admin  boolean adminChecker(Identity identity) throws ClientException {
        boolean result = false;
        if (identity.getAuthenticatorName() != null) {
            result = rolesValidator.isAdmin(identity.getAuthenticatorName());
        }else if(currentUserManager.getCurrentAccount()!=null){
            result = currentUserManager.isAdmin();
        }
        return result;
    }


}
