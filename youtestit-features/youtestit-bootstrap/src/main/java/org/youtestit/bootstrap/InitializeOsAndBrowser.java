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
import org.youtestit.datamodel.dao.BrowserDAO;
import org.youtestit.datamodel.dao.OsDAO;
import org.youtestit.datamodel.entity.Browser;
import org.youtestit.datamodel.entity.Os;
import org.youtestit.datamodel.enums.BrowserType;
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
    private Logger     log;

    @Inject
    private OsDAO      osDAO;

    @Inject
    private BrowserDAO browserDAO;


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
            initialiseBrowsers();
        } catch (ClientException e) {
            throw new FatalException(e);
        }
    }

    // =========================================================================
    // METHODS
    // =========================================================================

    /**
     * Allow to initialise all default OS.
     * 
     * @throws ClientException if an error has occur.
     */
    protected void initialiseOS() throws ClientException {
        List<Os> operatingSystems = new ArrayList<Os>();
        operatingSystems.add(new Os("Ubuntu", OsType.LINUX, OsArchi.PC_32_BITS));
        operatingSystems.add(new Os("Windows Xp", OsType.WINDOWS, OsArchi.PC_32_BITS));
        operatingSystems.add(new Os("Mac Os X", OsType.MAC, OsArchi.PC_32_BITS));


        for (Os item : operatingSystems) {
            try {
                begin();
                osDAO.create(item);
                commit();
            } catch (EntityExistsException except) {
                log.info(String.format("os %s already initializing.", item.getName()));
            } catch (ClientException except) {
                log.error(except);
                throw except;
            }finally{
                rollback();   
            }
        }

    }


    /**
     * Allow to initialise all default Browsers.
     * 
     * @throws ClientException if an error has occur.
     */
    protected void initialiseBrowsers() throws ClientException {
        List<Browser> browsers = new ArrayList<Browser>();

        browsers.add(new Browser(BrowserType.FIREFOX, "8.0"));
        browsers.add(new Browser(BrowserType.FIREFOX, "9.0.1"));
        browsers.add(new Browser(BrowserType.IE, "7"));
        browsers.add(new Browser(BrowserType.IE, "8"));
        browsers.add(new Browser(BrowserType.SAFARI, "5.1.2"));
        browsers.add(new Browser(BrowserType.OPERA, "9"));
        browsers.add(new Browser(BrowserType.OPERA, "11.60"));


        for (Browser item : browsers) {
            try {
                begin();
                browserDAO.create(item);
                commit();
            } catch (ClientException except) {
                if (except instanceof EntityExistsException) {
                    log.info(String.format("browser %s-%s already initializing.", item.getType().name(),
                            item.getVersion()));
                    
                } else {
                    log.error(except);
                    throw except;
                }
            }finally{
                rollback();
            }
        }


    }

}
