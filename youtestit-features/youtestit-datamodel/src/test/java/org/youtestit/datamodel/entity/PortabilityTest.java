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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.datamodel.enums.BrowserType;
import org.youtestit.datamodel.enums.OsArchi;
import org.youtestit.datamodel.enums.OsType;


/**
 * Test unit for Group entity.
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 11, 2011
 * @see org.youtestit.datamodel.entity.User
 */
public class PortabilityTest extends AbstractEntityTest {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant LOGGER. */
    private static final Logger LOGGER  = LoggerFactory.getLogger(PortabilityTest.class);

    /** The Constant QUERY. */
    private static final String QUERY   = "from Portability";

    /** windows OS */
    private final Os            windows = new Os("windows", OsType.WINDOWS, OsArchi.PC_32_BITS);

    /** Ubuntu OS */
    private final Os            ubuntu  = new Os("ubuntu", OsType.LINUX, OsArchi.PC_64_BITS);

    /** firefox browser */
    private final Browser       firefox = new Browser(BrowserType.FIREFOX, "8.0");

    /** Internet Explorer 9 */
    private final Browser       ie9     = new Browser(BrowserType.IE, "9.0");


    // =========================================================================
    // METHODS
    // =========================================================================

    /**
     * All portability entities are identify by their uid . Two Document object
     * are equals if they have the same uid. The Uid's database table ID. This
     * test allow to check it and verify if basic java usage work well.
     * 
     * @throws ClientException if test fail
     */
    @Test
    public void equalsHashCodeTest() throws ClientException {
        LOGGER.info("equalsHashCodeTest");

        final Portability ubuntuFF = new Portability(1, ubuntu, firefox, true);
        final Portability windowsIE = new Portability(2, windows, ie9, true);

        assertFalse(ubuntuFF.equals(windowsIE));

        final Portability debian = new Portability(1, ubuntu, firefox, true);
        assertTrue(ubuntuFF.equals(debian));

        final List<Portability> dublinCores = new ArrayList<Portability>();
        dublinCores.add(ubuntuFF);
        dublinCores.add(windowsIE);

        assertTrue(dublinCores.contains(ubuntuFF));
        assertTrue(dublinCores.contains(windowsIE));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void persistenceTest() throws ClientException {
        LOGGER.info("persistenceTest");
        assertNotNull(entityManager);

        // write references datas
        beginTransaction();
        entityManager.persist(ubuntu);
        entityManager.persist(windows);

        entityManager.persist(firefox);
        entityManager.persist(ie9);
        commitTransaction();

        // test portability
        List<Portability> portabilities = entityManager.createQuery(QUERY, Portability.class).getResultList();
        assertNotNull(portabilities);
        assertTrue(portabilities.isEmpty());

        final Portability portability = new Portability(ubuntu, firefox);
        beginTransaction();
        entityManager.persist(portability);
        commitTransaction();

        portabilities = entityManager.createQuery(QUERY, Portability.class).getResultList();
        assertNotNull(portabilities);
        assertFalse(portabilities.isEmpty());
        assertEquals(portabilities.size(), 1);
    }
}
