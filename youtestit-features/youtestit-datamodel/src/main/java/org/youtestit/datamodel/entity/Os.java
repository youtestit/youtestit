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

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.validator.constraints.NotEmpty;
import org.youtestit.datamodel.enums.OsArchi;
import org.youtestit.datamodel.enums.OsType;


/**
 * Operating System.
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Jan 2, 2012
 */
@Entity
@NamedQueries({ @NamedQuery(name = Os.QUERY_ALL_OS, query = "FROM Os"),
        @NamedQuery(name = Os.QUERY_OS_BY_NAME, query = "FROM Os WHERE name=:" + Os.PARAM_OS_BY_NAME),
        @NamedQuery(name = Os.QUERY_OS_BY_TYPE, query = "FROM Os WHERE type=:" + Os.PARAM_OS_BY_TYPE) })
public class Os implements Serializable {
    // =========================================================================
    // STATICS ATTRIBUTES
    // =========================================================================
    /** Named Query for select ALL_OS. */
    public static final String QUERY_ALL_OS     = "allOs";

    /** Named Query for select an OS by name */
    public static final String QUERY_OS_BY_NAME = "osByName";

    /** Parameter for Named query OS_BY_NAME, it's name value */
    public static final String PARAM_OS_BY_NAME = "name";

    /** Named Query for select an OS list by type */
    public static final String QUERY_OS_BY_TYPE = "osByType";

    /** Parameter for Named query OS_BY_TYPE, it's type value */
    public static final String PARAM_OS_BY_TYPE = "type";

    /** The Constant serialVersionUID. */
    private static final long  serialVersionUID = 8315547300525419958L;

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================

    /** The name. */
    @NotEmpty
    @Id
    private String             name;

    /** The type. */
    @Enumerated(EnumType.STRING)
    @Basic(fetch = FetchType.LAZY)
    private OsType             type;

    /** The architecture. */
    @Enumerated(EnumType.STRING)
    @Basic(fetch = FetchType.LAZY)
    private OsArchi            architecture;


    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Instantiates a new os.
     */
    public Os() {
        super();
    }


    /**
     * Instantiates a new os.
     * 
     * @param name the name
     */
    public Os(final String name, final OsType type, final OsArchi architecture) {
        super();
        this.name = name;
        this.type = type;
        this.architecture = architecture;
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

        int nameHash = 0;
        int typeHash = 0;
        int archiHash = 0;

        if (name != null) {
            nameHash = name.hashCode();
        }

        if (type != null) {
            typeHash = type.hashCode();
        }

        if (architecture != null) {
            archiHash = architecture.hashCode();
        }
        result = prime * result + nameHash;
        result = prime * result + typeHash;
        result = prime * result + archiHash;
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
            final Os other = (Os) obj;
            final boolean sameName = name.equals(other.name);
            final boolean sametype = same(type, other.type);
            final boolean sameArchi = same(architecture, other.architecture);
            result = sameName && sametype && sameArchi;
        }

        return result;
    }

    /**
     * Allow to check if two object are same.
     * 
     * @param objRef reference object
     * @param objToTest object to check
     * @return true if they are equals.
     */
    protected boolean same(final Object objRef, final Object objToTest) {
        boolean result = false;
        if (objRef == null) {
            if (objToTest == null) {
                result = true;
            }
        } else {
            result = objRef.equals(objToTest);
        }

        return result;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Os [name=" + name + ", type=" + type + ", architecture=" + architecture + "]";
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


    /**
     * Gets the type.
     * 
     * @return the type
     */
    public OsType getType() {
        return type;
    }


    /**
     * Sets the type.
     * 
     * @param type the new type
     */
    public void setType(OsType type) {
        this.type = type;
    }


    /**
     * Gets the architecture.
     * 
     * @return the architecture
     */
    public OsArchi getArchitecture() {
        return architecture;
    }


    /**
     * Sets the architecture.
     * 
     * @param architecture the new architecture
     */
    public void setArchitecture(OsArchi architecture) {
        this.architecture = architecture;
    }


}
