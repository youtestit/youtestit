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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


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

    /** The uid. */
    @Id
    @GeneratedValue
    private int           uid;

    /** The operating system. */
    @NotNull
    @ManyToOne(optional = true)
    private Os            operatingSystem;

    /** The browsers. */
    @NotNull
    @NotEmpty
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Browser.class,cascade = CascadeType.REMOVE)
    private List<Browser> browsers;


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
     * Instantiates a new portability.
     * 
     * @param operatingSystem the operating system
     * @param browsers the browsers
     */
    public Portability(final Os operatingSystem, final List<Browser> browsers) {
        super();
        this.operatingSystem = operatingSystem;
        this.browsers = browsers;
    }


    // =========================================================================
    // METHODS
    // =========================================================================
    /**
     * Adds the browser.
     * 
     * @param browser the browser
     */
    public void addBrowser(final Browser browser) {
        if (browsers == null) {
            browsers = new ArrayList<Browser>();
        }
        browsers.add(browser);
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
        result = prime * result + uid;
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
     * Gets the browsers.
     * 
     * @return the browsers
     */
    public List<Browser> getBrowsers() {
        return browsers;
    }


    /**
     * Sets the browsers.
     * 
     * @param browsers the new browsers
     */
    public void setBrowsers(List<Browser> browsers) {
        this.browsers = browsers;
    }
}
