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

import javax.inject.Named;


// TODO: Auto-generated Javadoc
/**
 * ActionsButtonsVisibility.
 *
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 29, 2011
 */
@Named
public class ActionsButtonsVisibility implements Serializable {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5294887694338548648L;

    // =========================================================================
    // METHODS
    // =========================================================================


    /**
     * Gets the show home.
     *
     * @return the show home
     */
    public Boolean getShowHome(){
        return true;
    }


    /**
     * Gets the show create project.
     *
     * @return the show create project
     */
    public Boolean getShowCreateProject(){
        return true;
    }

    /**
     * Gets the show create test.
     *
     * @return the show create test
     */
    public Boolean getShowCreateTest(){
        return true;
    }

    /**
     * Gets the show config.
     *
     * @return the show config
     */
    public Boolean getShowConfig() {
        return true;
    }

    /**
     * Gets the show run.
     *
     * @return the show run
     */
    public Boolean getShowRun() {
        return true;
    }

    /**
     * Gets the show admin config.
     *
     * @return the show admin config
     */
    public Boolean getShowAdminConfig() {
        return true;
    }

}
