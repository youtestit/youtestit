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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.commons.utils.sha1.Sha1Encryption;
import org.youtestit.datamodel.entity.TestCaseHelper;
import org.youtestit.datamodel.entity.User;


/**
 * UserDAOTest.
 * 
 * @see org.youtestit.datamodel.dao.UserDAO
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 11, 2011
 */
public class UserDAOTest extends TestCaseHelper {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant ERR_ERROR_MUST_OCCUR. */
    private static final String   ERR_ERROR_MUST_OCCUR = "Error must be occur";

    /** The Constant LOGIN. */
    private static final String   LOGIN                = "foo";

    /** The Constant EMAIL. */
    private static final String   EMAIL                = "foo@youtestit.org";

    /** The Constant PASSWORD. */
    private static final String   PASSWORD             = "1234";

    /** The Constant FIRSTNAME. */
    private static final String   FIRSTNAME            = "Foo";

    /** The Constant LASTNAME. */
    private static final String   LASTNAME             = "Bar";

    /** The Constant LOGIN_LULIN. */
    private static final String   LOGIN_LULIN          = "lulin";

    /** The Constant LOGIN_GLOMLI. */
    private static final String   LOGIN_GLOMLI         = "glomli";

    /** The Constant LOGINS. */
    private static final String[] LOGINS               = { LOGIN, LOGIN_GLOMLI, LOGIN_LULIN };

    // =========================================================================
    // TESTS
    // =========================================================================

    /**
     * Test create user.
     * 
     * @throws ClientException if an error has occur.
     */
    @Test
    public void testCreateUser() throws ClientException {
        loadEntityManager();
        assertNotNull(entityManager);

        final User user = new User(LOGIN, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, null);
        final UserDAO serviceDAO = new UserDAO(entityManager);

        try {
            serviceDAO.createUser(null);
            throw new ClientException(ERR_ERROR_MUST_OCCUR);
        } catch (ClientException e) {
            assertNotNull(ERR_ERROR_MUST_OCCUR, e);
        }

        beginTransaction();
        serviceDAO.createUser(user);
        commitTransaction();

        final User userSaved = entityManager.createNamedQuery(User.USER_BY_LOGIN, User.class).setParameter(
                User.USER_BY_LOGIN_PARAM_LOGIN, user.getLogin()).getSingleResult();

        assertNotNull(userSaved);
        assertFalse(PASSWORD.equals(userSaved.getPassword()));


        try {
            serviceDAO.createUser(user);
            throw new ClientException(ERR_ERROR_MUST_OCCUR);
        } catch (ClientException e) {
            assertNotNull(ERR_ERROR_MUST_OCCUR, e);
        }
        closeEntityManager();
    }


    /**
     * Test save user.
     * 
     * @throws ClientException if an error has occur.
     */
    @Test
    public void testSaveUser() throws ClientException {
        loadEntityManager();
        assertNotNull(entityManager);

        final User user = new User(LOGIN, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, null);
        final UserDAO serviceDAO = new UserDAO(entityManager);

        beginTransaction();
        serviceDAO.createUser(user);
        commitTransaction();

        final Sha1Encryption sha1Service = Sha1Encryption.getInstance();
        final User userSaved = entityManager.createNamedQuery(User.USER_BY_LOGIN, User.class).setParameter(
                User.USER_BY_LOGIN_PARAM_LOGIN, user.getLogin()).getSingleResult();

        assertEquals(LOGIN, userSaved.getLogin());
        assertEquals(EMAIL, userSaved.getEmail());
        assertFalse(PASSWORD.equals(userSaved.getPassword()));
        assertEquals(sha1Service.encryptToSha1(PASSWORD), userSaved.getPassword());
        assertEquals(FIRSTNAME, userSaved.getFirstname());
        assertEquals(LASTNAME, userSaved.getLastname());

        final String newEmail = "glomli@youtestit.org";
        final String newPassword = "12345";
        final String newFirstname = "Glomli";
        final String newLastname = "Roifur";

        userSaved.setEmail(newEmail);
        userSaved.setPassword(newPassword);
        userSaved.setFirstname(newFirstname);
        userSaved.setLastname(newLastname);

        beginTransaction();
        serviceDAO.saveUser(userSaved);
        commitTransaction();

        final User newUserSaved = entityManager.createNamedQuery(User.USER_BY_LOGIN, User.class).setParameter(
                User.USER_BY_LOGIN_PARAM_LOGIN, LOGIN).getSingleResult();

        assertNotNull(newUserSaved);
        assertEquals(newEmail, newUserSaved.getEmail());
        assertEquals(newPassword, newUserSaved.getPassword());
        assertEquals(newFirstname, newUserSaved.getFirstname());
        assertEquals(newLastname, newUserSaved.getLastname());
        closeEntityManager();
    }

