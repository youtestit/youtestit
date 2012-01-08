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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;


/**
 * Generic Youtestit Document.
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 30, 2011
 */
@Entity
public class Document extends DublinCore implements Serializable {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6251772001497647256L;

    /** The importance. */
    private Integer           importance;

    /** The complexity. */
    private Integer           complexity;

    /** The portability. */
    @ManyToOne(targetEntity = Portability.class, cascade = CascadeType.REMOVE)
    private List<Portability> portabilities;

    /** The url wiki. */
    private String            urlWiki;

    /** The url tracker. */
    private String            urlTracker;

    /** The url server. */
    private String            urlServer;


    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Instantiates a new document.
     */
    public Document() {
        super();
    }

    /**
     * Instantiates a new document.
     * 
     * @param title the document title
     * @param path the document path (like /foo/bar )
     * @param subject the document subject, it's document short description
     * @param creator the document creator
     * @param dateCreation the document name
     * 
     * @see org.youtestit.datamodel.entity.User
     */
    public Document(final String title, final String path, final String subject, final User creator,
            final Calendar dateCreation) {
        super(title, path, subject, creator, dateCreation);
    }

    /**
     * Instantiates a new document with require values.
     * 
     * @param name the document name
     * @param path the document path
     */
    public Document(String name, String path) {
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
        final String nullObj = "null";

        result.append(", importance=");
        result.append(importance);

        result.append(", complexity=");
        result.append(complexity);

        result.append(",\nportabilities=");
        if (portabilities == null) {
            result.append(nullObj);
        } else {
            result.append("{");
            for (Portability item : portabilities) {
                result.append(item);
                result.append("\n");
            }
            result.append("}");
        }
        result.append(portabilities);

        result.append(", urlWiki=");
        result.append(urlWiki);

        result.append(", urlTracker=");
        result.append(urlTracker);

        result.append(", urlServer=");
        result.append(urlServer);

        return result.toString();
    }

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================

    /**
     * Gets the importance.
     * 
     * @return the importance
     */
    public Integer getImportance() {
        return importance;
    }


    /**
     * Sets the importance.
     * 
     * @param importance the new importance
     */
    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    /**
     * Gets the complexity.
     * 
     * @return the complexity
     */
    public Integer getComplexity() {
        return complexity;
    }

    /**
     * Sets the complexity.
     * 
     * @param complexity the new complexity
     */
    public void setComplexity(Integer complexity) {
        this.complexity = complexity;
    }

    /**
     * Gets the portability.
     * 
     * @return the portability
     */
    @ManyToOne(targetEntity = Portability.class, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, optional = true)
    public List<Portability> getPortabilities() {
        return portabilities;
    }

    /**
     * Sets the portability.
     * 
     * @param portabilities the new portabilities
     */
    public void setPortabilities(final List<Portability> portabilities) {
        this.portabilities = portabilities;
    }

    /**
     * Add portability.
     * 
     * @param portability the portability to add
     */
    public void addPortability(final Portability portability) {
        if (portabilities == null) {
            portabilities = new ArrayList<Portability>();
        }
        if (portability != null) {
            portabilities.add(portability);
        }
    }

    /**
     * Removes portability.
     * 
     * @param portability the portability to remove
     */
    public void removePortability(final Portability portability) {
        if (portabilities != null && portability != null && portabilities.contains(portability)) {
            portabilities.remove(portability);
        }
    }

    /**
     * Gets the url wiki.
     * 
     * @return the url wiki
     */
    public String getUrlWiki() {
        return urlWiki;
    }

    /**
     * Sets the url wiki.
     * 
     * @param urlWiki the new url wiki
     */
    public void setUrlWiki(String urlWiki) {
        this.urlWiki = urlWiki;
    }

    /**
     * Gets the url tracker.
     * 
     * @return the url tracker
     */
    public String getUrlTracker() {
        return urlTracker;
    }

    /**
     * Sets the url tracker.
     * 
     * @param urlTracker the new url tracker
     */
    public void setUrlTracker(String urlTracker) {
        this.urlTracker = urlTracker;
    }

    /**
     * Gets the url server.
     * 
     * @return the url server
     */
    public String getUrlServer() {
        return urlServer;
    }

    /**
     * Sets the url server.
     * 
     * @param urlServer the new url server
     */
    public void setUrlServer(String urlServer) {
        this.urlServer = urlServer;
    }
}