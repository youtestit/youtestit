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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class DocumentTest extends DublinCoreTest {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant LOGGER. */
    private static final Logger LOGGER     = LoggerFactory.getLogger(DocumentTest.class);

    /** The Constant QUERY. */
    private static final String QUERY      = "from Document";

    /** The Constant IE_9_0. */
    private static final String IE_9_0     = "9.0";

    /** The Constant FF_8_0. */
    private static final String FF_8_0     = "8.0";

    /** The Constant OS_WINDOWS. */
    private static final String OS_WINDOWS = "windows";

    /** The Constant OS_UBUNTU. */
    private static final String OS_UBUNTU  = "ubuntu";

    /** The Constant DOC_PATH. */
    private static final String DOC_PATH   = "/myDocument";

    /** The Constant DOC_TITLE. */
    private static final String DOC_TITLE  = "myDocument";
    // =========================================================================
    // METHODS
    // =========================================================================

    /**
     * All document entities are identify by their uid and their path. Two
     * Document object are equals if they have the same uid or if have the same
     * path. The Uid's database table ID. This test allow to check it and verify
     * if basic java usage work well.
     * 
     * @throws ClientException if test fail
     */
    @Test
    public void equalsHashCodeTest() throws ClientException {
        LOGGER.info("equalsHashCodeTest");
        final String title = "documentA";
        final String path = "/documentA";

        final DublinCore dublinCoreA = new DublinCore(1, title, path);
        final DublinCore dublinCoreB = new DublinCore(2, "documentB", "/documentB");

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

        List<Document> documents = entityManager.createQuery(QUERY, Document.class).getResultList();
        assertNotNull(documents);
        assertTrue(documents.isEmpty());

        final Document doc = new Document("document", "/document");
        doc.setSubject(super.getSubject());
        doc.setDescription(super.getDescription());

        final Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        doc.setDateCreation(calendar);

        beginTransaction();
        entityManager.persist(doc);
        commitTransaction();

        documents = entityManager.createQuery(QUERY, Document.class).getResultList();
        assertNotNull(documents);
        assertEquals(documents.size(), 1);

        super.addCreator(doc);
    }


    /**
     * Test to append protabilities.
     * 
     * @throws ClientException the client exception
     */
    @Test
    public void testAddProtabilities() throws ClientException {
        loadEntityManager();

        // create Os and browser ...............................................
        final Os ubuntu = new Os(OS_UBUNTU, OsType.LINUX, OsArchi.PC_64_BITS);
        final Browser firefox = new Browser(BrowserType.FIREFOX, FF_8_0);
        final Os windows = new Os(OS_WINDOWS, OsType.WINDOWS, OsArchi.PC_32_BITS);
        final Browser ie9 = new Browser(BrowserType.IE, IE_9_0);

        beginTransaction();
        entityManager.persist(ubuntu);
        entityManager.persist(firefox);
        entityManager.persist(windows);
        entityManager.persist(ie9);
        commitTransaction();

        // create portabilities ................................................
        final Portability ubuntuFF = new Portability(ubuntu, firefox);
        final Portability windowsIE = new Portability(windows, ie9);

        beginTransaction();
        entityManager.persist(ubuntuFF);
        entityManager.persist(windowsIE);
        commitTransaction();

        // associate with document .............................................
        final Document doc = new Document(DOC_TITLE, DOC_PATH);
        doc.addPortability(ubuntuFF);
        doc.addPortability(windowsIE);

        beginTransaction();
        entityManager.persist(doc);
        commitTransaction();

        final List<Document> documents = entityManager.createQuery(QUERY, Document.class).getResultList();
        assertNotNull(documents);
        assertEquals(documents.size(), 1);
        assertEquals(doc, documents.get(0));
        assertEquals(2, documents.get(0).getPortabilities().size());
        closeEntityManager();
    }


    /**
     * Test to remove portability.
     *
     * @throws ClientException the client exception
     */
    @Test
    public void testRemovePortability() throws ClientException {
        loadEntityManager();
        // create Os and browser ...............................................
        final Os ubuntu = new Os(OS_UBUNTU, OsType.LINUX, OsArchi.PC_64_BITS);
        final Browser firefox = new Browser(BrowserType.FIREFOX, FF_8_0);

        beginTransaction();
        entityManager.persist(ubuntu);
        entityManager.persist(firefox);
        commitTransaction();

        // create portabilities ................................................
        final Portability ubuntuFF = new Portability(ubuntu, firefox);

        beginTransaction();
        entityManager.persist(ubuntuFF);
        commitTransaction();

        // associate with document .............................................
        final Document doc = new Document(DOC_TITLE, DOC_PATH);
        doc.addPortability(ubuntuFF);

        beginTransaction();
        entityManager.persist(doc);
        commitTransaction();

        List<Document> documents = entityManager.createQuery(QUERY, Document.class).getResultList();
        assertEquals(documents.size(), 1);
        assertEquals(doc, documents.get(0));


        // remove portability ..................................................
        doc.removePortability(ubuntuFF);
        beginTransaction();
        entityManager.merge(doc);
        commitTransaction();
        
        documents = entityManager.createQuery(QUERY, Document.class).getResultList();
        assertNotNull(documents);
        assertEquals(documents.size(), 1);
        assertEquals(doc, documents.get(0));
        assertTrue(documents.get(0).getPortabilities().isEmpty());

        closeEntityManager();
    }
}
