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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.commons.utils.exceptions.ErrorsMSG;
import org.youtestit.datamodel.entity.Portability;

@Singleton
@Named
public class PortabilityDAO implements Serializable {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5577302363588124323L;
  
    /** The logger. */
    @Inject
    private Logger log;
    
    /** The entity manager. */
    @PersistenceContext
    private EntityManager entityManager;
    
    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Instantiates a new portability dao.
     */
    public PortabilityDAO() {
        super();
    }
    
    /**
     * Instantiates a new portability dao in unit test.
     *
     * @param entityManager the entity manager
     * @param log the log
     */
    protected PortabilityDAO(final EntityManager entityManager,final Logger log) {
        super();
        this.log = log;
        this.entityManager = entityManager;
    }

    
    // =========================================================================
    // METHODS
    // =========================================================================

    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // CREATE
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    

    /**
     * Allow to create a portability
     *
     * @param portability the portability to create
     * @return created portability
     * @throws ClientException if error is occur.
     */
    public Portability create(final Portability portability) throws ClientException{
        log.debug("Portability create");
        
        if(portability == null){
            throw new ClientException(ErrorsMSG.VALUE_NOT_NULL);
        }
        
        Portability result = null;
        try{
            result =entityManager.createNamedQuery(Portability.QUERY_PROTABILITY,Portability.class)
                         .setParameter(Portability.PARAM_OS, portability.getOperatingSystem())
                         .setParameter(Portability.PARAM_BROWSER, portability.getBrowser())
                         .getSingleResult();
        }catch (NoResultException e) {
            // it can have no Entity in database. it isn't an error !
            log.debug(e);
        }
        
        if(result==null){
            entityManager.persist(portability);
            result = portability;
        }
        
        return result;
        
    }
    


    /**
     * Allow to create some portability. 
     *
     * @param portabilities some portability to create
     * @return some portability created
     * @throws ClientException if error is occur.
     */
    public List<Portability> create(final List<Portability> portabilities) throws ClientException{
        log.debug("List<Portability> create");
        
        if(portabilities == null){
            throw new ClientException(ErrorsMSG.VALUE_NOT_NULL); 
        }
        
        List<Portability> result = new ArrayList<Portability>();

        for (Portability item : portabilities){
            result.add(create(item));
        }
        
        return result;
    }
    
    
}
