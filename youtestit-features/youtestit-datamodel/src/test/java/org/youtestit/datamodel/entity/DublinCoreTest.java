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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.youtestit.commons.utils.exceptions.ClientException;


/**
 * Test unit for Group entity.
 *
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 11, 2011
 * @see org.youtestit.datamodel.entity.User
 */
public class DublinCoreTest extends AbstractEntityTest {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DublinCoreTest.class);

    /** The Constant QUERY. */
    private static final String QUERY  = "from DublinCore";

    /** The doc. */
    private DublinCore          doc;

    /** The doc child. */
    private DublinCore          docChild;

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
        final String title = "projectA";
        final String path = "/projectA";

        final DublinCore dublinCoreA = new DublinCore(1, title, path);
        final DublinCore dublinCoreB = new DublinCore(2, "projectB", "/projectB");

        assertFalse(dublinCoreA.equals(dublinCoreB));

        final DublinCore dublinCoreC = new DublinCore(1, title, path);
        assertTrue(dublinCoreA.equals(dublinCoreC));


        final List<DublinCore> dublinCores = new ArrayList<DublinCore>();
        dublinCores.add(dublinCoreA);
        dublinCores.add(dublinCoreB);

        assertTrue(dublinCores.contains(dublinCoreA));
        assertTrue(dublinCores.contains(dublinCoreB));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void persistenceTest() throws ClientException {
        assertNotNull(entityManager);


        List<DublinCore> dublincores = entityManager.createQuery(QUERY, DublinCore.class).getResultList();
        assertNotNull(dublincores);
        assertTrue(dublincores.isEmpty());

        doc = new DublinCore("document", "/document");
        doc.setSubject("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
        doc.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc leo est, pulvinar vitae tincidunt a");

        final Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        doc.setDateCreation(calendar);

        beginTransaction();
        entityManager.persist(doc);
        commitTransaction();

        dublincores = entityManager.createQuery(QUERY, DublinCore.class).getResultList();
        assertNotNull(dublincores);
        assertEquals(dublincores.size(), 1);

        // create user .........................................................
        LOGGER.info("create creator....");
        beginTransaction();
        final Profile profile = new Profile("admin");
        entityManager.persist(profile);
        commitTransaction();

        beginTransaction();
        final User user = new User("joe", "joe@youtestit.org", "kqz@15#$W", "Joe", "Smith", profile);
        entityManager.persist(user);
        commitTransaction();

        // update doc ..........................................................
        doc.setCreator(user);
        beginTransaction();
        entityManager.merge(doc);
        commitTransaction();
        LOGGER.info(doc.toString());

    }


    /**
     * Test add child.
     * 
     * @throws ClientException the client exception
     */
    @Test
    public void testAddChild() throws ClientException {
        loadEntityManager();
        addChild();
        closeEntityManager();
    }

    /**
     * Adds the child.
     *
     * @throws ClientException the client exception
     */
    protected void addChild() throws ClientException {
        doc = new DublinCore("document", "/document");
        beginTransaction();
        entityManager.persist(doc);
        commitTransaction();

        // create child document ...............................................
        LOGGER.info("create child document....");
        docChild = new DublinCore("document child", "/document/document_child");

        doc.addChild(docChild);

        beginTransaction();
        entityManager.persist(docChild);
        entityManager.merge(doc);
        commitTransaction();

        final List<DublinCore> dublincores = entityManager.createQuery(QUERY, DublinCore.class).getResultList();
        assertNotNull(dublincores);
        assertEquals(dublincores.size(), 2);
    }


    /**
     * Test remove child.
     *
     * @throws ClientException the client exception
     */
    @Test
    public void testRemoveChild() throws ClientException {
        loadEntityManager();
        addChild();

        // remove child document ...............................................
        doc.removeChild(docChild);
        beginTransaction();
        entityManager.merge(doc);
        entityManager.remove(docChild);
        commitTransaction();

        List<DublinCore> dublincores = entityManager.createQuery(QUERY, DublinCore.class).getResultList();
        assertNotNull(dublincores);
        assertEquals(dublincores.size(), 1);
        closeEntityManager();
    }

}