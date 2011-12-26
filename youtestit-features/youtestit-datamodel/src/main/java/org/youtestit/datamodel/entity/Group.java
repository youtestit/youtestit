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

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * User Profile.
 *
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 8, 2011
 */
@Entity
@Table(name = "yti_group")
public class Group {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================

    /** The uid. */
    @Id
    @NotNull
    @GeneratedValue
    private int uid; 
    
    /** The login. */
    @NotNull
    private String  name;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity=User.class,cascade=CascadeType.ALL)
    @JoinTable(name="users_goups")
    private List<User> users;



    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Default constructor for instantiates a new profile.
     */
    public Group() {
        super();
    }

    /**
     * Instantiates a new group in unit test.
     *
     * @param uid the uid
     * @param name the name
     */
    protected Group(final int uid, final String name) {
        super();
        this.uid = uid;
        this.name = name;
    }
    
    /**
     * Instantiates a new group.
     *
     * @param name the name
     * @param users the users
     */
    protected Group(final String name, final List<User> users) {
        super();
        this.name = name;
        this.users = users;
    }



   
    // =========================================================================
    // OVERRIDES
    // =========================================================================
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result +uid;
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;

        if (this == obj) {
            return true;
        }

        if (obj != null && getClass() == obj.getClass()) {
            final Group other = (Group) obj;
            if (uid == other.uid || name.equals(other.getName())) {
                result = true;
            }
        }

        return result;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final String sep = ",";
        StringBuilder result = new StringBuilder("Profile [");

        result.append("name=");
        result.append(name);
        result.append(sep);

        result.append("users=");
        result.append(users);
        
        result.append("]");

        return result.toString();
    }


    

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================
    /**
     * Gets the uid.
     *
     * @return the uid
     */
    public int getUid() {
        return uid;
    }


    /**
     * Sets the uid.
     *
     * @param uid the new uid
     */
    public void setUid(int uid) {
        this.uid = uid;
    }


    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the users.
     *
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Sets the users.
     *
     * @param users the new users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    

}
