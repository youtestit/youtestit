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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import net.iharder.Base64;

import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.commons.utils.exceptions.ErrorsMSG;



/**
 * Sh
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 10, 2011
 */
public class Sha1Encryption {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant INSTANCE. */
    private static final Sha1Encryption INSTANCE = new Sha1Encryption();

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Singleton contructor a new sh1 encryption.
     */
    private Sha1Encryption(){
        super();
    }
    
    public static Sha1Encryption getInstance() {
        return INSTANCE;
    }

    // =========================================================================
    // METHODS
    // =========================================================================
    /**
     * Allo to encrypt a value to SHA1 algorithme
     *
     * @param value the value to encrypt
     * @return the value encrypted in SHA1 
     * @throws ClientException the client exception
     */
    public String encryptToSha1(String value) throws ClientException{
        synchronized (this) {
            if(value==null){
                throw new ClientException(ErrorsMSG.VALUE_NOT_NULL);
            }
            
            MessageDigest messageDigest =null;
            final byte[] uniqueKey = value.getBytes(); 
            byte[] hash = null; 
            
            try {
                messageDigest = MessageDigest.getInstance("SHA-1");
                hash = messageDigest.digest(uniqueKey);
            } catch (NoSuchAlgorithmException e) {
                throw new ClientException("Can't find SHA1 algorithme",e);
            }
            
            return new String(Base64.encodeBytes(hash));
        }
        
    }

}


