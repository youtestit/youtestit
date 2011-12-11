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
package org.youtestit.datamodel.dao;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.commons.utils.exceptions.ErrorsMSG;
import org.youtestit.datamodel.entity.User;



/**
 * UserDAO
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 11, 2011
 */
@Singleton
@Named
public class UserDAO {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    @PersistenceContext
    EntityManager entityManager;

    
    

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Instantiates a new user dao.
     */
    public UserDAO() {
        super();
    }
    
    /**
     * Constructor for Unit test
     *
     * @param entityManager the entity manager
     */
    protected UserDAO(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    

    // =========================================================================
    // METHODS
    // =========================================================================

    /**
     * Creates the user.
     *
     * @param user the user
     * @throws ClientException the client exception
     */
    public void createUser(User user) throws ClientException{
        if(user==null){
            throw new ClientException(ErrorsMSG.VALUE_NOT_NULL);
        }

    }

    // =========================================================================
    // OVERRIDES
    // =========================================================================


    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================
}


