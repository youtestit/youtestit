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
 * Test unit for User entity.
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
    private static final Logger LOGGER        = LoggerFactory.getLogger(UserTest.class);

    /** The Constant KEY_GEST. */
    private static final String KEY_GEST      = "Gest";

    /** The Constant QUERY_PROFILE. */
    private static final String QUERY_PROFILE = "from Profile";


    /** The Constant FOO_EMAIL. */
    private static final String FOO_EMAIL     = "foo@youtestit.org";
    
    /** The Constant LOGIN_FOO. */
    private static final String FOO_LOGIN     = "Foo";
    
    /** The Constant BAR_NAME. */
    private static final String FOO_NAME      = "Bar";

    
    /** The Constant JOE_EMAIL. */
    private static final String JOE_EMAIL     = "joe@youtestit.org";

    /** The Constant LOGIN_JOE. */
    private static final String JOE_LOGIN     = "Joe";
    
    /** The Constant NAME_JOE. */
    private static final String JOE_NAME      = "joe";


    /** The Constant LULIN_NAME. */
    private static final String LULIN_NAME    = "lulin";

    
    /** The Constant SMITH_NAME. */
    private static final String SMITH_NAME    = "Smith";


    
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
        final User user = new User(JOE_NAME, JOE_EMAIL, password, JOE_LOGIN, SMITH_NAME, new Profile(KEY_GEST));
        final User userB = new User(FOO_LOGIN, FOO_EMAIL, password, FOO_LOGIN, FOO_NAME, new Profile(KEY_GEST));

        assertFalse(user.equals(userB));

        final User userC = new User(JOE_NAME, FOO_EMAIL, password, FOO_LOGIN, FOO_NAME, new Profile(KEY_GEST));
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

        final String query = String.format("from %s", User.class.getSimpleName());

        final List<User> resultA = entityManager.createQuery(query, User.class).getResultList();
        assertNotNull(resultA);
        assertTrue(resultA.isEmpty());

        // user must have an profile
        beginTransaction();
        final Profile profile = new Profile(KEY_GEST);
        entityManager.persist(profile);
        commitTransaction();

        final Profile profileSaved = entityManager.createQuery(QUERY_PROFILE, Profile.class).getSingleResult();


        // persiste user
        beginTransaction();
        final User user = new User(JOE_NAME, JOE_EMAIL, "kqz@@15#$W", JOE_LOGIN, SMITH_NAME, profileSaved);

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
     * Allow to tests User NamedQuery.
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
        final Profile profileSaved = entityManager.createQuery(QUERY_PROFILE, Profile.class).getSingleResult();


        final User user = new User(JOE_NAME, JOE_EMAIL, "123", JOE_LOGIN, SMITH_NAME, profileSaved);
        final List<User> users = new ArrayList<User>();
        users.add(user);
        users.add(new User("foo", FOO_EMAIL, "1234", FOO_LOGIN, FOO_NAME, profileSaved));
        users.add(new User("glomli", JOE_EMAIL, "12345", "Glomli", "Roifur", profileSaved));
        users.add(new User(LULIN_NAME, JOE_EMAIL, "123456", LULIN_NAME, "Ushissham", profileSaved));

        beginTransaction();
        for (User userItem : users) {
            entityManager.persist(userItem);
        }
        commitTransaction();


        final List<User> allUser = entityManager.createNamedQuery(User.ALL_USERS, User.class).getResultList();
        assertNotNull(allUser);
        assertSame(allUser.size(), users.size());

        final User userByLogin = entityManager.createNamedQuery(User.USER_BY_LOGIN, User.class).setParameter(
                User.USER_BY_LOGIN_PARAM_LOGIN, JOE_NAME).getSingleResult();
        assertNotNull(userByLogin);
        assertEquals(user, userByLogin);
        closeEntityManager();
    }


}
