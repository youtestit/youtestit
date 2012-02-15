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
import org.youtestit.datamodel.entity.Os;
import org.youtestit.datamodel.enums.OsType;



/**
 * OsDOA, allow to manage Os CRUD
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Jan 13, 2012
 */
@Singleton
@Named
public class OsDAO implements Serializable {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3096832461481018940L;

    /** The entity manager. */
    @PersistenceContext
    private EntityManager     entityManager;

    @Inject
    private Logger            log;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Instantiates a new user dao.
     */
    public OsDAO() {
        super();
    }

    /**
     * Instantiates a new OsDAO for Unit test.
     * 
     * @param entityManager the entity manager
     * @param log the log
     */
    protected OsDAO(final EntityManager entityManager, final Logger log) {
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
     * Allow to creates a new Os if it don't already exists.
     * 
     * @param operatingSystem the operating system to create
     * @throws ClientException if an error has occur.
     */
    public void create(final Os operatingSystem) throws ClientException {
        log.debug("createOs");
        if (operatingSystem == null) {
            throw new ClientException(ErrorsMSG.VALUE_NOT_NULL);
        }

        final Os existingOs = readByName(operatingSystem.getName());
        if (existingOs == null) {
            entityManager.persist(operatingSystem);
        } else {
            throw new EntityExistsException();
        }

    }


    /**
     * Allow to creates a list of Os if they doesn't already exists.
     * 
     * @param operatingSystems the l operating system list to create
     * @throws ClientException if an error has occur.
     */
    public void create(List<Os> operatingSystems) throws ClientException {
        log.debug("createOs-list");
        if (operatingSystems == null) {
            throw new ClientException(ErrorsMSG.VALUE_NOT_NULL);
        }
        for (Os item : operatingSystems) {
            create(item);
        }
    }


    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // UPDATE
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /**
     * Allow to update os modification.
     * 
     * @param operatingSystem the operating system to save.
     * @throws ClientException if an error has occur.
     */
    public void update(final Os operatingSystem) throws ClientException {
        log.debug("update Os");
        if (operatingSystem == null) {
            throw new ClientException(ErrorsMSG.VALUE_NOT_NULL);
        }
        entityManager.merge(operatingSystem);

    }

    /**
     * Allow to update os list modification.
     * 
     * @param operatingSystems the operating systems list
     * @throws ClientException if an error has occur.
     */
    public void update(final List<Os> operatingSystems) throws ClientException {
        log.debug("update list Os");
        if (operatingSystems == null) {
            throw new ClientException(ErrorsMSG.VALUE_NOT_NULL);
        }
        for (Os item : operatingSystems) {
            update(item);
        }
    }


    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // READ
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    
    /**
     * Allow to grab all Os.
     *
     * @return the Os list saved
     * @throws ClientException if an error has occur.
     */
    public List<Os> read() throws ClientException {
        log.debug("read - get all OS");
        return entityManager.createNamedQuery(Os.QUERY_ALL_OS, Os.class)
                            .getResultList();
    }
    
    /**
     * Allow to read an Os its name.
     * 
     * @param name the Os name
     * @return the os finded
     * @throws ClientException if an error has occur.
     */
    public Os readByName(final String name) throws ClientException {
        log.debug("readByName");
        if (name == null) {
            throw new ClientException(ErrorsMSG.VALUE_NOT_NULL);
        }

        Os result = null;
        try {
            result = entityManager.createNamedQuery(Os.QUERY_OS_BY_NAME, Os.class)
                                  .setParameter(Os.PARAM_OS_BY_NAME, name)
                                  .getSingleResult();

        } catch (NoResultException e) {
            // user can not exist. it musn't throw exception for this.
            log.debug("search os", e);
        }
        return result;
    }

    /**
     * Allow to read an Os its type.
     * 
     * @param type the operating system type
     * @return the list of operating system
     * @throws ClientException if an error has occur.
     */
    public List<Os> readByType(final OsType type) throws ClientException {
        log.debug("readByType");
        if (type == null) {
            throw new ClientException(ErrorsMSG.VALUE_NOT_NULL);
        }
        return entityManager.createNamedQuery(Os.QUERY_OS_BY_TYPE, Os.class)
                            .setParameter(Os.PARAM_OS_BY_TYPE, type.name())
                            .getResultList();
    }


    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // DELETE
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    /**
     * Allow to delete Os.
     * 
     * @param operatingSystem the operating system to delete
     * @throws ClientException if an error has occur.
     */
    public void delete(final Os operatingSystem) throws ClientException {
        log.debug("delete");
        if (operatingSystem == null) {
            throw new ClientException(ErrorsMSG.VALUE_NOT_NULL);
        }
        entityManager.remove(operatingSystem);
    }

    /**
     * Allow to delete an Os list .
     * 
     * @param operatingSystems the liste of operating systems to delete
     * @throws ClientException if an error has occur.
     */
    public void delete(final List<Os> operatingSystems) throws ClientException {
        log.debug("delete - list");
        if (operatingSystems == null) {
            throw new ClientException(ErrorsMSG.VALUE_NOT_NULL);
        }
        for (Os item : operatingSystems) {
            entityManager.remove(item);
        }

    }

}
