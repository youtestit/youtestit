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
package org.youtestit.datamodel.entity;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.youtestit.commons.utils.exceptions.ClientException;


/**
 * AbstractEntityTest.
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 9, 2011
 */
public abstract class AbstractEntityTest {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The em factory. */
    protected EntityManagerFactory emFactory;

    /** The connection. */
    private Connection           connection;

    /** The entity manager. */
    protected EntityManager      entityManager;

    /** The Constant LOGGER. */
    private static final Logger  LOGGER = LoggerFactory.getLogger(AbstractEntityTest.class);


    // =========================================================================
    // LOADER
    // =========================================================================
    /**
     * {@inheritDoc}
     * @throws ClassNotFoundException 
     * @throws SQLException 
     */
    public void loadEntityManager() throws ClassNotFoundException, SQLException {
        LOGGER.info("Starting in-memory HSQL database for unit tests");
        Class.forName("org.hsqldb.jdbcDriver");
        connection = DriverManager.getConnection("jdbc:hsqldb:mem:testPersistancetUnit", "sa", "");

        LOGGER.info("Building JPA EntityManager for unit tests");
        emFactory = Persistence.createEntityManagerFactory("testPersistancetUnit");
        entityManager = emFactory.createEntityManager();
    }

    /**
     * {@inheritDoc}
     * @throws SQLException 
     */
    public void shutDownEntityManager() throws SQLException  {
        LOGGER.info("Shuting down HSQL DB");
        if (entityManager != null) {
            entityManager.close();
        }
        if (emFactory != null) {
            emFactory.close();
        }
        LOGGER.info("Stopping in-memory HSQL database.");
        connection.createStatement().execute("SHUTDOWN");
    }

    // =========================================================================
    // METHODS
    // =========================================================================
    /**
     * Generic method for check entity persistance .
     * 
     * @throws ClientException the client exception
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    @Test
    public void checkPersistanceTest() throws ClientException, ClassNotFoundException, SQLException {
//        loadEntityManager();
//        assertNotNull("error entityManager not started", entityManager);
//        try {
            persistanceTest();
//        } catch (ClientException ex) {
//            entityManager.getTransaction().rollback();
//            LOGGER.error("Exception during testPersistence", ex);
//            throw new ClientException(ex);
//        }finally{
//            shutDownEntityManager();
//        }
    }

    /**
     * Method who realize the unit test for entity. It must be implemented in
     * all entity unit tests
     * 
     * @throws ClientException the client exception
     */
    protected abstract void persistanceTest() throws ClientException;

}
