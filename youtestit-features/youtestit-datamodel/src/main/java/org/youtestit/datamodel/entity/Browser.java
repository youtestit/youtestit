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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.youtestit.datamodel.enums.BrowserType;


/**
 * Browser entity.
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Jan 2, 2012
 */
@Entity
@NamedQueries({
        @NamedQuery(name = Browser.QUERY_ALL_BROWSER,
                    query = "FROM Browser"),
        
        @NamedQuery(name = Browser.QUERY_BROWSER_BY_ID,
                    query = "FROM Browser WHERE name=:" + Browser.PARAM_ID),
        
        @NamedQuery(name = Browser.QUERY_BROWSER_BY_TYPE,
                    query = "FROM Browser WHERE type=:" + Browser.PARAM_TYPE),

        @NamedQuery(name = Browser.QUERY_BROWSER_BY_TYPE_AND_VERSION,
                    query = "FROM Browser WHERE type=:" + Browser.PARAM_TYPE + " AND version=:" + Browser.PARAM_VERSION) })
public class Browser implements Serializable {
    // =========================================================================
    // STATICS ATTRIBUTES
    // =========================================================================
    /** Named Query for select all browsers. */
    public static final String QUERY_ALL_BROWSER                 = "allBrowser";

    /** Named Query for select browsers by id. */
    public static final String QUERY_BROWSER_BY_ID               = "browserById";


    /** Named Query for select browsers by type. */
    public static final String QUERY_BROWSER_BY_TYPE             = "browserByType";


    public static final String QUERY_BROWSER_BY_TYPE_AND_VERSION = "browserByTypeAndVersion";


    /** Parameter for Named query, it's browser uid. */
    public static final String PARAM_ID                          = "uid";

    /** Parameter for Named query, it's browser type. */
    public static final String PARAM_TYPE                        = "type";

    /** Parameter for Named query, it's browser version. */
    public static final String PARAM_VERSION                     = "version";

    /** The Constant serialVersionUID. */
    private static final long  serialVersionUID                  = -2476222938649994016L;


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================

    /** The name. */
    @Id
    @GeneratedValue
    private int                uid;

    /** The name. */
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private BrowserType        type;

    /** The version. */
    @Column(name = "version")
    private String             version;

    /** The selenium command. */
    private String             seleniumCommand;


    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Instantiates a new os.
     */
    public Browser() {
        super();
    }


    /**
     * Instantiates a new os.
     * 
     * @param type the type
     */
    public Browser(final BrowserType type) {
        super();
        this.type = type;
    }

    /**
     * Instantiates a new browser.
     * 
     * @param type the browser type
     * @param version the version
     */
    public Browser(final BrowserType type, final String version) {
        super();
        this.type = type;
        this.version = version;
    }


    /**
     * Instantiates a new browser.
     * 
     * @param type the browser type
     * @param version the version
     * @param seleniumCommand the selenium command
     */
    public Browser(final BrowserType type, final String version, final String seleniumCommand) {
        super();
        this.type = type;
        this.version = version;
        this.seleniumCommand = seleniumCommand;
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
        int versionHash = 0;

        if (type != null) {
            nameHash = type.hashCode();
        }
        if (version != null) {
            versionHash = version.hashCode();
        }


        result = prime * result + uid;
        result = prime * result + nameHash;
        result = prime * result + versionHash;
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
            final Browser other = (Browser) obj;
            final boolean sameName = type.equals(other.type);
            final boolean sameVersion = sameVersion(other.version);

            result = sameName && sameVersion;
        }

        return result;
    }

    /**
     * Allow to check if a version is same than current brower version.
     * 
     * @param otherVersion the other version
     * @return true, if successful
     */
    protected boolean sameVersion(final String otherVersion) {
        boolean result = false;

        if (version == null) {
            if (otherVersion == null) {
                result = true;
            }
        } else {
            result = version.equals(otherVersion);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder("Browser [");
        result.append("uid=" + uid);
        result.append(", type=" + type);
        result.append(", version=" + version);
        result.append(", seleniumCommand=" + seleniumCommand);
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
     * Gets the type.
     * 
     * @return the type
     */
    public BrowserType getType() {
        return type;
    }


    /**
     * Sets the type.
     * 
     * @param type the new type
     */
    public void setType(BrowserType type) {
        this.type = type;
    }

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
    public void setVersion(String version) {
        this.version = version;
    }


    /**
     * Gets the selenium command.
     * 
     * @return the selenium command
     */
    public String getSeleniumCommand() {
        return seleniumCommand;
    }


    /**
     * Sets the selenium command.
     * 
     * @param seleniumCommand the new selenium command
     */
    public void setSeleniumCommand(String seleniumCommand) {
        this.seleniumCommand = seleniumCommand;
    }


}
