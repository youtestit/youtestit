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
package org.youtestit.commons.utils.sha1;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.youtestit.commons.utils.exceptions.ClientException;



/**
 * Sha1EncryptionTest
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 11, 2011
 */
public class Sha1EncryptionTest {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(Sha1EncryptionTest.class);

    

    // =========================================================================
    // METHODS
    // =========================================================================
    
    /**
     * Allow to test get instance method.
     *
     * @throws ClientException the client exception
     */
    @Test
    public void testGetInstance() throws ClientException{
        LOGGER.debug("getInstanceTest");
        Sha1Encryption service = Sha1Encryption.getInstance();
        assertNotNull(service );
    }

    

    /**
     * Allow to test encrypt to sha1.
     *
     * @throws ClientException the client exception
     */
    @Test
    public void testEncryptToSha1() throws ClientException{
        LOGGER.debug("encryptToSha1Test");
        Sha1Encryption service = Sha1Encryption.getInstance();
        
        final String password = "myPassword";
        final String result = service.encryptToSha1(password);
        assertNotNull("result musn't be null",result);
        assertFalse("Error result and password musn't be equals",password.equals(result));
        
        final String password2 = "mySecondPassword";
        final String result2 = service.encryptToSha1(password2);
        assertNotNull("result2 musn't be null",result2);
        assertFalse("Error result and result2 musn't be equals",result.equals(result2));
        
        final String password3 = "myPassword";
        final String result3 = service.encryptToSha1(password3);
        
        LOGGER.info("password1 : {}",result);
        LOGGER.info("password3 : {}",result3);
        assertNotNull("result3 musn't be null",result3);
        assertTrue("Error result and result3 must be equals",result.equals(result3));
        
    }
}


