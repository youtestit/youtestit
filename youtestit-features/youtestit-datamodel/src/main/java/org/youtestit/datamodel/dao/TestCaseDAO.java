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
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import org.jboss.logging.Logger;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.commons.utils.exceptions.ErrorsMSG;
import org.youtestit.commons.utils.exceptions.entities.EntityExistsException;
import org.youtestit.commons.utils.exceptions.entities.ParentNullException;
import org.youtestit.commons.utils.exceptions.entities.ParentTypeException;
import org.youtestit.datamodel.entity.Document;
import org.youtestit.datamodel.entity.Portability;
import org.youtestit.datamodel.entity.Project;
import org.youtestit.datamodel.entity.TestCase;

/**
 * The Class TestCaseDAO.
 */
@Singleton
@Named
public class TestCaseDAO extends DocumentDAO implements Serializable {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2223620775930007961L;

    /** The logger. */
    @Inject
    private Logger            log;

    /** The portabilities dao. */
    @Inject
    private PortabilityDAO    portabilitiesDAO;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================

    /**
     * Instantiates a new project dao.
     */
    public TestCaseDAO() {
        super();
    }

    /**
     * Instantiates a new project dao for unit test.
     * 
     * @param entityManager the entity manager
     * @param log the log
     */
    protected TestCaseDAO(final EntityManager entityManager, final Logger log) {
        super();
        this.setEntityManager(entityManager);
        this.log = log;
        this.portabilitiesDAO = new PortabilityDAO(entityManager, log);
    }

    /**
     * Instantiates a new project dao.
     * 
     * @param entityManager the entity manager
     * @param log the log
     * @param portabilitiesDAO the portabilities dao
     */
    protected TestCaseDAO(EntityManager entityManager, Logger log, PortabilityDAO portabilitiesDAO) {
        super();
        this.setEntityManager(entityManager);
        this.log = log;
        this.portabilitiesDAO = portabilitiesDAO;
    }

    // =========================================================================
    // METHODS
    // =========================================================================

    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // CREATE
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /**
     * Allow to creates a new Project.
     *
     * @param test the test case to create
     * @throws EntityExistsException the entity exists exception
     * @throws ParentNullException the parent null exception
     * @throws ParentTypeException the parent type exception
     * @throws ClientException if exception is occure.
     */
    public void create(final TestCase test) throws EntityExistsException, ParentNullException, ParentTypeException,
            ClientException {

        log.debug("create");
        final Document parent = readDocByPath(test.getParentPath());

        assertCreate(test, parent);

        persistePortability(test);
        persist(test);

        parent.addChild(test);
        getEntityManager().merge(parent);
    }

    /**
     * Allow to check test case to create. It's throw an exception if :
     * <ul>
     * <li>test is null</li>
     * <li>test path already exist</li>
     * <li>parent isn't a project</li>
     * </ul>
     *
     * @param test the test to check
     * @param parent the parent
     * @throws EntityExistsException the entity exists exception
     * @throws ParentNullException the parent null exception
     * @throws ParentTypeException the parent type exception
     * @throws ClientException if exception is occure.
     */
    public void assertCreate(TestCase test, Document parent) throws EntityExistsException, ParentNullException,
            ParentTypeException, ClientException {

        if (test == null) {
            throw new ClientException(ErrorsMSG.VALUE_NOT_NULL);
        }

        final Document resultSet = readDocByPath(test.getPath());

        if (resultSet != null) {
            throw new EntityExistsException();
        }

        if (parent == null) {
            throw new ParentNullException();
        } else if (!(parent instanceof Project)) {
            throw new ParentTypeException();
        }

    }

    /**
     * Persiste portability.
     *
     * @param test the test
     * @throws ClientException the client exception
     */
    protected void persistePortability(final TestCase test) throws ClientException {

        if (test.getPortabilities() != null && !test.getPortabilities().isEmpty()) {
            final List<Portability> result = new ArrayList<Portability>();

            for (Portability item : test.getPortabilities()) {
                result.add(portabilitiesDAO.create(item));
            }
            test.setPortabilities(result);
        }
    }

    /**
     * Persist.
     * 
     * @param test the test case to persist
     * @throws ClientException the client exception
     */
    protected void persist(final TestCase test) throws ClientException {
        final List<Portability> portabilities = portabilitiesDAO.create(test.getPortabilities());
        test.setPortabilities(portabilities);
        getEntityManager().persist(test);
    }

}
