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
import org.youtestit.datamodel.enums.BrowserType;


/**
 * Test unit for Browser entity
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 11, 2011
 * @see org.youtestit.datamodel.entity.User
 */
public class BrowserTest extends AbstractEntityTest {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(BrowserTest.class);

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

        final Browser browserA = new Browser(BrowserType.FIREFOX, "8.0");
        final Browser browserB = new Browser(BrowserType.IE, "8");

        assertFalse(browserA.equals(browserB));

        final Browser browserC = new Browser(BrowserType.FIREFOX, "8.0");
        assertTrue(browserA.equals(browserC));


        final List<Browser> browsers = new ArrayList<Browser>();
        browsers.add(browserA);
        browsers.add(browserB);
        browsers.add(browserC);

        assertTrue(browsers.contains(browserA));
        assertTrue(browsers.contains(browserB));
        assertTrue(browsers.contains(browserC));

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void persistenceTest() throws ClientException {
        assertNotNull(entityManager);

        final String query = "from Browser";
        
        List<Browser> browsers = entityManager.createQuery(query, Browser.class).getResultList();
        assertNotNull(browsers);
        assertTrue(browsers.isEmpty());

        final Browser browserA = new Browser(BrowserType.FIREFOX, "8.0");

        beginTransaction();
        entityManager.persist(browserA);
        commitTransaction();


        browsers = entityManager.createQuery(query, Browser.class).getResultList();
        assertNotNull(browsers);
        assertFalse(browsers.isEmpty());
        assertEquals(browsers.size(), 1);


        final Browser browserToCheck = browsers.get(0);
        assertEquals(browserA, browserToCheck);

    }
}
