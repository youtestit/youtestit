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
import static org.youtestit.commons.utils.constants.Constants.DOCUMENT_PART;
import static org.youtestit.commons.utils.constants.Constants.ITEM_CLOSE;
import static org.youtestit.commons.utils.constants.Constants.ITEM_OPEN;
import static org.youtestit.commons.utils.constants.Constants.NULL_OBJ;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.youtestit.commons.utils.exceptions.entities.DependencyAssociationException;


/**
 * The Dependency entity allow to persist all dependancies. The dependancies
 * resolver must be a Digital Assets Management resolver. Dependancies graph
 * it's a web of nodes and not a tree. This model is better for cron jobs
 * modelisation. Be careful, we mus't have a cyclic dependancies.
 * 
 * <pre>
 *                         °
 *                parents  ° children
 *       <---------------- ° ------------------->
 *                         °
 *                         °
 *      .---.              °
 *      | A |----.         °
 *      '---'    |         °              .---.
 *               |         °         .--->| D |
 *      .---.    |    .---------.    |    '---'
 *      | B |-------->| Current |----.
 *      '---'    |    '---------'    |    .---.
 *               |         °         '--->| E |
 *      .---.    |         °              '---'
 *      | C |----'         °
 *      '---'              °
 *                         °
 *                         °
 * </pre>
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Jan 10, 2012
 */
@Entity
public class Dependency implements Serializable {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6748704693494054253L;

    /** The name. */
    @Id
    @GeneratedValue
    private long              uid;

