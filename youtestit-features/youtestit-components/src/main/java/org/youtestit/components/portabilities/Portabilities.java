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
package org.youtestit.components.portabilities;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.datamodel.dao.BrowserDAO;
import org.youtestit.datamodel.dao.OsDAO;
import org.youtestit.datamodel.entity.Browser;
import org.youtestit.datamodel.entity.Os;
import org.youtestit.datamodel.entity.Portability;


/**
 * Portabilities components. Allow to select portabilities on create project or
 * create test case.
 * 
 * <pre>
 *         .------------------------------------.
 *         |          | FF-8|IE-7|IE-8 |Safari 5|
 *         |----------|-----|----|-----|--------|
 *         |Ubuntu    |  X  |    |     |        |
 *         |----------|-----|----|-----|--------|
 *         |Windows XP|     | X  |     |        |
 *         |----------|-----|----|-----|--------|
 *         |Windows 7 |  X  |    | X   |        |
 *         |----------|-----|----|-----|--------|
 *         |Mac OS X  |  X  |    |     |   X    |
 *         '------------------------------------'
 * </pre>
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Jan 14, 2012
 */
@ViewScoped
@Named
public class Portabilities implements Serializable {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7444077164051724293L;
    

    /** The logger. */
    @Inject
    private Logger            log;

    /** The browser dao. */
    @Inject
    private BrowserDAO        browserDAO;

    /** The os dao. */
    @Inject
    private OsDAO             osDAO;

    /** The operating system. */
    private List<Os>          operatingSystem;

    /** The browsers. */
    private List<Browser>     browsers;

    /** The number of browser. */
    private int               nbBrowser        = 0;

    /** The number of operating systems. */
    private int               nbOs             = 0;
    
    

    

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================

    /**
     * Instantiates a new portabilities.
     */
    public Portabilities() {
        super();
    }


    /**
     * Instantiates some new portabilities in unit test.
     *
     * @param log the logger
     * @param browserDAO the browser dao instance
     * @param osDAO the os dao instance
     */
    protected Portabilities(Logger log, BrowserDAO browserDAO, OsDAO osDAO) {
        super();
        this.log = log;
        this.browserDAO = browserDAO;
        this.osDAO = osDAO;
    }


    // =========================================================================
    // METHODS
    // =========================================================================
    /**
     * Allow to initialize operatingSystem attribute.
     */
    protected void initializeOs() {
        log.debug("initializeOs");
        if (operatingSystem == null) {
            try {
                operatingSystem = osDAO.read();
            } catch (ClientException exception) {
                log.error("can't read Os information", exception);
                // TODO throw JSF message...
            }

            if (operatingSystem != null) {
                nbOs = operatingSystem.size();
            }
        }


    }

    /**
     * Allow to initialize browsers attribute.
     */
    protected void initializeBrowser() {
        log.debug("initializeBrowser");
        if (browsers == null) {
            try {
                browsers = browserDAO.read();
            } catch (ClientException exception) {
                log.error("can't read Browser information", exception);
                // TODO throw JSF message...
            }
            if (browsers != null) {
                nbBrowser = browsers.size();
            }
        }
    }


    /**
     * Gets the portability.
     *
     * @param portabilities the portabilities
     * @param osIndex the os index
     * @param browserIndex the browser index
     * @return the portability
     */
    public void addPortability(List<Portability> portabilities, int osIndex, int browserIndex){
        log.info("addPortability");
        
        final Portability protability = new Portability(operatingSystem.get(osIndex), browsers.get(browserIndex));
        if(!portabilities.contains(protability)){
            portabilities.add(protability);
        }
    }
    
    /**
     * Removes the portability.
     *
     * @param portabilities the portability list
     * @param osIndex the os index
     * @param browserIndex the browser index
     */
    public void removePortability(List<Portability> portabilities, int osIndex, int browserIndex){
        log.info("removePortability");
        
        final Portability protability = new Portability(operatingSystem.get(osIndex), browsers.get(browserIndex));
        if(portabilities.contains(protability)){
            portabilities.remove(protability);
        }
    }
    
    /**
     * Check if a portability is in a portability list.
     *
     * @param osIndex the os index
     * @param browserIndex the browser index
     * @param portabilities the portability  list
     * @return true if contains
     */
    public boolean getContains(int osIndex, int browserIndex,List<Portability> portabilities){
        boolean result = false;
        if(portabilities!=null){
            final Portability protability = new Portability(operatingSystem.get(osIndex), browsers.get(browserIndex));
            result = portabilities.contains(protability);
        }
        return result;
    }
    

    // =========================================================================
    // GETTERS
    // =========================================================================


    /**
     * Gets operating systems.
     * 
     * @return list of operating systems
     * @throws ClientException the client exception
     */
    public List<Os> getOperatingSystem() throws ClientException {
        initializeOs();
        return operatingSystem;
    }


    /**
     * Gets browsers.
     * 
     * @return list of browsers
     * @throws ClientException the client exception
     */
    public List<Browser> getBrowsers() throws ClientException {
        initializeBrowser();
        return browsers;
    }


    /**
     * Gets the bumber of browsers.
     * 
     * @return the nb browser
     */
    public int getNbBrowser() {
        return nbBrowser;
    }


    /**
     * Gets the number of operating system.
     * 
     * @return the nb os
     */
    public int getNbOs() {
        return nbOs;
    }


}
