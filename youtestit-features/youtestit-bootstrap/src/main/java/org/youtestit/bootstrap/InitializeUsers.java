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

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.jboss.logging.Logger;
import org.youtestit.bootstrap.events.InitializeOsAndBrowserEvent;
import org.youtestit.bootstrap.events.InitializeUser;
import org.youtestit.commons.utils.Constants;
import org.youtestit.commons.utils.ConstantsProperties;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.commons.utils.exceptions.FatalException;
import org.youtestit.datamodel.dao.UserDAO;
import org.youtestit.datamodel.entity.Group;
import org.youtestit.datamodel.entity.Profile;
import org.youtestit.datamodel.entity.User;


/**
 * InitializeUsers
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 26, 2011
 */
public class InitializeUsers extends InitializeHelper{
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    @Inject
    private Logger          log;

    @PersistenceContext
    private EntityManager   entityManager;

    @Inject
    private UserDAO         userDAO;

    
    @Inject
    @InitializeOsAndBrowserEvent
    private Event<String> initializeOsAndBrowserEvent;


    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Initialize file system foldes (/home/${user}/.youtestit).
     * 
     * @param webapp the webapp
     */
    public void initialize(@Observes
    @InitializeUser
    String value) {
        log.info("initialize users for YouTestit");

        try {
            initialiseAdminUser();
        } catch (ClientException e) {
            throw new FatalException(e);
        }
        
        initializeOsAndBrowserEvent.fire("initializeOsAndBrowserEvent");
    }

    // =========================================================================
    // METHODS
    // =========================================================================

    /**
     * Initialise admin user.
     * 
     * @throws ClientException the client exception
     */
    protected void initialiseAdminUser() throws ClientException {
        final ConstantsProperties properties = Constants.getInstance().getConstantsProperties();

        User admin = userDAO.getUserByLogin(properties.getUserAdmin());

        if (admin == null) {
            Profile profile = initializeProfile();
            Group group = initializeGroup();

            User user = new User(properties.getUserAdmin(), properties.getUserAdminEmail(),
                    properties.getUserAdminPassword(), properties.getUserAdmin(), properties.getUserAdmin(), profile);
            user.setEnable(true);

            begin();
            entityManager.persist(user);
            commit();


            List<Group> groups = new ArrayList<Group>();
            groups.add(group);
            user.setGroups(groups);

            if (group.getUsers() == null) {
                group.setUsers(new ArrayList<User>());
            }
            group.getUsers().add(user);

            begin();
            entityManager.merge(group);
            entityManager.merge(user);
            commit();

        }
    }

    /**
     * Allow to initialize administrator profile. If it not exist, this method
     * will create it.
     * 
     * @return the administrator profile
     * @throws ClientException the client exception
     */
    protected Profile initializeProfile() throws ClientException {
        final ConstantsProperties properties = Constants.getInstance().getConstantsProperties();

        Profile profile = null;

        try {
            profile = entityManager.createNamedQuery(Profile.PROFILE_BY_NAME, Profile.class).setParameter(
                    Profile.PROFILE_BY_NAME_PARAM_NAME, properties.getProfileAdministrator()).getSingleResult();
        } catch (NoResultException ex) {
            // if not existe we must creat it....it's not an error.
            log.debug("Administrator profile not exists...", ex);
        }


        if (profile == null) {
            profile = new Profile(properties.getProfileAdministrator(), true, true);
            begin();
            entityManager.persist(profile);
            commit();
        } else {
            profile.setAdministrator(true);
            profile.setEnable(true);
            begin();
            entityManager.merge(profile);
            commit();
        }


        return profile;
    }

    /**
     * Allow to initialize administrator group. If it not exist, this method
     * will create it.
     * 
     * @return the administrator group
     * @throws ClientException the client exception
     */
    protected Group initializeGroup() throws ClientException {
        final ConstantsProperties properties = Constants.getInstance().getConstantsProperties();

        Group group = null;

        try {
            group = entityManager.createNamedQuery(Group.GROUP_BY_NAME, Group.class).setParameter(
                    Group.GROUP_BY_NAME_PARAM_NAME, properties.getGroupAdministrator()).getSingleResult();
        } catch (NoResultException ex) {
            // if not existe we must creat it....it's not an error.
            log.debug("Administrator group not exists...", ex);
        }

        if (group == null) {
            group = new Group();
            group.setName(properties.getGroupAdministrator());
            begin();
            entityManager.persist(group);
            commit();
        }

        return group;
    }


}
