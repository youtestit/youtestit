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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

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
    private static final Logger LOGGER   = LoggerFactory.getLogger(UserTest.class);

    /** The Constant KEY_GEST. */
    private static final String KEY_GEST = "Gest";

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
        final String password = "kqz@15#$W";
        final User user = new User("joe", "joe@youtestit.org", password, "Joe", "Smith", new Profile(KEY_GEST));
        final User userB = new User("foo", "foo@youtestit.org", password, "Foo", "Bar", new Profile(KEY_GEST));

        assertFalse(user.equals(userB));

        final User userC = new User("joe", "foo@youtestit.org", password, "Foo", "Bar", new Profile(KEY_GEST));
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
     * {@inheritDoc}
     */
    @Override
    public void persistenceTest() throws ClientException {
        assertNotNull(entityManager);

        String query = String.format("from %s", User.class.getSimpleName());

        final List<User> resultA = entityManager.createQuery(query, User.class).getResultList();
        assertNotNull(resultA);
        assertTrue(resultA.isEmpty());

        // user must have an profile
        beginTransaction();
        final Profile profile = new Profile(KEY_GEST);
        entityManager.persist(profile);
        commitTransaction();

        final Profile profileSaved = entityManager.createQuery("from Profile", Profile.class).getSingleResult();


        // persiste user
        beginTransaction();
        final User user = new User("joe", "joe@youtestit.org", "kqz@15#$W", "Joe", "Smith", profileSaved);

        entityManager.persist(user);
        commitTransaction();

        final List<User> resultB = entityManager.createQuery(query, User.class).getResultList();
        assertNotNull(resultB);
        assertFalse(resultB.isEmpty());
        assertSame(resultB.size(), 1);


        final User userResult = resultB.get(0);

        assertEquals(user, userResult);
        assertEquals(user.getCellularNumber(), userResult.getCellularNumber());
        assertEquals(user.getDescription(), userResult.getDescription());
        assertEquals(user.getEmail(), userResult.getEmail());
        assertEquals(user.getFirstname(), userResult.getFirstname());
        assertEquals(user.getGravatar(), userResult.getGravatar());
        assertEquals(user.getLastname(), userResult.getLastname());
        assertEquals(user.getOffice(), userResult.getOffice());
        assertEquals(user.getPassword(), userResult.getPassword());
        assertEquals(user.getPhoneNumber(), userResult.getPhoneNumber());
    }


    /**
     * Allow to tests User NamedQuery
     * 
     * @throws ClientException the client exception
     */
    @Test
    public void testNamedQuery() throws ClientException {
        // user must have an profile
        loadEntityManager();
        beginTransaction();
        final Profile profile = new Profile(KEY_GEST);
        entityManager.persist(profile);
        commitTransaction();
        final Profile profileSaved = entityManager.createQuery("from Profile", Profile.class).getSingleResult();


        final User user = new User("joe", "joe@youtestit.org", "123", "Joe", "Smith", profileSaved);
        final List<User> users = new ArrayList<User>();
        users.add(user);
        users.add(new User("foo", "foo@youtestit.org", "1234", "Foo", "Bar", profileSaved));
        users.add(new User("glomli", "joe@youtestit.org", "12345", "Glomli", "Roifur", profileSaved));
        users.add(new User("lulin", "joe@youtestit.org", "123456", "lulin", "Ushissham", profileSaved));

        beginTransaction();
        for (User userItem : users) {
            entityManager.persist(userItem);
        }
        commitTransaction();


        List<User> allUser = entityManager.createNamedQuery(User.ALL_USERS, User.class).getResultList();
        assertNotNull(allUser);
        assertSame(allUser.size(), users.size());

        User userByLogin = entityManager.createNamedQuery(User.USER_BY_LOGIN, User.class).setParameter(
                User.USER_BY_LOGIN_PARAM_LOGIN, "joe").getSingleResult();
        assertNotNull(userByLogin);
        assertEquals(user, userByLogin);
        closeEntityManager();
    }


}
