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
import org.youtestit.commons.utils.exceptions.entities.EntityExistsException;
import org.youtestit.commons.utils.sha1.Sha1Encryption;
import org.youtestit.datamodel.entity.User;


/**
 * UserDAO Service
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 11, 2011
 */
@Singleton
@Named
public class UserDAO implements Serializable {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long   serialVersionUID = 4258879045230285L;


    private static final String ERR_USER_EXISTS  = "error.entity.user.exists";


    @Inject
    private Logger              log;

    /** The entity manager. */
    @PersistenceContext
    private EntityManager       entityManager;


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

    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // CREATE
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /**
     * Allow to creates a user if he don't already exists.
     * 
     * @param user the user to create
     * @throws ClientException if an error has occur.
     */
    public void createUser(User user) throws ClientException {
        if (user == null) {
            throw new ClientException(ErrorsMSG.VALUE_NOT_NULL);
        }

        final User userInDatabase = getUserByLogin(user.getLogin());
        if (userInDatabase != null) {
            throw new EntityExistsException(ERR_USER_EXISTS);
        }

        final Sha1Encryption sha1Service = Sha1Encryption.getInstance();
        final String cryptedPassword = sha1Service.encryptToSha1(user.getPassword());
        user.setPassword(cryptedPassword);


        entityManager.persist(user);
    }


    /**
     * Allow to save user modification.
     * 
     * @param user the user to save
     * @throws ClientException if an error has occur.
     */
    public void saveUser(User user) throws ClientException {
        if (user == null) {
            throw new ClientException(ErrorsMSG.VALUE_NOT_NULL);
        }
        entityManager.merge(user);
    }

    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // READ
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /**
     * Allow to gets the all users.
     * 
     * @return all users in database
     * @throws ClientException if an error has occur.
     */
    public List<User> getAllUsers() throws ClientException {
        return entityManager.createNamedQuery(User.ALL_USERS, User.class).getResultList();
    }


    /**
     * Gets a user by login.
     * 
     * @param login the login of wanted user.
     * @return the user with specify login or null if he don't exists.
     * @throws ClientException if an error has occur.
     */
    public User getUserByLogin(final String login) throws ClientException {
        if (login == null) {
            throw new ClientException(ErrorsMSG.VALUE_NOT_NULL);
        }

        User result = null;
        try {
            result = entityManager.createNamedQuery(User.USER_BY_LOGIN, User.class).setParameter(
                    User.USER_BY_LOGIN_PARAM_LOGIN, login).getSingleResult();
        } catch (NoResultException e) {
            // user can not exist. it musn't thow exception for this.
            if (log != null && log.isDebugEnabled()) {
                log.debug("search user", e);
            }
        }
        return result;
    }


    /**
     * Gets all users account who waitting to be enable.
     * 
     * @return users accounts
     * @throws ClientException if an error has occur.
     */
    public List<User> getUsersAccountWaittingEnable() throws ClientException {
        final String jpql = "SELECT u FROM User u WHERE u.enable=false";
        return entityManager.createQuery(jpql, User.class)
                            .getResultList();
    }

    
    
    /**
     * Gets the users by name or login.
     *
     * @param value the value
     * @return the users by name or login
     * @throws ClientException the client exception
     */
    public List<User> getUsersByNameOrLogin(final String value) throws ClientException{
        List<User> result = new ArrayList<User>(0);

        if(value ==null){
            return result;
        }
        
        final StringBuilder jpql = new StringBuilder("SELECT u FROM User u WHERE ");
        jpql.append(" u.login like :login");
//        jpql.append(" OR u.lastname like :lastname");
//        jpql.append(" OR u.firstname like :firstname");
//        jpql.append(" OR u.email like :email");
        
        final String valueLike = "%"+value+"%";
        
        result =  entityManager.createQuery(jpql.toString(), User.class)
                .setParameter("login", valueLike)
//                .setParameter("lastname", valueLike)
//                .setParameter("firstname", valueLike)
//                .setParameter("email", valueLike)
                .getResultList();
        
        return result;
    }
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // DELETE
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    /**
     * Allow to delete a user.
     * 
     * @param user the user
     * @throws ClientException if an error has occur.
     */
    public void deleteUser(final User user) throws ClientException {
        if (user == null) {
            throw new ClientException(ErrorsMSG.VALUE_NOT_NULL);
        }
        entityManager.remove(user);
    }


    /**
     * Allow to delete a user.
     * 
     * @param users the users list to delete
     * @throws ClientException if an error has occur.
     */
    public void deleteUsers(final List<User> users) throws ClientException {
        if (users == null) {
            throw new ClientException(ErrorsMSG.VALUE_NOT_NULL);
        }
        for (User user : users) {
            entityManager.remove(user);
        }
    }
}

