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

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.ejb.Ejb3Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.youtestit.commons.utils.exceptions.ClientException;


/**
 * AbstractEntityTest.
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 9, 2011
 */
public class TestCaseHelper {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant LOGGER. */
    private static final Logger     LOGGER       = LoggerFactory.getLogger(TestCaseHelper.class);

    /** The Constant URL. */
    private static final String     URL          = "jdbc:derby:memory:database;create=true";

    /** The Constant LOGIN. */
    private static final String     LOGIN        = "";

    /** The Constant PASSWORD. */
    private static final String     PASSWORD     = "";

    /** The Constant DRIVER. */
    private static final String     DRIVER       = "org.apache.derby.jdbc.EmbeddedDriver";

    /** The Constant PROPERTIES. */
    private static final Properties PROPERTIES   = loadProperties();

    /** The Constant CLASSES. */
    private static final Class<?>[] CLASSES      = {
        User.class,
        Profile.class,
        Group.class,
        Browser.class,
        DublinCore.class,
        Document.class,
        Os.class,
        Portability.class,
        Instruction.class,
        Dependency.class,
        Project.class,
        TestCase.class,
        Tag.class};

    /** The Constant TARGET_FILES. */
    private static final File[]     TARGET_FILES = {
        new File("target/classes"),
        new File("target/test-classes") };

    /** The em factory. */
    private EntityManagerFactory    emFactory;

    /** The connection. */
    private Connection              connection;

    /** The ejb3 context. */
    private Context                 ejb3Context;

    /** The ejb container. */
    private EJBContainer            ejbContainer;

    /** The entity manager. */
    protected EntityManager         entityManager;


    
    // =========================================================================
    // ENTITY MANAGER
    // =========================================================================

    /**
     * Load entity manager.
     * 
     * @return the entity manager
     * @throws ClientException the client exception
     */
    public EntityManager loadEntityManager() throws ClientException {
        LOGGER.debug("loadEntityManager");
        if (connection == null) {
            loadConnection();
        }

        final Ejb3Configuration cfg = new Ejb3Configuration();
        for (Class<?> clazz : CLASSES) {
            cfg.addAnnotatedClass(clazz);
        }
        cfg.addProperties(PROPERTIES);

        emFactory = cfg.buildEntityManagerFactory();
        entityManager = emFactory.createEntityManager();
        return entityManager;
    }

    /**
     * Close entity manager.
     * 
     * @throws ClientException the client exception
     */
    public void closeEntityManager() throws ClientException {
        LOGGER.debug("closeEntityManager");
        entityManager.close();
        if (emFactory != null) {
            emFactory.close();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new ClientException("Can't close connection!", e);
        }
    }

    /**
     * Begin transaction.
     * 
     * @throws ClientException the client exception
     */
    public void beginTransaction() throws ClientException {
        LOGGER.debug("beginTransaction");
        if (entityManager == null) {
            throw new ClientException("can't begin transaction, entityManager is null!");
        } else {
            entityManager.getTransaction().begin();
        }
    }

    /**
     * Commit transaction.
     * 
     * @throws ClientException the client exception
     */
    public void commitTransaction() throws ClientException {
        LOGGER.debug("commitTransaction");
        if (entityManager == null) {
            throw new ClientException("can't commit transaction, entityManager is null!");
        } else {
            entityManager.getTransaction().commit();
        }
    }

    /**
     * Rollback transaction.
     * 
     * @throws ClientException the client exception
     */
    public void rollbackTransaction() throws ClientException {
        LOGGER.debug("rollbackTransaction");
        if (entityManager == null) {
            throw new ClientException("can't rollback transaction, entityManager is null!");
        } else {
            entityManager.getTransaction().rollback();
        }
    }


    /**
     * Load connection.
     * 
     * @throws ClientException the client exception
     */
    private void loadConnection() throws ClientException {
        LOGGER.debug("loadConnection");
        final String error = "can't get derby connection !";
        try {
            Class.forName(DRIVER).newInstance();
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (InstantiationException e) {
            throw new ClientException(error, e);
        } catch (IllegalAccessException e) {
            throw new ClientException(error, e);
        } catch (ClassNotFoundException e) {
            throw new ClientException(error, e);
        } catch (SQLException e) {
            throw new ClientException(error, e);
        }

        if (connection == null) {
            throw new ClientException(error);
        }
    }


    /**
     * Load properties.
     * 
     * @return the properties
     */
    private static Properties loadProperties() {
        LOGGER.debug("loadProperties");
        final Properties props = new Properties();
        props.put("hibernate.hbm2ddl.auto", "create-drop");
        props.put("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
        props.put("hibernate.connection.url", URL);
        props.put("hibernate.connection.driver_class", DRIVER);
        props.put("hibernate.connection.username", LOGIN);
        props.put("hibernate.connection.password", PASSWORD);
        return props;
    }

    // =========================================================================
    // EJB 3 CONTEXT
    // =========================================================================

    /**
     * Load ejb container.
     * 
     * @return the context
     * @throws ClientException the client exception
     */
    public Context loadEJBContainer() throws ClientException {
        final Map<String, Object> props = new HashMap<String, Object>();
        props.put(EJBContainer.MODULES, TARGET_FILES);
        props.put(EJBContainer.APP_NAME, "youtestit");
        props.put(EJBContainer.PROVIDER, "org.glassfish.ejb.embedded.EJBContainerProviderImpl");

        ejbContainer = EJBContainer.createEJBContainer(props);
        ejb3Context = ejbContainer.getContext();
        return ejb3Context;
    }


    /**
     * Close ejb container.
     * 
     * @throws ClientException the client exception
     */
    public void closeEJBContainer() throws ClientException {
        try {
            ejb3Context.close();
        } catch (NamingException e) {
            throw new ClientException("Can't close EJB Container !", e);
        } finally {
            ejbContainer.close();
        }
    }

}
