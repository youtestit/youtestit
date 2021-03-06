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

import org.hibernate.validator.constraints.NotEmpty;


/**
 * Simple Tag entity.
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Jan 8, 2012
 */
@Entity
public class Tag {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The name. */
    @Id
    @NotNull
    @NotEmpty
    private String name;


    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Instantiates a new tag.
     */
    public Tag() {
        super();
    }


    /**
     * Instantiates a new tag.
     * 
     * @param name the name
     */
    public Tag(String name) {
        super();
        this.name = name;
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
            result = true;
        }

        if (obj != null && obj != null && getClass() == obj.getClass()) {
            final Tag other = (Tag) obj;
            result = name.equals(other.name);
        }

        return result;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Tag [name=" + name + "]";
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
    public void setName(String name) {
        this.name = name;
    }

}
