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
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.datamodel.entity.Document;

@Singleton
@Named
public class DocumentDAO implements Serializable {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2223620775930007961L;

    /** The logger. */
    @Inject
    private Logger            log;


    /** The entity manager. */
    @PersistenceContext
    private EntityManager     entityManager;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================

    /**
     * Instantiates a new project dao.
     */
    public DocumentDAO() {
        super();
    }

    /**
     * Instantiates a new project dao for unit test.
     * 
     * @param entityManager the entity manager
     * @param log the log
     */
    protected DocumentDAO(final EntityManager entityManager, final Logger log) {
        super();
        this.entityManager = entityManager;
        this.log = log;
    }

    // =========================================================================
    // METHODS
    // =========================================================================


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
    public List<Document> readDocByPathParent(final String path) throws ClientException {
        log.debug("readDocByPathParent");

        final String param = "parentPath";
        final String jpql = "SELECT d FROM Document d WHERE d.parentPath=:" + param;

        return entityManager.createQuery(jpql, Document.class).setParameter(param, path).getResultList();
    }

    /**
     * Read doc by path.
     * 
     * @param path the path
     * @return the list
     * @throws ClientException the client exception
     */
    public Document readDocByPath(final String path) throws ClientException {
        log.debug("readDocByPath");

        if (path == null) {
            return null;
        }


        final String param = "path";
        final String jpql = "SELECT d FROM Document d WHERE d.path=:" + param;
        Document document = null;

        final List<Document> documents = entityManager.createQuery(jpql, Document.class).setParameter(param, path).getResultList();

        if (documents != null && !documents.isEmpty()) {
            document = documents.get(0);
        }
        return document;

    }


    /**
     * Gets the entity manager.
     * 
     * @return the entity manager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Sets the entity manager.
     * 
     * @param entityManager the new entity manager
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


}
