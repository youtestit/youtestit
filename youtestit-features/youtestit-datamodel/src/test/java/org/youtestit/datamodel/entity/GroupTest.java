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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.youtestit.commons.utils.exceptions.ClientException;


/**
 * Test unit for Group entity
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 11, 2011
 * @see org.youtestit.datamodel.entity.User
 */
public class GroupTest extends AbstractEntityTest {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant LOGGER. */
    private static final Logger LOGGER    = LoggerFactory.getLogger(GroupTest.class);

    /** The Constant KEY_ADMIN. */
    private static final String KEY_ADMIN = "admin";

    // =========================================================================
    // METHODS
    // =========================================================================

    /**
     * All group are identify by their uid. Two Group object are equals if they
     * have the same uid. It's database table ID. This test allow to check it
     * and verify if basic java usage work well with User entity
     * 
     * @throws ClientException if test fail
     */
    @Test
    public void equalsHashCodeTest() throws ClientException {
        LOGGER.info("verify equals and hash code of group entity");

        final Group group = new Group(1, "Administrator");
        final Group groupB = new Group(2, "Customer");

        assertFalse(group.equals(groupB));

        final Group groupC = new Group(1, "Administrator2");
        assertTrue(group.equals(groupC));
        groupC.setUid(3);


        final List<Group> profiles = new ArrayList<Group>();
        profiles.add(group);
        profiles.add(groupB);
        profiles.add(groupC);

        assertTrue(profiles.contains(group));
        assertTrue(profiles.contains(groupB));
        assertTrue(profiles.contains(groupC));

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void persistenceTest() throws ClientException {
        assertNotNull(entityManager);
        final String queryGroup = "from Group";
        final String password = "kqz@15#$W";

        final List<Group> groups = entityManager.createQuery(queryGroup, Group.class).getResultList();
        assertNotNull(groups);

        beginTransaction();
        final Profile profile = new Profile(KEY_ADMIN, true, true);
        entityManager.persist(profile);
        commitTransaction();
        final Profile profileSaved = entityManager.createQuery("from Profile", Profile.class).getSingleResult();

        beginTransaction();
        final User user = new User("joe", "joe@youtestit.org", password, "Joe", "Smith", profileSaved);
        entityManager.persist(user);
        final User userB = new User("foo", "foo@youtestit.org", password, "Foo", "Bar", profileSaved);
        entityManager.persist(userB);
        commitTransaction();

        final List<User> users = entityManager.createQuery("from User", User.class).getResultList();
        assertNotNull(users);

        final String groupName = "Administrators";
        beginTransaction();
        final Group group = new Group(groupName, users);
        entityManager.persist(group);
        commitTransaction();

        final List<Group> groupsResult = entityManager.createQuery(queryGroup, Group.class).getResultList();
        assertNotNull(groupsResult);
        assertFalse(groupsResult.isEmpty());

        final Group groupResult = groupsResult.get(0);
        assertNotNull(groupResult.getName());
        assertNotNull(groupResult.getUsers());
        assertSame(groupResult.getUid(), 1);
        assertSame(groupResult.getUsers().size(), 2);
        assertEquals(groupName, groupResult.getName());
        assertUserEquals(groupResult.getUsers().get(0), user, profile);
        assertUserEquals(groupResult.getUsers().get(1), userB, profile);


    }


    /**
     * Assert user equals.
     * 
     * @param userRef the user ref
     * @param userResult the user result
     * @param profile the profile
     * @throws ClientException the client exception
     */
    private void assertUserEquals(final User userRef, final User userResult, final Profile profile)
            throws ClientException {
        assertEquals(userResult.getLogin(), userRef.getLogin());
        assertEquals(userResult.getCellularNumber(), userRef.getCellularNumber());
        assertEquals(userResult.getDescription(), userRef.getDescription());
        assertEquals(userResult.getEmail(), userRef.getEmail());
        assertEquals(userResult.getFirstname(), userRef.getFirstname());
        assertEquals(userResult.getLastname(), userRef.getLastname());
        assertEquals(userResult.getOffice(), userRef.getOffice());
        assertEquals(userResult.getPassword(), userRef.getPassword());
        assertEquals(userResult.getPhoneNumber(), userRef.getPhoneNumber());
        assertNotNull(userResult.getProfile());
        assertEquals(userResult.getProfile().getName(), profile.getName());
    }
}
