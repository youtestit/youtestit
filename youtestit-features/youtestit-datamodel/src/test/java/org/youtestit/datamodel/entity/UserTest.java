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


    /**
     *  {@inheritDoc}
     */
    @Override
    public void persistenceTest() throws ClientException {
        assertNotNull(entityManager);

        String query = String.format("from %s", User.class.getSimpleName());
        
        final List<User> resultA = entityManager.createQuery(query,User.class).getResultList();
        assertNotNull(resultA);

        beginTransaction();
        final User user = new User("joe", "joe@youtestit.org", "kqz@15#$W", "Joe", "Smith");
        entityManager.persist(user);
        commitTransaction();

        final List<User> resultB = entityManager.createQuery(query,User.class).getResultList();
        assertNotNull(resultB);

    }


}
