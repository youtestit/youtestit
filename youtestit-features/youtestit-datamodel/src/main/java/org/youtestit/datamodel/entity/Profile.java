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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


/**
 * User Profile
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 8, 2011
 */
@Entity
public class Profile {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================

    /** The login. */
    @Id
    @NotNull
    private String  name;


    /** The administrator. */
    private boolean administrator = false;

    /** The enable. */
    private boolean enable        = true;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Default constructor for instantiates a new profile.
     */
    public Profile() {
        super();
    }


    /**
     * Instantiates a new profile with all require values.
     * 
     * @param name the name
     */
    public Profile(String name) {
        super();
        this.name = name;
    }


    /**
     * Instantiates a new profile with all values.
     * 
     * @param name profile name
     * @param administrator if it's an Administrator profile
     * @param enable this profile is enable
     */
    public Profile(String name, boolean administrator, boolean enable) {
        super();
        this.name = name;
        this.administrator = administrator;
        this.enable = enable;
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
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
            final Profile other = (Profile) obj;
            if (name != null && name.equals(other.name)) {
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

        result.append("administrator=");
        result.append(administrator);
        result.append(sep);

        result.append("enable=");
        result.append(enable);
        result.append("]");

        return result.toString();
    }


    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================

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
    protected void setName(String name) {
        this.name = name;
    }


    /**
     * Checks if is administrator.
     * 
     * @return true, if is administrator
     */
    public boolean isAdministrator() {
        return administrator;
    }


    /**
     * Sets the administrator.
     * 
     * @param administrator the new administrator
     */
    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }


    /**
     * Checks if is enable.
     * 
     * @return true, if is enable
     */
    public boolean isEnable() {
        return enable;
    }


    /**
     * Sets the enable.
     * 
     * @param enable the new enable
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
    }


}
