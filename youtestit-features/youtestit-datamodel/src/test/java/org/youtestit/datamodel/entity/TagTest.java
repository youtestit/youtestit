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
public class TagTest extends AbstractEntityTest {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant LOGGER. */
    private static final Logger LOGGER  = LoggerFactory.getLogger(TagTest.class);

    /** The Constant TAG_FOO : foo */
    private static final String TAG_FOO = "foo";

    // =========================================================================
    // METHODS
    // =========================================================================

    /**
     * All tag are identify by their name. Two Tags object are equals if they
     * have the same name. It's database table ID. This test allow to check it
     * and verify if basic java usage work well with User entity
     * 
     * @throws ClientException if test fail
     */
    @Test
    public void equalsHashCodeTest() throws ClientException {
        LOGGER.info("equalsHashCodeTest");

        final Tag foo = new Tag(TAG_FOO);
        final Tag bar = new Tag("bar");
        assertFalse(foo.equals(bar));

        final Tag fooB = new Tag(TAG_FOO);
        assertTrue(foo.equals(fooB));

        final List<Tag> tags = new ArrayList<Tag>();
        tags.add(foo);
        tags.add(bar);

        assertTrue(tags.contains(foo));
        assertTrue(tags.contains(bar));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void persistenceTest() throws ClientException {
        LOGGER.info("persistenceTest");
        assertNotNull(entityManager);

        final String query = "from  Tag";
        List<Tag> tags = entityManager.createQuery(query, Tag.class).getResultList();
        assertNotNull(tags);
        assertTrue(tags.isEmpty());

        final Tag myTag = new Tag("myTag");
        beginTransaction();
        entityManager.persist(myTag);
        commitTransaction();

        tags = entityManager.createQuery(query, Tag.class).getResultList();
        assertNotNull(tags);
        assertEquals(tags.size(), 1);
        assertEquals(myTag, tags.get(0));
    }
    
    
    /**
     * Allow to test association of Tags with Document
     *
     * @throws ClientException if test fail
     */
    @Test
    public void testTagWithDocument() throws  ClientException{
        loadEntityManager();
        
        // create tags .........................................................
        final Tag frontoffice = new Tag("frontoffice");
        final Tag backoffice = new Tag("backoffice");
        
        beginTransaction();
        entityManager.persist(frontoffice);
        entityManager.persist(backoffice);
        commitTransaction();
        
        // create doc and add tags .............................................        
        final Document doc = new Document("test view", "/youtestit/view");
        doc.addTag(frontoffice);
        doc.addTag(backoffice);
        
        beginTransaction();
        entityManager.persist(doc);
        commitTransaction();
        
        assertEquals(doc.getTags().size(), 2);
        

        // remove tags .........................................................
        doc.removeTag(frontoffice);
        doc.removeTag(backoffice);
        
        beginTransaction();
        entityManager.merge(doc);
        commitTransaction();

        assertTrue(doc.getTags().isEmpty());
        
        closeEntityManager();
    }
    
}