    /**
     * Test get all users.
     * 
     * @throws ClientException if an error has occur.
     */
    @Test
    public void testGetAllUsers() throws ClientException {
        loadEntityManager();
        assertNotNull(entityManager);
        createUsers();

        final UserDAO serviceDAO = new UserDAO(entityManager);

        final List<User> usersSaved = serviceDAO.getAllUsers();
        assertNotNull(usersSaved);

        assertSame(LOGINS.length, usersSaved.size());
        for (int index = 0; index < LOGINS.length; index++) {
            assertEquals(LOGINS[index], usersSaved.get(index).getLogin());
        }

        closeEntityManager();
    }


    /**
     * Test get user by login.
     * 
     * @throws ClientException if an error has occur.
     */
    @Test
    public void testGetUserByLogin() throws ClientException {
        loadEntityManager();
        assertNotNull(entityManager);

        final User user = new User(LOGIN, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, null);
        final UserDAO serviceDAO = new UserDAO(entityManager);

        beginTransaction();
        serviceDAO.createUser(user);
        commitTransaction();

        final User userSaved = serviceDAO.getUserByLogin(LOGIN);
        assertNotNull(userSaved);

        final User newUserSaved = serviceDAO.getUserByLogin("toto");
        assertNull(newUserSaved);

        closeEntityManager();
    }


    /**
     * Test delete user.
     * 
     * @throws ClientException if an error has occur.
     */
    @Test
    public void testDeleteUser() throws ClientException {
        loadEntityManager();
        assertNotNull(entityManager);

        loadEntityManager();
        assertNotNull(entityManager);

        final User user = new User(LOGIN, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, null);
        final UserDAO serviceDAO = new UserDAO(entityManager);

        beginTransaction();
        serviceDAO.createUser(user);
        commitTransaction();

        final User userSaved = serviceDAO.getUserByLogin(LOGIN);
        assertNotNull(userSaved);


        beginTransaction();
        serviceDAO.deleteUser(userSaved);
        commitTransaction();

        final User userDeleted = serviceDAO.getUserByLogin(LOGIN);
        assertNull(userDeleted);

        closeEntityManager();
    }


    /**
     * Test delete users.
     * 
     * @throws ClientException if an error has occur.
     */
    @Test
    public void testDeleteUsers() throws ClientException {
        loadEntityManager();
        assertNotNull(entityManager);

        createUsers();
        final UserDAO serviceDAO = new UserDAO(entityManager);
        final List<User> usersSaved = serviceDAO.getAllUsers();
        assertNotNull(usersSaved);
        assertSame(LOGINS.length, usersSaved.size());

        beginTransaction();
        serviceDAO.deleteUsers(usersSaved);
        commitTransaction();
        
        final List<User> usersDeleted = serviceDAO.getAllUsers();
        assertNotNull(usersDeleted);
        assertTrue(usersDeleted.isEmpty());
        
        closeEntityManager();
    }


    // =========================================================================
    // METHODS
    // =========================================================================

    /**
     * Allow to creates multiple users
     * 
     * @throws ClientException if an error has occur.
     */
    protected void createUsers() throws ClientException {
        final User user = new User(LOGIN, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, null);
        final List<User> users = new ArrayList<User>();
        users.add(user);
        users.add(new User(LOGINS[1], EMAIL, "12345", "Glomli", "Roifur", null));
        users.add(new User(LOGINS[2], EMAIL, "123456", LOGIN_LULIN, "Ushissham", null));

        beginTransaction();
        for (User userItem : users) {
            entityManager.persist(userItem);
        }
        commitTransaction();
    }
}
