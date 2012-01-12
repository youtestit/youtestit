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


/**
 * Test unit for Group entity
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 11, 2011
 * @see org.youtestit.datamodel.entity.User
 */
public class DependencyTest extends AbstractEntityTest {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DependencyTest.class);


    // =========================================================================
    // METHODS
    // =========================================================================

    /**
     * All dependency are identify by their uid. Two Dependency object are
     * equals if they have the same uid. It's database table ID. This test allow
     * to check it and verify if basic java usage work well with User entity
     * 
     * @throws ClientException if test fail
     */
    @Test
    public void equalsHashCodeTest() throws ClientException {
        LOGGER.info("equalsHashCodeTest");

        final Dependency dependA = new Dependency(1, null, null, null);
        final Dependency dependB = new Dependency(2, null, null, null);

        assertFalse(dependA.equals(dependB));

        final Dependency dependC = new Dependency(1, null, null, null);
        assertTrue(dependA.equals(dependC));

        final List<Dependency> dublinCores = new ArrayList<Dependency>();
        dublinCores.add(dependA);
        dublinCores.add(dependB);

        assertTrue(dublinCores.contains(dependA));
        assertTrue(dublinCores.contains(dependB));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void persistenceTest() throws ClientException {
        LOGGER.info("persistenceTest");
        assertNotNull(entityManager);

        // create simple dependency ............................................
        final String query = "from  Dependency";
        List<Dependency> resultSet = entityManager.createQuery(query, Dependency.class).getResultList();
        assertNotNull(resultSet);
        assertTrue(resultSet.isEmpty());


        final Document docB = createDoc("myTestB", "/project/testB");

        final Dependency dependB = new Dependency(docB);
        beginTransaction();
        entityManager.persist(dependB);
        commitTransaction();

        resultSet = entityManager.createQuery(query, Dependency.class).getResultList();
        assertNotNull(resultSet);
        assertEquals(resultSet.size(), 1);

        assertEquals(dependB, resultSet.get(0));

        // create child and parent dependencies ................................
        final Dependency dependA = createDependency("myTest A", "/project/testA");
        final Dependency dependC = createDependency("myTest C", "/project/testC");

        dependB.addParent(dependA);
        dependA.addChild(dependB);

        dependB.addChild(dependC);
        dependC.addParent(dependB);

        beginTransaction();
        entityManager.merge(dependA);
        entityManager.merge(dependB);
        entityManager.merge(dependC);
        commitTransaction();
        
        LOGGER.info(dependB.toString());

    }

    /**
     * Allow to creates a document.
     * 
     * @param title the title
     * @param path the path
     * @return the document
     * @throws ClientException if creation fail
     */
    protected Document createDoc(final String title, final String path) throws ClientException {
        final TestCase doc = new TestCase(title, path);
        beginTransaction();
        entityManager.persist(doc);
        commitTransaction();

        return doc;
    }

    /**
     * Allow to creates a simple dependency.
     * 
     * @param title the title of dependency document
     * @param path the path of dependency document
     * @return the simple dependency
     * @throws ClientException if creation fail
     */
    protected Dependency createDependency(final String title, final String path) throws ClientException {
        final Document doc = createDoc(title, path);

        final Dependency result = new Dependency(doc);

        beginTransaction();
        entityManager.persist(result);
        commitTransaction();

        return result;
    }
}
