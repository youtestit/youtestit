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

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


/**
 * Portability.
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Jan 2, 2012
 */
@Entity
public class Portability {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================

    /** The name. */
    @Id
    @GeneratedValue
    private long    uid;

    /** The operating system. */
    @NotNull
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "os_id", nullable = false, updatable = false)
    private Os      operatingSystem;

    /** The browsers. */
    @NotNull
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "browser_id", nullable = false, updatable = false)
    private Browser browser;

    /** The strict. */
    private boolean strict;


    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Instantiates a new portability.
     */
    public Portability() {
        super();
    }


    /**
     * Instantiates a new portability. By default the protability isn't strict.
     * If you running a test, Youtestit will try to get the most similar render
     * node.
     * 
     * @param operatingSystem the operating system
     * @param browser the browser
     */
    public Portability(final Os operatingSystem, final Browser browser) {
        super();
        this.operatingSystem = operatingSystem;
        this.browser = browser;
        this.strict = false;
    }

    /**
     * Instantiates a new portability.
     * 
     * @param operatingSystem the operating system
     * @param browser the browser
     * @param strict the strict
     */
    public Portability(final Os operatingSystem, final Browser browser, final boolean strict) {
        super();
        this.operatingSystem = operatingSystem;
        this.browser = browser;
        this.strict = strict;
    }


    /**
     * Instantiates a new portability for unit test.
     * 
     * @param operatingSystem the operating system
     * @param browser the browser
     * @param strict the strict
     */
    protected Portability(final long uid, final Os operatingSystem, final Browser browser, final boolean strict) {
        super();
        this.uid = uid;
        this.operatingSystem = operatingSystem;
        this.browser = browser;
        this.strict = strict;
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
        final int nameHash = String.valueOf(uid).hashCode();
        result = prime * result + nameHash;
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

        if (obj != null && getClass() == obj.getClass()) {
            final Portability other = (Portability) obj;
            result = uid == other.uid;
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder("Portability [");

        result.append("uid=" + uid);
        result.append(", operatingSystem=" + operatingSystem);
        result.append(", browser=" + browser);
        result.append(", strict=" + strict);

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
    public long getUid() {
        return uid;
    }


    /**
     * Sets the uid.
     * 
     * @param uid the new uid
     */
    public void setUid(final int uid) {
        this.uid = uid;
    }


    /**
     * Gets the operating system.
     * 
     * @return the operating system
     */
    public Os getOperatingSystem() {
        return operatingSystem;
    }


    /**
     * Sets the operating system.
     * 
     * @param operatingSystem the new operating system
     */
    public void setOperatingSystem(Os operatingSystem) {
        this.operatingSystem = operatingSystem;
    }


    /**
     * Gets the browser.
     * 
     * @return the browser
     */
    public Browser getBrowser() {
        return browser;
    }


    /**
     * Sets the browser.
     * 
     * @param browser the new browser
     */
    public void setBrowser(Browser browser) {
        this.browser = browser;
    }


    /**
     * Checks if is strict.
     * 
     * @return true, if is strict
     */
    public boolean isStrict() {
        return strict;
    }


    /**
     * Sets the strict.
     * 
     * @param strict the new strict
     */
    public void setStrict(boolean strict) {
        this.strict = strict;
    }


}
