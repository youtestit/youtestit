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

import java.sql.SQLException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.youtestit.commons.utils.exceptions.ClientException;


/**
 * AbstractEntityTest.
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 9, 2011
 */
public abstract class AbstractEntityTest extends TestCaseHelper{
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant LOGGER. */
    private static final Logger  LOGGER = LoggerFactory.getLogger(AbstractEntityTest.class);
 


    // =========================================================================
    // METHODS
    // =========================================================================
    /**
     * Generic method for check entity persistence. It's open and close  
     * entity manager around the test.
     * 
     * @throws ClientException the client exception
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    @Test
    public void checkPersistenceTest() throws ClientException, ClassNotFoundException, SQLException {
        LOGGER.info("run checkPresistance...");
        loadEntityManager();
        persistenceTest();
        closeEntityManager();
    }

    /**
     * Method who realize the unit test for entity. It must be implemented in
     * all entity unit tests. EntityManager is load before running this method and
     * close after.
     * 
     * @throws ClientException the client exception
     */
    protected abstract void persistenceTest() throws ClientException;

}
