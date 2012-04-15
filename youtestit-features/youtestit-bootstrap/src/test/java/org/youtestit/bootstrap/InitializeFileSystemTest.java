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
package org.youtestit.bootstrap;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.youtestit.commons.utils.JUnitLogger;
import org.youtestit.commons.utils.constants.Constants;
import org.youtestit.commons.utils.exceptions.ClientException;


/**
 * InitializeFileSystemTest
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 26, 2011
 */
public class InitializeFileSystemTest {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    private static final Logger LOGGER = LoggerFactory.getLogger(InitializeFileSystemTest.class);


    // =========================================================================
    // METHODS
    // =========================================================================
    @Test
    public void testInitializeFoldes() throws ClientException {
        LOGGER.info("testInitializeFoldes");
        InitializeFileSystem service = new InitializeFileSystem(new JUnitLogger(InitializeFileSystemTest.class));
        assertNotNull(service);
        LOGGER.info(Constants.USER_HOME);

        try {
            service.initializeFoldes();
            Constants.getInstance().resetProperties();
        } catch (ClientException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }


    @Test
    public void testInitializeConfiguration() throws ClientException {
        LOGGER.info("testInitializeConfiguration");
        InitializeFileSystem service = new InitializeFileSystem(new JUnitLogger(InitializeFileSystemTest.class));
        assertNotNull(service);
        
        try {
            service.initializeConfiguration();
        } catch (ClientException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }

}
