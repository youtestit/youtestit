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
package org.youtestit.datamodel.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.commons.utils.exceptions.ErrorsMSG;
import org.youtestit.commons.utils.exceptions.entities.EntityExistsException;
import org.youtestit.datamodel.entity.Browser;
import org.youtestit.datamodel.enums.BrowserType;


/**
 * BrowserDAO, allow to manage Browser CRUD
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Jan 13, 2012
 */
@Singleton
@Named
public class BrowserDAO implements Serializable {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6155824803870998808L;


    /** The entity manager. */
    @PersistenceContext
    private EntityManager     entityManager;

    @Inject
    private Logger            log;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================

    /**
     * Instantiates a new browser dao.
     */
    public BrowserDAO() {
        super();
    }

    /**
     * Instantiates a new browser dao in unit test.
     * 
     * @param entityManager the entity manager
     * @param log the log
     */
    protected BrowserDAO(EntityManager entityManager, Logger log) {
        super();
        this.entityManager = entityManager;
        this.log = log;
    }


    // =========================================================================
    // METHODS
    // =========================================================================

    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // CREATE
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /**
     * Allow to persist a new Browser
     * 
     * @param browser the browser to create
     * @throws ClientException if an error has occur.
     */
    public void create(final Browser browser) throws ClientException {
        log.debug("create-browser");
        if (browser == null) {
            throw new ClientException(ErrorsMSG.VALUE_NOT_NULL);
        }
        try {
            Browser resultSet = null;
            resultSet = entityManager.createNamedQuery(Browser.QUERY_BROWSER_BY_TYPE_AND_VERSION, Browser.class)
                                                   .setParameter(Browser.PARAM_TYPE, browser.getType())
                                                   .setParameter(Browser.PARAM_VERSION, browser.getVersion())
                                                   .getSingleResult();
            if (resultSet != null) {
                throw new EntityExistsException();
            }

        } catch (NoResultException e) {
            // browser can not exist. it musn't throw exception for this.
            entityManager.persist(browser);
        }


    }


    /**
     * Allow to persist new Browsers
     * 
     * @param browsers the browsers list to create
     * @throws ClientException if an error has occur.
     */
    public void create(final List<Browser> browsers) throws ClientException {
        log.debug("create-list-browser");
        if (browsers == null) {
            throw new ClientException(ErrorsMSG.VALUE_NOT_NULL);
        }
        for (Browser browser : browsers) {
            create(browser);
        }
    }

    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // UPDATE
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /**
     * Allow to update browser modification.
     * 
     * @param browser the browser to update.
     * @throws ClientException if an error has occur.
     */
    public void update(final Browser browser) throws ClientException {
        log.debug("update browser");
        if (browser == null) {
            throw new ClientException(ErrorsMSG.VALUE_NOT_NULL);
        }
        entityManager.merge(browser);

    }

    /**
     * Allow to update browser list modification.
     * 
     * @param browsers the browsers list to update
     * @throws ClientException if an error has occur.
     */
    public void update(final List<Browser> browsers) throws ClientException {
        log.debug("update list browsers");
        if (browsers == null) {
            throw new ClientException(ErrorsMSG.VALUE_NOT_NULL);
        }
        for (Browser item : browsers) {
            update(item);
        }
    }

    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // READ
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    /**
     * Allow to grab all Browser.
     * 
     * @return the browser list saved
     * @throws ClientException if an error has occur.
     */
    public List<Browser> read() throws ClientException {
        log.debug("read - get all browsers");
        return entityManager.createNamedQuery(Browser.QUERY_ALL_BROWSER, Browser.class).getResultList();
    }

    /**
     * Allow to grab browser by id.
     * 
     * @param uid the Browser uid to find
     * @return the browser finded or null
     * @throws ClientException if an error has occur.
     */
    public Browser readByID(final int uid) throws ClientException {
        log.debug("read browser by ID");

        Browser result = null;
        try {
            result = entityManager.createNamedQuery(Browser.QUERY_BROWSER_BY_ID, Browser.class).setParameter(
                    Browser.PARAM_ID, uid).getSingleResult();
        } catch (NoResultException e) {
            // browser can not exist. it musn't throw exception for this.
            log.debug("search browser", e);
        }

        return result;
    }

    /**
     * Allow to grab browsers by type.
     * 
     * @param type the Browser type to find
     * @return browsers finded or null
     * @throws ClientException if an error has occur.
     */
    public List<Browser> readByType(final BrowserType type) throws ClientException {
        log.debug("read browser by type");

        return entityManager.createNamedQuery(Browser.QUERY_BROWSER_BY_TYPE, Browser.class).setParameter(
                Browser.PARAM_TYPE, type.name()).getResultList();

    }

    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // DELETE
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /**
     * Allow to delete browser.
     * 
     * @param browser the browser to delete
     * @throws ClientException if an error has occur.
     */
    public void delete(final Browser browser) throws ClientException {
        log.debug("delete");
        if (browser == null) {
            throw new ClientException(ErrorsMSG.VALUE_NOT_NULL);
        }
        entityManager.remove(browser);
    }

    /**
     * Allow to delete browsers.
     * 
     * @param browsers the browsers to delete
     * @throws ClientException if an error has occur.
     */
    public void delete(final List<Browser> browsers) throws ClientException {
        log.debug("delete - list");
        if (browsers == null) {
            throw new ClientException(ErrorsMSG.VALUE_NOT_NULL);
        }
        for (Browser item : browsers) {
            entityManager.remove(item);
        }

    }

}
