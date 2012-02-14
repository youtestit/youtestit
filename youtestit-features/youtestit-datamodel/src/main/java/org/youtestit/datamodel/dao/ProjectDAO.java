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
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.commons.utils.exceptions.EntityExistsException;
import org.youtestit.commons.utils.exceptions.ErrorsMSG;
import org.youtestit.datamodel.entity.Document;
import org.youtestit.datamodel.entity.Portability;
import org.youtestit.datamodel.entity.Project;

@Singleton
@Named
public class ProjectDAO implements Serializable {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2223620775930007961L;

    /** The logger. */
    @Inject
    private Logger log;

    /** The entity manager. */
    @PersistenceContext
    private EntityManager entityManager;

    /** The portabilities dao. */
    @Inject
    private PortabilityDAO portabilitiesDAO;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================

    /**
     * Instantiates a new project dao.
     */
    public ProjectDAO() {
        super();
    }

    /**
     * Instantiates a new project dao for unit test.
     * 
     * @param entityManager the entity manager
     * @param log the log
     */
    protected ProjectDAO(final EntityManager entityManager, final Logger log) {
        super();
        this.entityManager = entityManager;
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
    protected ProjectDAO(EntityManager entityManager, Logger log,
            PortabilityDAO portabilitiesDAO) {
        super();
        this.entityManager = entityManager;
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
     * @param project the project to create
     * @throws ClientException if exception is occure.
     */
    public void create(final Project project) throws ClientException {
        log.debug("create");

        if (project == null) {
            throw new ClientException(ErrorsMSG.VALUE_NOT_NULL);
        }

        final String param = "path";
        final String jpql = "SELECT p FROM Project p WHERE p.path=:" + param;

        Project resultSet = null;
        try {
            resultSet = entityManager.createQuery(jpql, Project.class).setParameter(
                    param, project.getPath()).getSingleResult();
        } catch (NoResultException e) {
            // it can have no Entity in database. it isn't an error !
            log.debug(e);
        }

        persistePortability(project);

        if (resultSet == null) {
            persist(project);
        } else {
            throw new EntityExistsException();
        }

        Document parent = readDocByPath(project.getParentPath());
        if (parent != null) {
            parent.addChild(project);
            entityManager.merge(parent);
        }
    }

    /**
     * Persiste portability.
     * 
     * @param project the project
     * @throws ClientException the client exception
     */
    protected void persistePortability(final Project project)
            throws ClientException {
        if (project.getPortabilities() != null
                && !project.getPortabilities().isEmpty()) {
            List<Portability> result = new ArrayList<Portability>();

            for (Portability item : project.getPortabilities()) {
                result.add(portabilitiesDAO.create(item));
            }
            project.setPortabilities(result);
        }
    }

    /**
     * Persist.
     * 
     * @param project the project
     * @throws ClientException the client exception
     */
    protected void persist(final Project project) throws ClientException {
        final List<Portability> portabilities = portabilitiesDAO.create(project.getPortabilities());
        project.setPortabilities(portabilities);
        entityManager.persist(project);
    }

    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // READ
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    /**
     * Read doc by path parent.
     * 
     * @param path the path
     * @return the list
     * @throws ClientException the client exception
     */
    public List<Document> readDocByPathParent(final String path)
            throws ClientException {
        final String param = "parentPath";
        final String jpql = "SELECT d FROM Document d WHERE d.parentPath=:"
                + param;

        return entityManager.createQuery(jpql, Document.class).setParameter(
                param, path).getResultList();
    }

    /**
     * Read doc by path.
     * 
     * @param path the path
     * @return the list
     * @throws ClientException the client exception
     */
    public Document readDocByPath(final String path) throws ClientException {
        if(path==null){
            return null;
        }
        
        final String param = "path";
        final String jpql = "SELECT d FROM Document d WHERE d.path=:" + param;

        Document document = null;
        final List<Document> documents= entityManager.createQuery(jpql, Document.class).setParameter(
                param, path).getResultList();
        
        if(documents!=null && !documents.isEmpty()){
            document = documents.get(0);
        }
        return document;

    }
}
