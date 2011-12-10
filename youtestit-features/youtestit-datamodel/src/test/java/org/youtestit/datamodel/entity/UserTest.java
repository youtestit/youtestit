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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.hibernate.ejb.Ejb3Configuration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.youtestit.commons.utils.exceptions.ClientException;


/**
 * Test unit for User entity
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 9, 2011
 * @see org.youtestit.datamodel.entity.User
 */
public class UserTest extends AbstractEntityTest {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserTest.class);


    // =========================================================================
    // METHODS
    // =========================================================================

    /**
     * All user are identify by their login. Two User object ars equals if they
     * have the same login. It's database table ID. This test allow to check it
     * and verify if basic java usage work well with User entity
     * 
     * @throws ClientException if test fail
     */
    @Test
    public void equalsHashCodeTest() throws ClientException {
        LOGGER.info("verify equals and hash code of User entity");

        final User user = new User("joe", "joe@youtestit.org", "kqz@15#$W", "Joe", "Smith");
        final User userB = new User("foo", "foo@youtestit.org", "kqz@15#$W", "Foo", "Bar");

        assertFalse(user.equals(userB));

        final User userC = new User("joe", "foo@youtestit.org", "kqz@15#$W", "Foo", "Bar");
        assertTrue(user.equals(userC));
        userC.setLogin("joe2");


        final List<User> users = new ArrayList<User>();
        users.add(user);
        users.add(userB);
        users.add(userC);

        assertTrue(users.contains(user));
        assertTrue(users.contains(userB));
        assertTrue(users.contains(userC));

    }

    @Test
    public void test() throws ClientException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        LOGGER.info("run test...");
        final String url = "jdbc:derby:memory:database;create=true"; //
        final String login = "";
        final String password = "";
        final String driver = "org.apache.derby.jdbc.EmbeddedDriver";


        Class.forName(driver).newInstance();
        Connection connexion = DriverManager.getConnection(url, login ,  password);
        
        assertNotNull(connexion);
        
        
        final Properties props = new Properties();
         props.put("hibernate.hbm2ddl.auto", "create-drop");
         props.put("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
         props.put("hibernate.connection.url", url);
         props.put("hibernate.connection.driver_class",driver);
         props.put("hibernate.connection.username", login);
         props.put("hibernate.connection.password", password);
        
         final Ejb3Configuration cfg = new Ejb3Configuration();
         cfg.addProperties(props);
        
         emFactory = cfg.buildEntityManagerFactory();
        
         entityManager = emFactory.createEntityManager();
         assertNotNull(entityManager);
         

    }


    @Override
    public void persistanceTest() throws ClientException {
        // final String url = "jdbc:hsqldb:mem:testdb;create=true";
        // final String login = "sa";
        // final String password = "sa";
        // final String driver = "org.hsqldb.jdbcDriver";


        // try {
        // final Connection connexion = DriverManager.getConnection(url, login,
        // password);
        // assertNotNull(connexion);
        // } catch (SQLException e) {
        // throw new ClientException("can't connect to memory database",e);
        // }
        //
        // final Properties props = new Properties();
        // props.put("hibernate.hbm2ddl.auto", "create-drop");
        // props.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        // props.put("hibernate.connection.url", "jdbc:hsqldb:mem:testdb");
        // props.put("hibernate.connection.driver_class",
        // "org.hsqldb.jdbcDriver");
        // props.put("hibernate.connection.username", login);
        // props.put("hibernate.connection.password", password);
        //
        // final Ejb3Configuration cfg = new Ejb3Configuration();
        // cfg.addProperties(props);
        //
        // emFactory = cfg.buildEntityManagerFactory();
        //
        // entityManager = emFactory.createEntityManager();


        // emFactory =
        // Persistence.createEntityManagerFactory("testPU",properties);
        // entityManager = emFactory.createEntityManager();

        assertNotNull(entityManager);

    }

    /*
     * Map<String, Object> props = new HashMap<String, Object>();
     * props.put(EJBContainer.MODULES, new File [] {new File
     * ("target/classes"),new File ("target/test-classes")});
     * props.put(EJBContainer.APP_NAME, "youtestit");
     * props.put(EJBContainer.PROVIDER,
     * "org.glassfish.ejb.embedded.EJBContainerProviderImpl");
     * 
     * EJBContainer ejbContainer = EJBContainer.createEJBContainer(props);
     * Context ctx = ejbContainer.getContext(); assertNotNull(ctx);
     */

}
