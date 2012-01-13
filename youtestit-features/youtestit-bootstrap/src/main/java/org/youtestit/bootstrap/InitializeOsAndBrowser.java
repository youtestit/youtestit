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

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.youtestit.bootstrap.events.InitializeOsAndBrowserEvent;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.commons.utils.exceptions.EntityExistsException;
import org.youtestit.commons.utils.exceptions.FatalException;
import org.youtestit.datamodel.dao.OsDAO;
import org.youtestit.datamodel.entity.Os;
import org.youtestit.datamodel.enums.OsArchi;
import org.youtestit.datamodel.enums.OsType;


/**
 * InitializeUsers
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 26, 2011
 */
public class InitializeOsAndBrowser extends InitializeHelper {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    @Inject
    private Logger log;

    @Inject
    private OsDAO  osDAO;


    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Initialize file system foldes (/home/${user}/.youtestit).
     * 
     * @param webapp the webapp
     */
    public void initialize(@Observes
    @InitializeOsAndBrowserEvent
    String value) {
        log.info("initialize os and browser for YouTestit");
        
        try {
            initialiseOS();
        } catch (ClientException e) {
            throw new FatalException(e);
        }
    }

    // =========================================================================
    // METHODS
    // =========================================================================

    /**
     * Initialise admin user.
     * 
     * @throws ClientException the client exception
     */
    protected void initialiseOS() throws ClientException {
        List<Os> operatingSystems = new ArrayList<Os>();
        operatingSystems.add(new Os("ubuntu", OsType.LINUX, OsArchi.PC_32_BITS));
        operatingSystems.add(new Os("windows_Xp", OsType.WINDOWS, OsArchi.PC_32_BITS));

        begin();
        for (Os item : operatingSystems) {
            try {
                osDAO.create(item);
            } catch (EntityExistsException except) {
                log.info(String.format("os %s already initializing.", item.getName()));
            } catch (ClientException except) {
                log.error(except);
                rollback();
                throw except;
            }
        }
        commit();
    }

}
