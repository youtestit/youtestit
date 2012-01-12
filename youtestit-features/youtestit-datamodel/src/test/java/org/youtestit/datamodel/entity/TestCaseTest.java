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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.datamodel.enums.SeleniumActionType;


/**
 * Test unit for Test entity.
 *
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 11, 2011
 * @see org.youtestit.datamodel.entity.User
 */
public class TestCaseTest extends AbstractEntityTest {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant LOGGER. */
    private static final Logger LOGGER    = LoggerFactory.getLogger(TestCaseTest.class);

    /** The Constant JOE_EMAIL. */
    private static final String JOE_EMAIL = "joe@youtestit.org";

    /** The Constant LOGIN_JOE. */
    private static final String JOE_LOGIN = "Joe";

    /** The Constant NAME_JOE. */
    private static final String JOE_NAME  = "joe";

    /** The Constant KEY_GEST. */
    private static final String KEY_GEST  = "Gest";

    /** The Constant ERROR_MSG. */
    private static final String ERROR_MSG = "error";

    /** The Constant VALUE. */
    private static final String VALUE     = "value";

    /** The Constant TARGET. */
    private static final String TARGET    = "target";

    /** The Constant PATH_A. */
    private static final String PATH_A    = "/project/testA";

    /** The Constant PATH_B. */
    private static final String PATH_B    = "/project/testB";


    // =========================================================================
    // METHODS
    // =========================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public void persistenceTest() throws ClientException {
        LOGGER.info("persistenceTest");
        assertNotNull(entityManager);

        final String query = "from  TestCase";
        List<TestCase> resultSet = entityManager.createQuery(query, TestCase.class).getResultList();
        assertNotNull(resultSet);
        assertTrue(resultSet.isEmpty());

        final User user = createUser();
        final List<Instruction> instructions = createInstructions();


        final List<Dependency> dependencies = new ArrayList<Dependency>();
        dependencies.add(createDependency("myTest A", PATH_A));
        dependencies.add(createDependency("myTest B", PATH_B));

        final String currentDoc = "/myTest";
        final TestCase test = new TestCase("My Test", currentDoc);
        test.setFonctionnalReferer(user);
        test.setTester(user);
        test.setDevelopper(user);
        test.setSeleniumInstructions(instructions);
        test.setDependancies(dependencies);

        beginTransaction();
        entityManager.persist(test);
        commitTransaction();

        final int nbDocumentMustHave = 3;
        resultSet = entityManager.createQuery(query, TestCase.class).getResultList();
        assertNotNull(resultSet);
        assertEquals(resultSet.size(), nbDocumentMustHave);


        assertEquals(resultSet.get(0).getPath(), PATH_A);
        assertEquals(resultSet.get(1).getPath(), PATH_B);
        assertEquals(resultSet.get(2).getPath(), currentDoc);
        assertEquals(resultSet.get(2), test);

        LOGGER.info(test.toString());

    }


    /**
     * Allow to create a user.
     * 
     * @return the user created
     * @throws ClientException if creation fail
     */
    protected User createUser() throws ClientException {
        final Profile profile = new Profile(KEY_GEST);

        beginTransaction();
        entityManager.persist(profile);
        commitTransaction();

        beginTransaction();
        final User user = new User(JOE_NAME, JOE_EMAIL, "kqz@@15#$W", JOE_LOGIN, JOE_NAME, profile);
        entityManager.persist(user);
        commitTransaction();

        return user;

    }


    /**
     * Allow to creates instructions.
     * 
     * @return the list of instructions
     * @throws ClientException if creation fail
     */
    protected List<Instruction> createInstructions() throws ClientException {

        final Instruction instructA = new Instruction(SeleniumActionType.click, TARGET, VALUE, ERROR_MSG);
        final Instruction instructB = new Instruction(SeleniumActionType.type, TARGET, VALUE, ERROR_MSG);
        final Instruction instructC = new Instruction(SeleniumActionType.select, TARGET, VALUE, ERROR_MSG);

        beginTransaction();
        entityManager.persist(instructA);
        entityManager.persist(instructB);
        entityManager.persist(instructC);
        commitTransaction();

        final List<Instruction> result = new ArrayList<Instruction>();
        result.add(instructA);
        result.add(instructB);
        result.add(instructC);

        return result;
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
        final TestCase doc = new TestCase(title, path);
        beginTransaction();
        entityManager.persist(doc);
        commitTransaction();
        final Dependency result = new Dependency(doc);

        beginTransaction();
        entityManager.persist(result);
        commitTransaction();

        return result;
    }
}
