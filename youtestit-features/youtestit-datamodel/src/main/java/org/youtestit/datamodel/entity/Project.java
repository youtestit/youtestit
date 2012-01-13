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

import static javax.persistence.FetchType.LAZY;
import static org.youtestit.commons.utils.Constants.NULL_OBJ;
import static org.youtestit.commons.utils.Constants.ITEM_OPEN;
import static org.youtestit.commons.utils.Constants.ITEM_CLOSE;
import static org.youtestit.commons.utils.Constants.SEP;
import java.util.Calendar;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;

import org.youtestit.datamodel.enums.ServerType;


/**
 * Project.
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Jan 10, 2012
 */
@Entity
public class Project extends Document {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long   serialVersionUID = 2176044979636221411L;

    /** The team. */
    @ElementCollection
    @CollectionTable(name = "teams", joinColumns = @JoinColumn(name = "group_fk"))
    @MapKeyColumn(name = "profile", nullable = true)
    private Map<Profile, Group> team;

    /** The version. */
    @Basic(fetch = LAZY)
    private String              version;

    /** The server type. */
    @Enumerated(EnumType.STRING)
    private ServerType          serverType;


    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================

    /**
     * Instantiates a new project.
     */
    public Project() {
        super();
    }

    /**
     * Instantiates a new project.
     * 
     * @param title the title
     * @param path the path
     * @param subject the subject
     * @param creator the creator
     * @param dateCreation the date creation
     */
    public Project(final String title, final String path, final String subject, final User creator,
            final Calendar dateCreation) {
        super(title, path, subject, creator, dateCreation);
    }

    /**
     * Instantiates a new project.
     * 
     * @param name the name
     * @param path the path
     */
    public Project(final String name, final String path) {
        super(name, path);
    }

    // =========================================================================
    // OVERRIDES
    // =========================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    protected String toStringContent() {
        final StringBuilder result = new StringBuilder(super.toStringContent());


        result.append(", version=" + version);
        result.append(", tests=");
        result.append(", serverType=" + serverType);

        result.append(", team=");
        if (team == null) {
            result.append(NULL_OBJ);
        } else {
            result.append(ITEM_OPEN);
            for (Profile key : team.keySet()) {
                result.append(key.getName());
                result.append("=");
                result.append(ITEM_OPEN);
                result.append(team.get(key));
                result.append(ITEM_CLOSE);
                result.append(SEP);
            }
            result.append(ITEM_CLOSE);
        }

        return result.toString();
    }

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================


    /**
     * Gets the version.
     * 
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the version.
     * 
     * @param version the new version
     */
    public void setVersion(final String version) {
        this.version = version;
    }


    /**
     * Gets the server type.
     * 
     * @return the server type
     */
    public ServerType getServerType() {
        return serverType;
    }

    /**
     * Sets the server type.
     * 
     * @param serverType the new server type
     */
    public void setServerType(ServerType serverType) {
        this.serverType = serverType;
    }

    /**
     * Gets the team.
     * 
     * @return the team
     */
    public Map<Profile, Group> getTeam() {
        return team;
    }

    /**
     * Sets the team.
     * 
     * @param team the team
     */
    public void setTeam(Map<Profile, Group> team) {
        this.team = team;
    }

}
