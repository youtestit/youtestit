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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.datamodel.enums.ServerType;


/**
 * Test unit for Group entity.
 *
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 11, 2011
 * @see org.youtestit.datamodel.entity.User
 */
public class ProjectTest extends DublinCoreTest {




    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant LOGGER. */
    private static final Logger LOGGER   = LoggerFactory.getLogger(ProjectTest.class);

    /** The Constant PASSWORD. */
    private static final String PASSWORD = "kqz@@15#$W";

    /** The Constant FOO. */
    private static final String FOO      = "foo";

    /** The Constant JOE. */
    private static final String JOE      = "joe";

    // =========================================================================
    // METHODS
    // =========================================================================


    /**
     * {@inheritDoc}
     */
    @Override
    public void persistenceTest() throws ClientException {
        LOGGER.info("persistenceTest");
        assertNotNull(entityManager);

        final String query = "from  Project";
        List<Project> resultSet = entityManager.createQuery(query, Project.class).getResultList();
        assertNotNull(resultSet);
        assertTrue(resultSet.isEmpty());


        final Project project = new Project("My Project", "/myproject");
        project.setTeam(createTeam());
        project.setVersion("0.1-SNAPSHOT");
        project.setServerType(ServerType.INTEGRATION);

        beginTransaction();
        entityManager.persist(project);
        commitTransaction();

        resultSet = entityManager.createQuery(query, Project.class).getResultList();
        assertNotNull(resultSet);
        assertEquals(resultSet.size(), 1);

        assertEquals(project, resultSet.get(0));

    }


    /**
     * Creates the team.
     *
     * @return the map
     * @throws ClientException the client exception
     */
    public Map<Profile, Group> createTeam() throws ClientException {

        final Profile profile = new Profile("member", true, true);
        beginTransaction();
        entityManager.persist(profile);
        commitTransaction();

        final User joe = new User(JOE, "joe@youtestit.org", PASSWORD, JOE, JOE, profile);
        final User foo = new User(FOO, "foo@youtestit.org", PASSWORD, FOO, FOO, profile);

        beginTransaction();
        entityManager.persist(joe);
        entityManager.persist(foo);
        commitTransaction();

        final List<User> users = new ArrayList<User>();
        users.add(joe);
        users.add(foo);

        final Group group = new Group("project_managers", users);
        beginTransaction();
        entityManager.persist(group);
        commitTransaction();

        final Profile managers = new Profile("managers", true, true);
        beginTransaction();
        entityManager.persist(managers);
        commitTransaction();

        final Map<Profile, Group> team = new HashMap<Profile, Group>();
        team.put(managers, group);

        return team;
    }
}
