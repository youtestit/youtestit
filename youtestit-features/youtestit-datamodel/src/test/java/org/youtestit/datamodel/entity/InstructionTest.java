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
import org.youtestit.datamodel.enums.SeleniumActionType;


/**
 * Test unit for Instruction entity.
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Jan 11, 2012
 */
public class InstructionTest extends AbstractEntityTest {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant LOGGER. */
    private static final Logger LOGGER    = LoggerFactory.getLogger(InstructionTest.class);

    /** The Constant ERROR_MSG. */
    private static final String ERROR_MSG = "error";

    /** The Constant VALUE. */
    private static final String VALUE     = "value";

    /** The Constant TARGET. */
    private static final String TARGET    = "target";

    // =========================================================================
    // METHODS
    // =========================================================================

    /**
     * All Instruction are identify by their uid. Two Instruction object are
     * equals if they have the same uid. It's database table ID. This test allow
     * to check it and verify if basic java usage work well with User entity
     * 
     * @throws ClientException if test fail
     */
    @Test
    public void equalsHashCodeTest() throws ClientException {
        LOGGER.info("equalsHashCodeTest");

        final Instruction instructionA = new Instruction(1, SeleniumActionType.click, TARGET, VALUE, ERROR_MSG);
        final Instruction instructionB = new Instruction(2, SeleniumActionType.type, TARGET, VALUE, ERROR_MSG);

        assertFalse(instructionA.equals(instructionB));

        final Instruction instructionC = new Instruction(1, SeleniumActionType.addSelection, TARGET, VALUE, ERROR_MSG);
        assertTrue(instructionA.equals(instructionC));


        final List<Instruction> operatingSystems = new ArrayList<Instruction>();
        operatingSystems.add(instructionA);
        operatingSystems.add(instructionB);

        assertTrue(operatingSystems.contains(instructionA));
        assertTrue(operatingSystems.contains(instructionB));

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void persistenceTest() throws ClientException {
        LOGGER.info("persistenceTest");
        assertNotNull(entityManager);

        final String query = "from  Instruction";
        List<Instruction> resultSet = entityManager.createQuery(query, Instruction.class).getResultList();
        assertNotNull(resultSet);
        assertTrue(resultSet.isEmpty());

        final Instruction instruction = new Instruction(SeleniumActionType.click, TARGET, VALUE, ERROR_MSG);
        
        beginTransaction();
        entityManager.persist(instruction);
        commitTransaction();

        resultSet = entityManager.createQuery(query, Instruction.class).getResultList();
        assertNotNull(resultSet);
        assertEquals(resultSet.size(), 1);

        assertEquals(instruction, resultSet.get(0));

    }
}
