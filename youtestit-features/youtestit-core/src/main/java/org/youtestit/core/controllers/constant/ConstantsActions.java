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
package org.youtestit.core.controllers.constant;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.youtestit.commons.utils.constants.Constants;
import org.youtestit.commons.utils.constants.ConstantsProperties;



/**
 * ConstantsActions
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 26, 2011
 */
@ApplicationScoped
@Named
public class ConstantsActions {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    private ConstantsProperties constants=null;



    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    @PostConstruct
    public void initialize(){
        constants = Constants.getInstance().getConstantsProperties();
    }
    

    // =========================================================================
    // METHODS
    // =========================================================================



    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================
    /**
     * Gets the constants.
     *
     * @return the constants
     */
    public ConstantsProperties getConstants() {
        return constants;
    }

    /**
     * Sets the constants.
     *
     * @param constants the new constants
     */
    public void setConstants(ConstantsProperties constants) {
        this.constants = constants;
    }
    
}


