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
 * Test unit for User entity
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 9, 2011
 * @see org.youtestit.datamodel.entity.User
 */
public class ProfileTest extends AbstractEntityTest {
    private static final String KEY_ADMIN = "admin";
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileTest.class);


    // =========================================================================
    // METHODS
    // =========================================================================

    /**
     * All profile are identify by their name. Two Profile object ars equals if
     * they have the same name. It's database table ID. This test allow to check
     * it and verify if basic java usage work well with User entity
     * 
     * @throws ClientException if test fail
     */
    @Test
    public void equalsHashCodeTest() throws ClientException {
        LOGGER.info("verify equals and hash code of User entity");

        final Profile profile = new Profile(KEY_ADMIN, true, true);
        final Profile profileB = new Profile("foo", false, false);

        assertFalse(profile.equals(profileB));

        final Profile profileC = new Profile(KEY_ADMIN, false, false);
        assertTrue(profile.equals(profileC));
        profileC.setName("admin2");


        final List<Profile> profiles = new ArrayList<Profile>();
        profiles.add(profile);
        profiles.add(profileB);
        profiles.add(profileC);

        assertTrue(profiles.contains(profile));
        assertTrue(profiles.contains(profileB));
        assertTrue(profiles.contains(profileC));

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void persistenceTest() throws ClientException {
        assertNotNull(entityManager);

        final String query = String.format("from %s", Profile.class.getSimpleName());

        final List<Profile> resultA = entityManager.createQuery(query, Profile.class).getResultList();
        assertNotNull(resultA);
        assertTrue(resultA.isEmpty());

        beginTransaction();
        final Profile profile = new Profile(KEY_ADMIN, true, true);
        entityManager.persist(profile);
        commitTransaction();

        final List<Profile> resultB = entityManager.createQuery(query, Profile.class).getResultList();
        assertNotNull(resultB);
        assertFalse(resultB.isEmpty());
        assertSame(resultB.size(), 1);


        final Profile profileResult = resultB.get(0);

        assertEquals(profile, profileResult);
        assertEquals(profile.isAdministrator(), profileResult.isAdministrator());
        assertEquals(profile.isEnable(), profileResult.isEnable());
    }


}