    /** The document. */
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "document_id", nullable = true, updatable = false)
    private Document          document;

    /** The parents. */
    @OneToMany(cascade = {CascadeType.REMOVE,CascadeType.PERSIST}, targetEntity = Dependency.class, orphanRemoval = true)
    @JoinColumn(name = "parents_uid", nullable = true)
    private List<Dependency>  parents          = new ArrayList<Dependency>();

    /** The children. */
    @OneToMany(cascade = {CascadeType.REMOVE,CascadeType.PERSIST}, targetEntity = Dependency.class, orphanRemoval = true)
    @JoinColumn(name = "children_uid", nullable = true)
    private List<Dependency>  children         = new ArrayList<Dependency>();

    /** attribut to force waitting parent dependancies done*/
    @Basic(fetch = LAZY)
    private boolean           mustWaitting;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Instantiates a new Dependency.
     */
    public Dependency() {
        super();
    }


    /**
     * Instantiates a new dependency.
     * 
     * @param document the document
     */
    public Dependency(final Document document) {
        super();
        this.document = document;
    }


    /**
     * Instantiates a new Dependency.
     * 
     * @param document the document
     * @param parents the parents
     * @param children the children
     */
    public Dependency(final Document document, final List<Dependency> parents, final List<Dependency> children) {
        super();
        this.document = document;
        this.parents = parents;
        this.children = children;
    }


    /**
     * Instantiates a new Dependency for unit test.
     * 
     * @param uid the uid
     * @param document the document
     * @param parents the parents
     * @param children the children
     */
    protected Dependency(final long uid, final Document document, final List<Dependency> parents,
            final List<Dependency> children) {
        super();
        this.uid = uid;
        this.document = document;
        this.parents = parents;
        this.children = children;
    }

    // =========================================================================
    // METHODS
    // =========================================================================


    /**
     * Adds the parent.
     * 
     * @param dependency the dependency
     * @throws DependencyAssociationException the dependancy association
     *             exception
     */
    public void addParent(final Dependency dependency) throws DependencyAssociationException {
        if (dependency != null) {
            if (children.contains(dependency)) {
                throw new DependencyAssociationException();
            }
            parents.add(dependency);
        }
    }

    /**
     * Removes the parent.
     * 
     * @param dependency the dependency
     */
    public void removeParent(final Dependency dependency) {
        if (parents != null && dependency != null && parents.contains(dependency)) {
            parents.remove(dependency);
        }
    }

    /**
     * Adds the child.
     * 
     * @param dependency the dependency
     * @throws DependencyAssociationException the dependancy association
     *             exception
     */
    public void addChild(final Dependency dependency) throws DependencyAssociationException {
        if (dependency != null) {
            if (parents.contains(dependency)) {
                throw new DependencyAssociationException();
            }
            children.add(dependency);
        }
    }

    /**
     * Removes the child.
     * 
     * @param dependency the dependency
     */
    public void removeChild(final Dependency dependency) {
        if (children != null && dependency != null && children.contains(dependency)) {
            children.remove(dependency);
        }
    }

    /**
     * Allow to check cyclic dependancies. For example a dependency can't be in
     * parent and in children dependencies. This methods check that.
     * 
     * @param current the list where no one dependencies do add must be contain.
     * @param dependenciesToAdd the dependencies to add
     * @throws DependencyAssociationException if one cyclic dependency find
     */
    protected void checkDependencies(final List<Dependency> current, final List<Dependency> dependenciesToAdd)
            throws DependencyAssociationException {

        if (current != null && dependenciesToAdd != null) {
            for (Dependency item : dependenciesToAdd) {
                if (current.contains(item)) {
                    throw new DependencyAssociationException();
                }
            }
        }
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
        final int nbBytes = 32;
        int result = 1;
        result = prime * result + (int) (uid ^ (uid >>> nbBytes));
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
            final Dependency other = (Dependency) obj;
            result = uid == other.uid;
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder("Dependency [");

        result.append("uid=" + uid);
        result.append("mustWaitting=" + mustWaitting);
        result.append(renderDocumentToString());

        result.append(renderListDependencies(", parents=", parents));
        result.append(renderListDependencies(", children=", children));

        return result.toString();
    }

    /**
     * Render document to string.
     * 
     * @return the string
     */
    private String renderDocumentToString() {
        final StringBuilder result = new StringBuilder();

        result.append("document=");
        if (document == null) {
            result.append(NULL_OBJ);
        } else {
            result.append(document.getUid());
            result.append(DOCUMENT_PART);
            result.append(document.getTitle());
        }
        return result.toString();
    }

    /**
     * Render list dependancies.
     * 
     * @param name the property name
     * @param depandancies the list of Dependency
     * @return the render
     */
    private String renderListDependencies(final String name, final List<Dependency> depandancies) {
        final StringBuilder result = new StringBuilder();

        result.append(name);
        if (depandancies == null) {
            result.append(NULL_OBJ);
        } else {
            result.append(ITEM_OPEN);
            for (Dependency item : depandancies) {
                result.append(item.uid);
                if (item.getDocument() != null) {
                    result.append(DOCUMENT_PART);
                    result.append(document.getUid());
                    result.append(DOCUMENT_PART);
                    result.append(document.getTitle());
                }
            }
            result.append(ITEM_CLOSE);
        }
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
    public void setUid(long uid) {
        this.uid = uid;
    }


    /**
     * Gets the document.
     * 
     * @return the document
     */
    public Document getDocument() {
        return document;
    }


    /**
     * Sets the document.
     * 
     * @param document the new document
     */
    public void setDocument(Document document) {
        this.document = document;
    }


    /**
     * Gets the parents.
     * 
     * @return the parents
     */
    public List<Dependency> getParents() {
        return parents;
    }


    /**
     * Sets the parents.
     * 
     * @param parents the new parents
     * @throws DependencyAssociationException if one cyclic dependency find
     */
    public void setParents(List<Dependency> parents) throws DependencyAssociationException {
        checkDependencies(children, parents);
        this.parents = parents;
        if (this.parents == null) {
            this.parents = new ArrayList<Dependency>();
        }
    }


    /**
     * Gets the children.
     * 
     * @return the children
     */
    public List<Dependency> getChildren() {
        return children;
    }


    /**
     * Sets the children.
     * 
     * @param children the new children
     * @throws DependencyAssociationException if one cyclic dependency find
     */
    public void setChildren(List<Dependency> children) throws DependencyAssociationException {
        checkDependencies(parents, children);
        this.children = children;
        if (this.children == null) {
            this.children = new ArrayList<Dependency>();
        }
    }


    /**
     * Checks if is must waitting.
     * 
     * @return true, if is must waitting
     */
    public boolean isMustWaitting() {
        return mustWaitting;
    }


    /**
     * Sets the must waitting.
     * 
     * @param mustWaitting the new must waitting
     */
    public void setMustWaitting(boolean mustWaitting) {
        this.mustWaitting = mustWaitting;
    }


}
