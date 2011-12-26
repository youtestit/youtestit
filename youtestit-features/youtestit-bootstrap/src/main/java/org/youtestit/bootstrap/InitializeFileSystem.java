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
package org.youtestit.bootstrap;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.jboss.seam.servlet.WebApplication;
import org.jboss.seam.servlet.event.Initialized;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.commons.utils.exceptions.FatalException;

/**
 * InitializeFileSystem
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 26, 2011
 */
@Alternative
public class InitializeFileSystem {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    @Inject
    private Logger log;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    
    /**
     * Instantiates a new initialize file system in unit test.
     */
    protected InitializeFileSystem(){
        // constructor for unit test
    }

    // =========================================================================
    // METHODS
    // =========================================================================
   
    /**
     * Initialize file system foldes (/home/${user}/.youtestit).
     *
     * @param webapp the webapp
     */
    public void initialize(@Observes @Initialized WebApplication webapp) {
        log.info("initialize filesystem storage for YouTestit");
        try {
            initializeFoldes();
        } catch (ClientException e) {
            log.fatal(e);
            throw new FatalException("can't initialize YouTestit application !",e);
        }
        
    }



    // =========================================================================
    // PROTECTED
    // =========================================================================
    
    /**
     * Initialize file system foldes.
     *  <pre>
     *   /home/${user}
     *            '--> .youtestit
     *                      |--> config
     *                      '--> projects
     *  </pre>
     * @throws ClientException the client exception
     */
    protected void initializeFoldes() throws ClientException{
        //
    }
    
}


