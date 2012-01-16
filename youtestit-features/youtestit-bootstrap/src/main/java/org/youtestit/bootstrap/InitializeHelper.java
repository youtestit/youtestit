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

import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.youtestit.commons.utils.exceptions.ClientException;



/**
 * InitializeHelper
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Jan 13, 2012
 */
public class InitializeHelper {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    @Inject
    private UserTransaction utx;


    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================


    // =========================================================================
    // METHODS
    // =========================================================================
    protected void begin() throws ClientException {
        try {
            if(!hasTransaction()){
                utx.begin();    
            }
            
        } catch (NotSupportedException e) {
            throw new ClientException(e);
        } catch (SystemException e) {
            throw new ClientException(e);
        }
    }

    protected void commit() throws ClientException {
        try {
            if(hasTransaction()){
                utx.commit();    
            }
            
        } catch (SecurityException e) {
            rollback();
            throw new ClientException(e);
        } catch (IllegalStateException e) {
            rollback();
            throw new ClientException(e);
        } catch (RollbackException e) {
            throw new ClientException(e);
        } catch (HeuristicMixedException e) {
            rollback();
            throw new ClientException(e);
        } catch (HeuristicRollbackException e) {
            throw new ClientException(e);
        } catch (SystemException e) {
            rollback();
            throw new ClientException(e);
        }
    }

    protected void rollback() throws ClientException {
        try {
            if(hasTransaction()){
                utx.rollback();    
            }
            
        } catch (IllegalStateException e) {
            throw new ClientException(e);
        } catch (SecurityException e) {
            throw new ClientException(e);
        } catch (SystemException e) {
            throw new ClientException(e);
        }
    }
    
    /**
     * Checks if transaction is active.
     *
     * @return true, if successful
     * @throws ClientException if exception is occur.
     */
    protected boolean hasTransaction() throws ClientException {
        boolean result = true;
        
        try {
            result = utx.getStatus()==Status.STATUS_ACTIVE;
        } catch (SystemException e) {
           throw new ClientException(e);
        }
        
        
        return result;
    }

}


