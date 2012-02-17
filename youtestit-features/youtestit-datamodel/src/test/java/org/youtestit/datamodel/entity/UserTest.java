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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
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

    /** The Constant KEY_GEST. */
    private static final String  KEY_GEST        = "Gest";

    /** The Constant QUERY_PROFILE. */
    private static final String  QUERY_PROFILE   = "from Profile";


    /** The Constant FOO_EMAIL. */
    private static final String  FOO_EMAIL       = "foo@youtestit.org";

    /** The Constant LOGIN_FOO. */
    private static final String  FOO_LOGIN       = "Foo";

    /** The Constant BAR_NAME. */
    private static final String  FOO_NAME        = "Bar";


    /** The Constant JOE_EMAIL. */
    private static final String  JOE_EMAIL       = "joe@youtestit.org";

    /** The Constant LOGIN_JOE. */
    private static final String  JOE_LOGIN       = "joe";

    /** The Constant NAME_JOE. */
    private static final String  JOE_NAME        = "Joe";

    /** The Constant JOE_PASSWORD. */
    private static final String  JOE_PASSWORD    = "password";


    /** The Constant LULIN_NAME. */
    private static final String  LULIN_NAME      = "lulin";


    /** The Constant SMITH_NAME. */
    private static final String  SMITH_NAME      = "Smith";

    /** The Constant JOE_DESCRIPTION. */
    private static final String  JOE_DESCRIPTION = "Joe user";

    /** The Constant JOE_CELLULAR. */
    private static final String  JOE_CELLULAR    = "0202020202";

    /** The Constant JOE_PHONE. */
    private static final String  JOE_PHONE       = "0101010101";

    /** The Constant JOE_GRAVATAR. */
    private static final String  JOE_GRAVATAR    = "/joe/gravatar.png";

    /** The Constant ADMIN. */
    private static final Profile ADMIN           = new Profile("admin");

    /** The Constant GROUP. */
    private static final Group   GROUP           = new Group(99, "group");

    /** The Constant JOE_OFFICE. */
    private static final String  JOE_OFFICE      = "w4a15";


    // =========================================================================
    // METHODS
    // =========================================================================

    /**
     * Allow to test instantiate user.
     * 
     * @throws ClientException the client exception
     */
    @Test
    public void testUser() throws ClientException {
        logInfoMSG("testUser()");
        final int userNbGroup = 1;
        final User user = createSimpleUser();
        assertNotNull(user);
        assertEquals(user.getLogin(), JOE_LOGIN);
        assertEquals(user.getEmail(), JOE_EMAIL);
        assertEquals(user.getPassword(), JOE_PASSWORD);
        assertEquals(user.getFirstname(), JOE_NAME);
        assertEquals(user.getLastname(), SMITH_NAME);
        assertEquals(user.getPhoneNumber(), JOE_PHONE);
        assertEquals(user.getCellularNumber(), JOE_CELLULAR);
        assertEquals(user.getDescription(), JOE_DESCRIPTION);
        assertEquals(user.getOffice(), JOE_OFFICE);
        assertTrue(user.isEnable());
        assertEquals(user.getProfile(), ADMIN);
        assertEquals(user.getGroups().size(), userNbGroup);
        assertEquals(user.getGroups().get(0), GROUP);
        assertEquals(user.toString(), userToString());
    }

    /**
     * Generate render that complete simple user must have
     * 
     * @return the render
     */
    protected String userToString() {
        final StringBuilder user2Str = new StringBuilder("User [");
        user2Str.append("login=joe,");
        user2Str.append("email=joe@youtestit.org,");
        user2Str.append("password=password,");
        user2Str.append("firstname=Joe,");
        user2Str.append("lastname=Smith,");
        user2Str.append("gravatar=/joe/gravatar.png,");
        user2Str.append("phoneNumber=0101010101,");
        user2Str.append("cellularNumber=0202020202,");
        user2Str.append("description=Joe user,");
        user2Str.append("office=w4a15,");
        user2Str.append("enable=true,");
        user2Str.append("]");
        return user2Str.toString();
    }

    /**
     * Allow to creates a simple user with all parameters set.
     * 
     * @return the complete user
     * @throws ClientException if creation fail
     */
    protected User createSimpleUser() throws ClientException {
        final List<Group> groups = new ArrayList<Group>();
        groups.add(GROUP);

        final User user = new User();
        user.setLogin(JOE_LOGIN);
        user.setEmail(JOE_EMAIL);
        user.setPassword(JOE_PASSWORD);
        user.setFirstname(JOE_NAME);
        user.setLastname(SMITH_NAME);
        user.setGravatar(JOE_GRAVATAR);
        user.setPhoneNumber(JOE_PHONE);
        user.setCellularNumber(JOE_CELLULAR);
        user.setDescription(JOE_DESCRIPTION);
        user.setOffice(JOE_OFFICE);
        user.setEnable(true);
        user.setProfile(ADMIN);
        user.setGroups(groups);
        return user;
    }

    /**
     * All user are identify by their login. Two User object ars equals if they
     * have the same login. It's database table ID. This test allow to check it
     * and verify if basic java usage work well with User entity
     * 
     * @throws ClientException if test fail
     */
    @Test
    public void equalsHashCodeTest() throws ClientException {
        logInfoMSG("equalsHashCodeTest : verify equals and hash code of User entity");
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
