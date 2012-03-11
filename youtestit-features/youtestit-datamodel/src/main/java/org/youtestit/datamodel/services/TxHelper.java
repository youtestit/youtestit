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
package org.youtestit.datamodel.services;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.youtestit.commons.utils.exceptions.ClientException;


/**
 * TxHelper allow to wrap transaction manager
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Mar 11, 2012
 */
@Singleton
@Named
public class TxHelper implements Serializable {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2339652755632727385L;

    @Inject
    private UserTransaction   utx;


    // =========================================================================
    // METHODS
    // =========================================================================

    /**
     * allow to begin transaction.
     *
     * @throws ClientException the client exception
     */
    public void begin() throws ClientException {
        try {
            utx.begin();
        } catch (NotSupportedException e) {
            throw new ClientException(e);
        } catch (SystemException e) {
            throw new ClientException(e);
        }
    }

    /**
     * allow to commit transaction.
     *
     * @throws ClientException the client exception
     */
    public void commit() throws ClientException {
        try {
            utx.commit();
        } catch (SecurityException e) {
            roolback();
        } catch (IllegalStateException e) {
            roolback();
        } catch (RollbackException e) {
            throw new ClientException(e);
        } catch (HeuristicMixedException e) {
            roolback();
        } catch (HeuristicRollbackException e) {
            throw new ClientException(e);
        } catch (SystemException e) {
            roolback();
        }
    }


    /**
     * allow to roolback transaction.
     *
     * @throws ClientException the client exception
     */
    public void roolback() throws ClientException {
        try {
            utx.rollback();
        } catch (IllegalStateException e) {
            throw new ClientException(e);
        } catch (SecurityException e) {
            throw new ClientException(e);
        } catch (SystemException e) {
            throw new ClientException(e);
        }
    }

}
