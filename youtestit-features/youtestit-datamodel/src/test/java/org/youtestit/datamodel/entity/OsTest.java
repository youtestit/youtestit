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
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.datamodel.enums.OsArchi;
import org.youtestit.datamodel.enums.OsType;


/**
 * Test unit for Group entity
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 11, 2011
 * @see org.youtestit.datamodel.entity.User
 */
public class OsTest extends AbstractEntityTest {
    private static final String OS_UBUNTU = "ubuntu";

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant LOGGER. */
    private static final Logger LOGGER    = LoggerFactory.getLogger(OsTest.class);

    /** The Constant KEY_ADMIN. */

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

        final Os osA = new Os(OS_UBUNTU, OsType.LINUX, OsArchi.PC_64_BITS);
        final Os osB = new Os("windows Xp", OsType.WINDOWS, OsArchi.PC_32_BITS);

        assertFalse(osA.equals(osB));

        final Os osC = new Os(OS_UBUNTU, OsType.LINUX, OsArchi.PC_64_BITS);
        assertTrue(osA.equals(osC));


        final List<Os> operatingSystems = new ArrayList<Os>();
        operatingSystems.add(osA);
        operatingSystems.add(osB);
        operatingSystems.add(osC);

        assertTrue(operatingSystems.contains(osC));
        assertTrue(operatingSystems.contains(osB));
        assertTrue(operatingSystems.contains(osC));

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void persistenceTest() throws ClientException {
        assertNotNull(entityManager);

        final String query = "from  Os";
        List<Os> operatingSystems = entityManager.createQuery(query, Os.class).getResultList();
        assertNotNull(operatingSystems);
        assertTrue(operatingSystems.isEmpty());

        final Os ubuntu = new Os(OS_UBUNTU, OsType.LINUX, OsArchi.PC_64_BITS);
        beginTransaction();
        entityManager.persist(ubuntu);
        commitTransaction();

        operatingSystems = entityManager.createQuery(query, Os.class).getResultList();
        assertNotNull(operatingSystems);
        assertEquals(operatingSystems.size(), 1);

        Os osToCheck = operatingSystems.get(0);
        assertEquals(ubuntu, osToCheck);

    }
}
