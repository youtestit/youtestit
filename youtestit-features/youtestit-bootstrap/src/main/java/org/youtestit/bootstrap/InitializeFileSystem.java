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

import java.io.File;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;
import org.jboss.seam.servlet.WebApplication;
import org.jboss.seam.servlet.event.Initialized;
import org.youtestit.bootstrap.events.InitializeUser;
import org.youtestit.commons.utils.constants.Constants;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.commons.utils.exceptions.FatalException;

/**
 * InitializeFileSystem
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 26, 2011
 */
@Alternative
@Named
public class InitializeFileSystem {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    @Inject
    private Logger log;
    
    @Inject
    @InitializeUser
    private Event<String> initializeUsers;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Instantiates a new initialize file system.
     */
    public InitializeFileSystem() {
        super();
    }

    /**
     * Instantiates a new initialize file system in unit test.
     */
    protected InitializeFileSystem(Logger log) {
        // constructor for unit test
        this.log = log;
    }

    // =========================================================================
    // METHODS
    // =========================================================================


    /**
     * Initialize file system foldes (/home/${user}/.youtestit).
     * 
     * @param webapp the webapp
     */
    public void initialize(@Observes
    @Initialized
    WebApplication webapp) {
        log.info("initialize filesystem storage for YouTestit");
        try {
            initializeFoldes();
            initializeConfiguration();
        } catch (ClientException e) {
            log.fatal(e);
            throw new FatalException("can't initialize YouTestit application !", e);
        }
        
        initializeUsers.fire("initializeUsers");
    }


    // =========================================================================
    // PROTECTED
    // =========================================================================

    /**
     * Initialize file system foldes.
     * 
     * <pre>
     *   /home/${user}
     *            '--> .youtestit
     *                      |--> config
     *                      '--> projects
     * </pre>
     * 
     * @throws ClientException the client exception
     */
    protected void initializeFoldes() throws ClientException {
        final File userHome = new File(Constants.USER_HOME);
        if (!userHome.exists()) {
            throw new ClientException(String.format("User home not exists :%s", Constants.USER_HOME));
        } else if (!userHome.canWrite()) {
            throw new ClientException(String.format("Can't write in user home :%s", Constants.USER_HOME));
        }

        createFolder(Constants.STORAGE_FOLDER);
        createFolder(Constants.STORAGE_FOLDER_CONFIG);
        createFolder(Constants.STORAGE_FOLDER_PROJECTS);

    }


    /**
     * Initialize configuration.
     * 
     * @throws ClientException the client exception
     */
    protected void initializeConfiguration() throws ClientException {
        Constants.getInstance().resetProperties();
    }

   

    /**
     * Allow to creates a folder if not exists
     * 
     * @param path the folder path
     * @throws ClientException the client exception
     */
    protected void createFolder(final String path) throws ClientException {
        final File pathFolder = new File(path);
        if (!pathFolder.exists()) {
            pathFolder.mkdir();
        }
    }

}
