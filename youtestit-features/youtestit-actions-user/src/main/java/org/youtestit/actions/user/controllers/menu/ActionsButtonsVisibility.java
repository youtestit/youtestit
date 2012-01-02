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
package org.youtestit.actions.user.controllers.menu;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.core.controllers.app.CurrentDocument;
import org.youtestit.security.identification.CurrentUserManager;


/**
 * ActionsButtonsVisibility.
 *
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 29, 2011
 */
@Named
@RequestScoped
public class ActionsButtonsVisibility implements Serializable {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5294887694338548648L;

    @Inject
    private CurrentUserManager currentUserManager;
    
    @Inject 
    private CurrentDocument currentDocument;
    
    private Boolean showInnerSection = null;
    // =========================================================================
    // METHODS
    // =========================================================================

    
    /**
     * Gets the show create test.
     *
     * @return the show create test
     */
    public Boolean getShowInnerSection(){
        if(showInnerSection==null){
            showInnerSection = false;
            
            if(currentDocument!=null){
                showInnerSection = currentDocument.getIsTest() || currentDocument.getIsProject(); 
            }    
        }
        
        return showInnerSection;
    }

    
    /**
     * Only administrator can't see and use the global configuration panel. 
     * This method allow to check it.
     *
     * @return true if current user is an administrator.
     * @throws ClientException 
     */
    public Boolean getShowAdminConfig() throws ClientException {
        boolean result = false;
        if(currentUserManager.getCurrentAccount()!=null){
            result = currentUserManager.isAdmin();
        }
        return result;
    }

}
