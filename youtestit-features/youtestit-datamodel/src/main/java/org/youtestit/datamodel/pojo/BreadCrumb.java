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
package org.youtestit.datamodel.pojo;

import java.io.Serializable;


/**
 * BreadCrumb.
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Feb 5, 2012
 */
public class BreadCrumb implements Serializable {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1186732477802821003L;


    /** The name. */
    private String            name;

    /** The path. */
    private String            path;


    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Instantiates a new bread crumb.
     */
    public BreadCrumb() {
        super();
    }

    /**
     * Instantiates a new bread crumb.
     * 
     * @param name the name
     * @param path the path
     */
    public BreadCrumb(String name, String path) {
        super();
        this.name = name;
        this.path = path;
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
        result = prime * result + ((path == null) ? 0 : path.hashCode());
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
        if (obj == null || getClass() != obj.getClass()) {
            result = false;
        } else {
            final BreadCrumb other = (BreadCrumb) obj;
            result = (path == null && other.path == null) || (path.equals(other.path));
        }

        return result;
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
     * Gets the path.
     * 
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the path.
     * 
     * @param path the new path
     */
    public void setPath(String path) {
        this.path = path;
    }


}
